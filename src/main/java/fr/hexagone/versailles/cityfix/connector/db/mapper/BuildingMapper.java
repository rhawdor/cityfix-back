package fr.hexagone.versailles.cityfix.connector.db.mapper;

import fr.hexagone.versailles.cityfix.connector.db.dto.BuildingDTO;
import fr.hexagone.versailles.cityfix.domain.Building;
import org.mapstruct.*;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BuildingMapper {

    BuildingDTO updateBuilding(BuildingDTO buildingSource, @MappingTarget BuildingDTO buildingTarget);

    Building mapToBuilding(BuildingDTO buildingDTO);

    BuildingDTO mapToBuildingDTO(Building building);
}
