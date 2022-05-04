package fr.hexagone.versailles.cityfix.connector.spinalcore.dto;

public class SpinalCoreTicketDetails extends SpinalCoreBasicObject {

    private String description;

    private String creationDate;

    private SpinalCoreStepDetails step;

    private SpinalCoreBasicObject process;

    public String getDescription() {
        return description;
    }

    public SpinalCoreTicketDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public SpinalCoreTicketDetails setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public SpinalCoreStepDetails getStep() {
        return step;
    }

    public SpinalCoreTicketDetails setStep(SpinalCoreStepDetails step) {
        this.step = step;
        return this;
    }

    public SpinalCoreBasicObject getProcess() {
        return process;
    }

    public SpinalCoreTicketDetails setProcess(SpinalCoreBasicObject process) {
        this.process = process;
        return this;
    }

}
