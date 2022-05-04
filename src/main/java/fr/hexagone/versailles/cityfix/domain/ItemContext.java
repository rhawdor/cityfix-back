package fr.hexagone.versailles.cityfix.domain;

import java.util.List;

public class ItemContext {

    private Integer totalElements;

    private Integer alertElements;

    private Integer sum;

    private List<ItemAlert> alerts;

    public Integer getSum() {
        return sum;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public ItemContext setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public Integer getAlertElements() {
        return alertElements;
    }

    public ItemContext setAlertElements(Integer alertElements) {
        this.alertElements = alertElements;
        return this;
    }

    public ItemContext setSum(Integer sum) {
        this.sum = sum;
        return this;
    }

    public List<ItemAlert> getAlerts() {
        return alerts;
    }

    public ItemContext setAlerts(List<ItemAlert> alerts) {
        this.alerts = alerts;
        return this;
    }
}
