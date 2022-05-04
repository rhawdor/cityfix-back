package fr.hexagone.versailles.cityfix.connector.spinalcore.dto;

public class SpinalCoreControlEndpoint {

    private String type;

    private String currentValue;

    public String getType() {
        return type;
    }

    public SpinalCoreControlEndpoint setType(String type) {
        this.type = type;
        return this;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public SpinalCoreControlEndpoint setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
        return this;
    }
}
