package fr.hexagone.versailles.cityfix.connector.thingspeak.dto;

import java.util.List;

public class ThingSpeakResponse {

    private List<ThingSpeakEntry> feeds;

    public List<ThingSpeakEntry> getFeeds() {
        return feeds;
    }

    public ThingSpeakResponse setFeeds(List<ThingSpeakEntry> feeds) {
        this.feeds = feeds;
        return this;
    }

}

