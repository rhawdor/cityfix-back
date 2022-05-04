package fr.hexagone.versailles.cityfix.service;

import fr.hexagone.versailles.cityfix.domain.DashboardContext;
import fr.hexagone.versailles.cityfix.service.context.AirService;
import fr.hexagone.versailles.cityfix.service.context.ElectricityService;
import fr.hexagone.versailles.cityfix.service.context.GazService;
import fr.hexagone.versailles.cityfix.service.context.WaterService;
import org.springframework.stereotype.Service;

@Service
public class ContextService {

    private final AirService airService;

    private final WaterService waterService;

    private final ElectricityService electricityService;

    private final GazService gazService;

    private final TicketService ticketService;

    public ContextService(AirService airService,
                          WaterService waterService,
                          ElectricityService electricityService,
                          GazService gazService,
                          TicketService ticketService) {
        this.airService = airService;
        this.waterService = waterService;
        this.electricityService = electricityService;
        this.gazService = gazService;
        this.ticketService = ticketService;
    }

    public DashboardContext getDashboardContext() {
        return new DashboardContext()
                .setAir(airService.getContext())
                .setWater(waterService.getContext())
                .setElectricity(electricityService.getContext())
                .setGaz(gazService.getContext())
                .setWaitingTickets(ticketService.getTicketsCount());
    }

}
