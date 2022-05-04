package fr.hexagone.versailles.cityfix.service;

import fr.hexagone.versailles.cityfix.connector.spinalcore.SpinalCoreConnector;
import fr.hexagone.versailles.cityfix.domain.BasicObject;
import fr.hexagone.versailles.cityfix.domain.Ticket;
import fr.hexagone.versailles.cityfix.domain.TicketCreation;
import fr.hexagone.versailles.cityfix.domain.TicketShort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final BuildingService buildingService;

    private final SpinalCoreConnector spinalCoreConnector;

    public TicketService(BuildingService buildingService, SpinalCoreConnector spinalCoreConnector) {
        this.buildingService = buildingService;
        this.spinalCoreConnector = spinalCoreConnector;
    }

    public List<Ticket> getTickets() {
        return buildingService.getBuildings()
                .stream()
                .flatMap(building -> {
                    var floors= spinalCoreConnector.getFloors(building.getApiUrl());
                    var tickets = new ArrayList<TicketShort>();
                    tickets.addAll(floors.stream()
                            .flatMap(floor -> spinalCoreConnector.getRoomsFromFloor(building.getApiUrl(), floor.getDynamicId()).stream())
                            .flatMap(room -> spinalCoreConnector.getTicketsFromRoom(building.getApiUrl(), room.getDynamicId()).stream().map(ticket -> mapToTicketShort(ticket, room.getName())))
                            .toList());
                    tickets.addAll(floors.stream()
                            .flatMap(floor -> spinalCoreConnector.getTicketsFromFloor(building.getApiUrl(), floor.getDynamicId()).stream().map(ticket -> mapToTicketShort(ticket, floor.getName())))
                            .toList());
                    return tickets.stream()
                            .map(ticket -> spinalCoreConnector.getTicketDetails(building.getApiUrl(), ticket.getDynamicId())
                                    .setFrom(ticket.getFrom())
                                    .setBuildingName(building.getBuildingName())
                                    .setBuildingId(building.getId()))
                            .filter(ticket -> ticket.getProcess().getName().equals("Maintenance"));
                }).toList();
    }

    public Integer getTicketsCount() {
        return (int) buildingService.getBuildings()
                .stream()
                .flatMap(building -> {
                    var floors= spinalCoreConnector.getFloors(building.getApiUrl());
                    var tickets = new ArrayList<TicketShort>();
                    tickets.addAll(floors.stream()
                            .flatMap(floor -> spinalCoreConnector.getRoomsFromFloor(building.getApiUrl(), floor.getDynamicId()).stream())
                            .flatMap(room -> spinalCoreConnector.getTicketsFromRoom(building.getApiUrl(), room.getDynamicId()).stream().map(ticket -> mapToTicketShort(ticket, room.getName())))
                            .toList());
                    tickets.addAll(floors.stream()
                            .flatMap(floor -> spinalCoreConnector.getTicketsFromFloor(building.getApiUrl(), floor.getDynamicId()).stream().map(ticket -> mapToTicketShort(ticket, floor.getName())))
                            .toList());
                    return tickets.stream()
                            .map(ticket -> spinalCoreConnector.getTicketDetails(building.getApiUrl(), ticket.getDynamicId())
                                    .setFrom(ticket.getFrom())
                                    .setBuildingName(building.getBuildingName())
                                    .setBuildingId(building.getId()))
                            .filter(ticket -> ticket.getProcess().getName().equals("Maintenance")
                                    && ticket.getStep().getName().equals("En attente de réalisation"));
                }).count();
    }

    public Ticket createTicket(@NonNull Long buildingId, @NonNull TicketCreation ticketCreation) {
        var building = buildingService.getBuilding(buildingId);
        var ticket = spinalCoreConnector.createTicket(building.getApiUrl(), ticketCreation);
        var nodeInformation = spinalCoreConnector.getNode(ticketCreation.getNodeDynamicId().toString());
        return spinalCoreConnector.getTicketDetails(building.getApiUrl(), ticket.getDynamicId())
                .setFrom(nodeInformation.getName())
                .setBuildingId(building.getId())
                .setBuildingName(building.getBuildingName());
    }

    private TicketShort mapToTicketShort(@NonNull BasicObject ticket, @NonNull String from) {
        var ticketShort = new TicketShort().setFrom(from);
        ticketShort.setName(ticket.getName()).setDynamicId(ticket.getDynamicId());
        return ticketShort;
    }

}
