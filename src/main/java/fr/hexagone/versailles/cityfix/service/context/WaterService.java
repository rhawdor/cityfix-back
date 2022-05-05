package fr.hexagone.versailles.cityfix.service.context;

import fr.hexagone.versailles.cityfix.connector.spinalcore.SpinalCoreConnector;
import fr.hexagone.versailles.cityfix.domain.ItemAlert;
import fr.hexagone.versailles.cityfix.domain.ItemContext;
import fr.hexagone.versailles.cityfix.service.BuildingService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterService implements GenericContext {

    private final BuildingService buildingService;

    private final SpinalCoreConnector spinalCoreConnector;

    public WaterService(BuildingService buildingService, SpinalCoreConnector spinalCoreConnector) {
        this.buildingService = buildingService;
        this.spinalCoreConnector = spinalCoreConnector;
    }

    @Override
    @Cacheable("waterContext")
    public ItemContext getContext() {
        return GenericContext.super.getContext();
    }

    @Override
    public Integer getSumContext() {
        return 50;
    }

    @Override
    public List<ItemAlert> getAlerts() {
        return List.of();
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
        return 0;
    }

}
