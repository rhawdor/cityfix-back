package fr.hexagone.versailles.cityfix.service.context;

import fr.hexagone.versailles.cityfix.connector.spinalcore.SpinalCoreConnector;
import fr.hexagone.versailles.cityfix.domain.ItemAlert;
import fr.hexagone.versailles.cityfix.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ElectricityService implements GenericContext {

    private static final Integer LIGHT_VALUE = 20;

    private final BuildingService buildingService;

    private final SpinalCoreConnector spinalCoreConnector;

    public ElectricityService(BuildingService buildingService, SpinalCoreConnector spinalCoreConnector) {
        this.buildingService = buildingService;
        this.spinalCoreConnector = spinalCoreConnector;
    }

    @Override
    public Integer getSumContext() {
        return buildingService.getBuildings().stream().map(building -> {
            var lightsOn = (int) spinalCoreConnector.getFloors(building.getApiUrl()).stream()
                    .flatMap(floor -> spinalCoreConnector.getRoomsFromFloor(building.getApiUrl(), floor.getDynamicId())
                            .stream())
                    .flatMap(room -> spinalCoreConnector.getControlEndpoint(building.getApiUrl(), room.getDynamicId())
                            .stream()
                            .filter(e -> e.getType().equals("Light")))
                    .filter(l -> l.getCurrentValue().equals("true"))
                    .count();
            return lightsOn*LIGHT_VALUE;
        }).mapToInt(Integer::intValue).sum();
    }

    @Override
    public List<ItemAlert> getAlerts() {
        return buildingService.getBuildings()
                .stream()
                .flatMap(building -> spinalCoreConnector.getFloors(building.getApiUrl())
                        .stream()
                        .flatMap(floor -> spinalCoreConnector.getRoomsFromFloor(building.getApiUrl(), floor.getDynamicId())
                                .stream())
                        .map(room -> spinalCoreConnector.getRoomControlEndpoints(building.getApiUrl(), room.getDynamicId())
                                .setRoomName(room.getName())
                                .setRoomDynamicId(room.getDynamicId()))
                        .filter(roomControlEndpoints -> roomControlEndpoints.getEndpoints()
                                .stream()
                                .filter(e -> e.getType().equals("Light") || e.getType().equals("Occupation")).count() > 1))
                .takeWhile(room -> room.getEndpoints().stream().filter(e -> e.getCurrentValue().equals("true")).count() > 1)
                .map(alertElement -> new ItemAlert()
                        .setNodeDynamicId(alertElement.getRoomDynamicId())
                        .setTitle("Consommation d'énergie anormale.")
                        .setDescription("La lumière de la pièce " + alertElement.getRoomName() + " est allumée alors que la pièce n'est pas occupée."))
                .toList();
    }

    @Override
    public Integer getTotalElements() {
        return buildingService.getBuildings().stream().map(building -> (int) spinalCoreConnector.getFloors(building.getApiUrl()).stream()
                .flatMap(floor -> spinalCoreConnector.getRoomsFromFloor(building.getApiUrl(), floor.getDynamicId())
                        .stream())
                .flatMap(room -> spinalCoreConnector.getControlEndpoint(building.getApiUrl(), room.getDynamicId())
                        .stream()
                        .filter(e -> e.getType().equals("Light")))
                .count()).mapToInt(Integer::intValue).sum();
    }

    @Override
    public Integer getAlertElements() {
        return (int) buildingService.getBuildings()
                .stream()
                .flatMap(building -> spinalCoreConnector.getFloors(building.getApiUrl())
                        .stream()
                        .flatMap(floor -> spinalCoreConnector.getRoomsFromFloor(building.getApiUrl(), floor.getDynamicId())
                                .stream())
                        .map(room -> spinalCoreConnector.getRoomControlEndpoints(building.getApiUrl(), room.getDynamicId())
                                .setRoomName(room.getName())
                                .setRoomDynamicId(room.getDynamicId()))
                        .filter(roomControlEndpoints -> roomControlEndpoints.getEndpoints()
                                .stream()
                                .filter(e -> e.getType().equals("Light") || e.getType().equals("Occupation")).count() > 1))
                .takeWhile(room -> room.getEndpoints().stream().filter(e -> e.getCurrentValue().equals("true")).count() > 1)
                .count();
    }
}
