package fr.hexagone.versailles.cityfix.connector.db.repository;

import fr.hexagone.versailles.cityfix.connector.db.dto.BuildingDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingRepository extends CrudRepository<BuildingDTO, Long> {

    List<BuildingDTO> findAll();

}
