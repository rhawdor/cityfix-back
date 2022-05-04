package fr.hexagone.versailles.cityfix.connector.spinalcore.dto;

import java.math.BigInteger;

public class SpinalCoreTicketCreation {

    private String workflow;

    private String process;

    private BigInteger nodeDynamicId;

    private String name;

    private String description;

    private String declarer_id;

    private Integer priority;

    public String getWorkflow() {
        return workflow;
    }

    public SpinalCoreTicketCreation setWorkflow(String workflow) {
        this.workflow = workflow;
        return this;
    }

    public String getProcess() {
        return process;
    }

    public SpinalCoreTicketCreation setProcess(String process) {
        this.process = process;
        return this;
    }

    public BigInteger getNodeDynamicId() {
        return nodeDynamicId;
    }

    public SpinalCoreTicketCreation setNodeDynamicId(BigInteger nodeDynamicId) {
        this.nodeDynamicId = nodeDynamicId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SpinalCoreTicketCreation setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SpinalCoreTicketCreation setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDeclarer_id() {
        return declarer_id;
    }

    public SpinalCoreTicketCreation setDeclarer_id(String declarer_id) {
        this.declarer_id = declarer_id;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public SpinalCoreTicketCreation setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

}
