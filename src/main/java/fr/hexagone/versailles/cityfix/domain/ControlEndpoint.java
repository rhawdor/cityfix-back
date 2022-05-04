package fr.hexagone.versailles.cityfix.domain;

public class ControlEndpoint {

    private String type;

    private String currentValue;

    public String getType() {
        return type;
    }

    public ControlEndpoint setType(String type) {
        this.type = type;
        return this;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public ControlEndpoint setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
        return this;
    }
}
