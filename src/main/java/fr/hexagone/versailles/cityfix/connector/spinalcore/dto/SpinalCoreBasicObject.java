package fr.hexagone.versailles.cityfix.connector.spinalcore.dto;

public class SpinalCoreBasicObject {

    private String dynamicId;

    private String name;

    public String getDynamicId() {
        return dynamicId;
    }

    public SpinalCoreBasicObject setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpinalCoreBasicObject setName(String name) {
        this.name = name;
        return this;
    }
}
