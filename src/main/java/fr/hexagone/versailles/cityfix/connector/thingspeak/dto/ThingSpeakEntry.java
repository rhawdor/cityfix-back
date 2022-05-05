package fr.hexagone.versailles.cityfix.connector.thingspeak.dto;

public class ThingSpeakEntry {

    private Double field1;

    private Double field2;

    private Integer field3;

    public Double getField1() {
        return field1;
    }

    public ThingSpeakEntry setField1(Double field1) {
        this.field1 = field1;
        return this;
    }

    public Double getField2() {
        return field2;
    }

    public ThingSpeakEntry setField2(Double field2) {
        this.field2 = field2;
        return this;
    }

    public Integer getField3() {
        return field3;
    }

    public ThingSpeakEntry setField3(Integer field3) {
        this.field3 = field3;
        return this;
    }
}
