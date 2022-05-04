package fr.hexagone.versailles.cityfix.domain;

public class Step extends BasicObject {

    private String color;

    public String getColor() {
        return color;
    }

    public Step setColor(String color) {
        this.color = color;
        return this;
    }
}
