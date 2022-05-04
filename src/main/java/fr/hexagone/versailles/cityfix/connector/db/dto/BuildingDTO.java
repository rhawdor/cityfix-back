package fr.hexagone.versailles.cityfix.connector.db.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BuildingDTO {

    @Id
    @GeneratedValue
    private Long id;

    private String apiUrl;

    private String buildingName;

    public BuildingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public BuildingDTO setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public BuildingDTO setBuildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }
}
