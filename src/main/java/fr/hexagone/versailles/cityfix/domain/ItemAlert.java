package fr.hexagone.versailles.cityfix.domain;

public class ItemAlert {

    private String title;

    private String description;

    private String nodeDynamicId;

    public String getTitle() {
        return title;
    }

    public ItemAlert setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemAlert setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getNodeDynamicId() {
        return nodeDynamicId;
    }

    public ItemAlert setNodeDynamicId(String nodeDynamicId) {
        this.nodeDynamicId = nodeDynamicId;
        return this;
    }
}
