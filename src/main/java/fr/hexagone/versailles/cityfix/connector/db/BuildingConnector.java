package fr.hexagone.versailles.cityfix.connector.db;

import fr.hexagone.versailles.cityfix.connector.db.dto.BuildingDTO;
import fr.hexagone.versailles.cityfix.connector.db.mapper.BuildingMapper;
import fr.hexagone.versailles.cityfix.connector.db.repository.BuildingRepository;
import fr.hexagone.versailles.cityfix.domain.Building;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class BuildingConnector {

    private final BuildingRepository buildingRepository;

    private final BuildingMapper buildingMapper;

    public BuildingConnector(BuildingRepository buildingRepository, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
    }

    public Building getBuilding(@NonNull Long id) {
        return buildingRepository.findById(id)
                .map(buildingMapper::mapToBuilding)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Building> getBuildings() {
        return buildingRepository.findAll()
                .stream()
                .map(buildingMapper::mapToBuilding)
                .toList();
    }

    public Building addBuilding(@NonNull Building building) {
        BuildingDTO buildingDTO = buildingMapper.mapToBuildingDTO(building);
        buildingRepository.save(buildingDTO);
        return buildingMapper.mapToBuilding(buildingDTO);
    }

    public Building updateBuilding(@NonNull Long id, @NonNull Building building) {
        BuildingDTO buildingDTO = buildingMapper.mapToBuildingDTO(building);
        return buildingRepository.findById(id)
                .map(b -> buildingMapper.updateBuilding(buildingDTO, b))
                .map(buildingRepository::save)
                .map(buildingMapper::mapToBuilding)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteBuilding(@NonNull Long id) {
        buildingRepository.deleteById(id);
    }

}
