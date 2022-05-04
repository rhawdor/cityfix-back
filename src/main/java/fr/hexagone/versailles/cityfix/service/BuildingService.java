package fr.hexagone.versailles.cityfix.service;

import fr.hexagone.versailles.cityfix.connector.db.BuildingConnector;
import fr.hexagone.versailles.cityfix.domain.Building;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingConnector buildingConnector;

    public BuildingService(BuildingConnector buildingConnector) {
        this.buildingConnector = buildingConnector;
    }

    public Building getBuilding(@NonNull Long id) {
        return buildingConnector.getBuilding(id);
    }

    public List<Building> getBuildings() {
        return buildingConnector.getBuildings();
    }

    public Building addBuilding(@NonNull Building building) {
        return buildingConnector.addBuilding(building);
    }

    public Building updateBuilding(@NonNull Long id, @NonNull Building building) {
        return buildingConnector.updateBuilding(id, building);
    }

    public void deleteBuilding(@NonNull Long id) {
        buildingConnector.deleteBuilding(id);
    }

}
