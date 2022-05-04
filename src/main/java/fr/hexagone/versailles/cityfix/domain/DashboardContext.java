package fr.hexagone.versailles.cityfix.domain;

public class DashboardContext {

    private ItemContext electricity;

    private ItemContext gaz;

    private ItemContext water;

    private ItemContext air;

    private Integer waitingTickets;

    public ItemContext getElectricity() {
        return electricity;
    }

    public DashboardContext setElectricity(ItemContext electricity) {
        this.electricity = electricity;
        return this;
    }

    public ItemContext getGaz() {
        return gaz;
    }

    public DashboardContext setGaz(ItemContext gaz) {
        this.gaz = gaz;
        return this;
    }

    public ItemContext getWater() {
        return water;
    }

    public DashboardContext setWater(ItemContext water) {
        this.water = water;
        return this;
    }

    public ItemContext getAir() {
        return air;
    }

    public DashboardContext setAir(ItemContext air) {
        this.air = air;
        return this;
    }

    public Integer getWaitingTickets() {
        return waitingTickets;
    }

    public DashboardContext setWaitingTickets(Integer waitingTickets) {
        this.waitingTickets = waitingTickets;
        return this;
    }
}
