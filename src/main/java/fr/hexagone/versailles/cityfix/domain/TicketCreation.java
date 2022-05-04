package fr.hexagone.versailles.cityfix.domain;

import java.math.BigInteger;

public class TicketCreation {

    private BigInteger nodeDynamicId;

    private String name;

    private String description;

    public BigInteger getNodeDynamicId() {
        return nodeDynamicId;
    }

    public TicketCreation setNodeDynamicId(BigInteger nodeDynamicId) {
        this.nodeDynamicId = nodeDynamicId;
        return this;
    }

    public String getName() {
        return name;
    }

    public TicketCreation setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TicketCreation setDescription(String description) {
        this.description = description;
        return this;
    }

}
