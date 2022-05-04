package fr.hexagone.versailles.cityfix.domain;

public class Building {

    private Long id;

    private String apiUrl;

    private String buildingName;

    public Long getId() {
        return id;
    }

    public Building setId(Long id) {
        this.id = id;
        return this;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public Building setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
        return this;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Building setBuildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }
}
