package fr.hexagone.versailles.cityfix.connector.thingspeak;

import fr.hexagone.versailles.cityfix.connector.spinalcore.dto.SpinalCoreTicketDetails;
import fr.hexagone.versailles.cityfix.connector.thingspeak.dto.ThingSpeakEntry;
import fr.hexagone.versailles.cityfix.connector.thingspeak.dto.ThingSpeakResponse;
import fr.hexagone.versailles.cityfix.connector.thingspeak.mapper.ThingSpeakMapper;
import fr.hexagone.versailles.cityfix.domain.CaptorObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Component
public class ThingSpeakConnector {

    private static final String THING_SPEAK_URL = "https://api.thingspeak.com/channels/286466/feeds.json?api_key=TOLN7SNZRTI8P9B8&results=1";

    private static final RestTemplate restTemplate = new RestTemplate();

    private final ThingSpeakMapper thingSpeakMapper;

    public ThingSpeakConnector(ThingSpeakMapper thingSpeakMapper) {
        this.thingSpeakMapper = thingSpeakMapper;
    }

    public CaptorObject getCaptorData() {
        RequestEntity<SpinalCoreTicketDetails> request = new RequestEntity<>(HttpMethod.GET, URI.create(THING_SPEAK_URL));
        var captorData = Objects.requireNonNull(restTemplate.exchange(request, ThingSpeakResponse.class)
                .getBody()).getFeeds().stream().findFirst().orElse(new ThingSpeakEntry());
        return thingSpeakMapper.mapToCaptorObject(captorData);
    }

}
