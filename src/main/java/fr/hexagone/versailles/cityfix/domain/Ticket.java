package fr.hexagone.versailles.cityfix.domain;

public class Ticket extends BasicObject {

    private Long buildingId;

    private String buildingName;

    private String description;

    private String creationDate;

    private Step step;

    private BasicObject process;

    private String from;

    public Long getBuildingId() {
        return buildingId;
    }

    public Ticket setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Ticket setBuildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Ticket setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Ticket setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Step getStep() {
        return step;
    }

    public Ticket setStep(Step step) {
        this.step = step;
        return this;
    }

    public BasicObject getProcess() {
        return process;
    }

    public Ticket setProcess(BasicObject process) {
        this.process = process;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public Ticket setFrom(String from) {
        this.from = from;
        return this;
    }
}
