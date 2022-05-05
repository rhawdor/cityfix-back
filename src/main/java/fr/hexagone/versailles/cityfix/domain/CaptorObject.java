package fr.hexagone.versailles.cityfix.domain;

public class CaptorObject {

    private Double temperature;

    private Double humidity;

    private Integer air;

    public Double getTemperature() {
        return temperature;
    }

    public CaptorObject setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Double getHumidity() {
        return humidity;
    }

    public CaptorObject setHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

    public Integer getAir() {
        return air;
    }

    public CaptorObject setAir(Integer air) {
        this.air = air;
        return this;
    }
}
