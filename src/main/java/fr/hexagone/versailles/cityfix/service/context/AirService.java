package fr.hexagone.versailles.cityfix.service.context;

import fr.hexagone.versailles.cityfix.connector.spinalcore.SpinalCoreConnector;
import fr.hexagone.versailles.cityfix.connector.thingspeak.ThingSpeakConnector;
import fr.hexagone.versailles.cityfix.domain.ItemAlert;
import fr.hexagone.versailles.cityfix.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirService implements GenericContext {

    private static final Integer MAX_NORMAL_VALUE = 500;

    private final BuildingService buildingService;

    private final ThingSpeakConnector thingSpeakConnector;

    private final SpinalCoreConnector spinalCoreConnector;

    public AirService(BuildingService buildingService,
                      ThingSpeakConnector thingSpeakConnector,
                      SpinalCoreConnector spinalCoreConnector) {
        this.buildingService = buildingService;
        this.thingSpeakConnector = thingSpeakConnector;
        this.spinalCoreConnector = spinalCoreConnector;
    }

    @Override
    public Integer getSumContext() {
        return thingSpeakConnector.getCaptorData().getAir();
    }

    @Override
    public List<ItemAlert> getAlerts() {
        return buildingService.getBuildings()
                .stream().flatMap(building -> {
                    var ppm = thingSpeakConnector.getCaptorData().getAir();
                    var alerts = new ArrayList<ItemAlert>();
                    if (ppm > MAX_NORMAL_VALUE) {
                        var buildingInfo = spinalCoreConnector.getBuilding(building.getApiUrl());
                        alerts.add(new ItemAlert()
                                .setTitle("Qualité d'air médiocre")
                                .setDescription("La taux de PPM dépasse les 500.")
                                .setNodeDynamicId(buildingInfo.getDynamicId())
                        );
                    }
                    return alerts.stream();
                })
                .toList();
    }

    @Override
    public Integer getTotalElements() {
        return 1;
    }

    @Override
    public Integer getAlertElements() {
        return (int) buildingService.getBuildings()
                .stream().flatMap(building -> {
                    var ppm = thingSpeakConnector.getCaptorData().getAir();
                    var alerts = new ArrayList<ItemAlert>();
                    if (ppm > MAX_NORMAL_VALUE) {
                        var buildingInfo = spinalCoreConnector.getBuilding(building.getApiUrl());
                        alerts.add(new ItemAlert()
                                .setTitle("Qualité d'air médiocre")
                                .setDescription("La taux de PPM dépasse les 500.")
                                .setNodeDynamicId(buildingInfo.getDynamicId())
                        );
                    }
                    return alerts.stream();
                })
                .count();
    }

}
