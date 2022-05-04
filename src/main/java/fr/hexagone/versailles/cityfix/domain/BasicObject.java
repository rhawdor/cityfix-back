package fr.hexagone.versailles.cityfix.domain;

public class BasicObject {

    private String dynamicId;

    private String name;

    public String getDynamicId() {
        return dynamicId;
    }

    public BasicObject setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BasicObject setName(String name) {
        this.name = name;
        return this;
    }

}
