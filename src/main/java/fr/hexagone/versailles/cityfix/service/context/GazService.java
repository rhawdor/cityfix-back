package fr.hexagone.versailles.cityfix.service.context;

import fr.hexagone.versailles.cityfix.connector.spinalcore.SpinalCoreConnector;
import fr.hexagone.versailles.cityfix.domain.BasicObject;
import fr.hexagone.versailles.cityfix.domain.ItemAlert;
import fr.hexagone.versailles.cityfix.domain.ItemContext;
import fr.hexagone.versailles.cityfix.service.BuildingService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GazService implements GenericContext {

    private final BuildingService buildingService;

    private final SpinalCoreConnector spinalCoreConnector;

    public GazService(BuildingService buildingService, SpinalCoreConnector spinalCoreConnector) {
        this.buildingService = buildingService;
        this.spinalCoreConnector = spinalCoreConnector;
    }

    @Override
    @Cacheable("gazContext")
    public ItemContext getContext() {
        return GenericContext.super.getContext();
    }

    @Override
    public Integer getSumContext() {
        return 3200;
    }

    @Override
    public List<ItemAlert> getAlerts() {
        var floor = buildingService.getBuildings()
                .stream()
                .flatMap(building -> spinalCoreConnector.getFloors(building.getApiUrl())
                    .stream())
                .findFirst().orElse(new BasicObject());

        return List.of(new ItemAlert()
                .setTitle("Consommation anormale de gaz.")
                .setDescription("La consommation de l'étage " + floor.getName() + " dépasse le seuil de 100kWh: une fuite peut être à l'origine de l'aléa.")
                .setNodeDynamicId(floor.getDynamicId()));
    }

    @Override
    public Integer getTotalElements() {
        return buildingService.getBuildings()
                .stream()
                .mapToInt(building -> spinalCoreConnector.getFloors(building.getApiUrl())
                        .size())
                .sum();
    }

    @Override
    public Integer getAlertElements() {
        return 1;
    }

}
