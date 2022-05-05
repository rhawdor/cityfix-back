package fr.hexagone.versailles.cityfix.connector.spinalcore;

import fr.hexagone.versailles.cityfix.connector.spinalcore.dto.*;
import fr.hexagone.versailles.cityfix.connector.spinalcore.mapper.SpinalCoreMapper;
import fr.hexagone.versailles.cityfix.domain.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Component
public class SpinalCoreConnector {

    private static final String SPINAL_CORE_API = "https://api-usine-hexagone.spinalcom.com/api/v1/";

    private static final RestTemplate restTemplate = new RestTemplate();

    private final SpinalCoreMapper spinalCoreMapper;

    public SpinalCoreConnector(SpinalCoreMapper spinalCoreMapper) {
        this.spinalCoreMapper = spinalCoreMapper;
    }

    public BasicObject getNode(@NonNull String nodeDynamicId) {
        final String NODE_API = "node/" + nodeDynamicId + "/read";
        RequestEntity<SpinalCoreBasicObject> request = new RequestEntity<>(HttpMethod.GET, URI.create(SPINAL_CORE_API + NODE_API));
        var basicObject = restTemplate.exchange(request, SpinalCoreBasicObject.class)
                .getBody();
        return spinalCoreMapper.mapToBasicObject(basicObject);
    }

    @Cacheable("floors")
    public List<BasicObject> getFloors(@NonNull String buildingURL) {
        final String FLOOR_API = "floor/list";
        return doBasicListRequest(buildingURL, FLOOR_API);
    }

    @Cacheable(value = "roomsFromFloor", key = "#floorDynamicId")
    public List<BasicObject> getRoomsFromFloor(@NonNull String buildingURL, @NonNull String floorDynamicId) {
        final String PARAM_API = "floor/" + floorDynamicId + "/room_list";
        return doBasicListRequest(buildingURL, PARAM_API);
    }

    public List<BasicObject> getTicketsFromFloor(@NonNull String buildingURL, @NonNull String floorDynamicId) {
        final String PARAM_API = "node/" + floorDynamicId + "/ticket_list";
        return doBasicListRequest(buildingURL, PARAM_API);
    }

    public List<BasicObject> getTicketsFromRoom(@NonNull String buildingURL, @NonNull String roomDynamicId) {
        final String PARAM_API = "room/" + roomDynamicId + "/ticket_list";
        return doBasicListRequest(buildingURL, PARAM_API);
    }

    public SpinalCoreRoomDetails getRoomDetails(@NonNull String buildingURL, @NonNull String roomDynamicId) {
        final String PARAM_API = "room/" + roomDynamicId + "/read_details";
        RequestEntity<SpinalCoreRoomDetails> request = new RequestEntity<>(HttpMethod.GET, URI.create(buildingURL + PARAM_API));
        return restTemplate.exchange(request, SpinalCoreRoomDetails.class)
                .getBody();
    }

    public Ticket getTicketDetails(@NonNull String buildingURL, @NonNull String ticketDynamicId) {
        final String PARAM_API = "ticket/" + ticketDynamicId + "/read_details";
        RequestEntity<SpinalCoreTicketDetails> request = new RequestEntity<>(HttpMethod.GET, URI.create(buildingURL + PARAM_API));
        var ticketDetails = restTemplate.exchange(request, SpinalCoreTicketDetails.class)
                .getBody();
        return spinalCoreMapper.mapToTicket(ticketDetails);
    }

    public BasicObject createTicket(@NonNull String buildingURL, TicketCreation ticketCreation) {
        final String PARAM_API = "ticket/create_ticket";
        var spinalCoreTicketCreation = spinalCoreMapper.mapToSpinalCoreTicketCreation(ticketCreation);
        spinalCoreTicketCreation
                .setDeclarer_id("CityFix")
                .setWorkflow("Contexte de mission")
                .setPriority(0)
                .setProcess("Maintenance");
        RequestEntity<SpinalCoreTicketCreation> request = new RequestEntity<>(spinalCoreTicketCreation, HttpMethod.POST, URI.create(buildingURL + PARAM_API), SpinalCoreTicketCreation.class);
        var ticket = restTemplate.exchange(request, SpinalCoreBasicObject.class)
                .getBody();
        return spinalCoreMapper.mapToBasicObject(ticket);
    }

    @Cacheable(value = "controlEndpoints", key = "#nodeDynamicId")
    public List<ControlEndpoint> getControlEndpoint(@NonNull String buildingURL, @NonNull String nodeDynamicId) {
        final String PARAM_API = "node/" + nodeDynamicId + "/control_endpoint_list";
        RequestEntity<List<SpinalCoreControlEndpointResponse>> request = new RequestEntity<>(HttpMethod.GET, URI.create(buildingURL + PARAM_API));
        return Objects.requireNonNull(restTemplate.exchange(request, new ParameterizedTypeReference<List<SpinalCoreControlEndpointResponse>>() {})
                        .getBody())
                .stream()
                .flatMap(s -> s.getEndpoints().stream())
                .map(spinalCoreMapper::mapToControlEndpoint)
                .toList();
    }

    @Cacheable(value = "roomControlEndpoints", key = "#nodeDynamicId")
    public RoomControlEndpoints getRoomControlEndpoints(@NonNull String buildingURL, @NonNull String nodeDynamicId) {
        final String PARAM_API = "node/" + nodeDynamicId + "/control_endpoint_list";
        RequestEntity<List<SpinalCoreControlEndpointResponse>> request = new RequestEntity<>(HttpMethod.GET, URI.create(buildingURL + PARAM_API));
        return Objects.requireNonNull(restTemplate.exchange(request, new ParameterizedTypeReference<List<SpinalCoreControlEndpointResponse>>() {})
                        .getBody())
                .stream()
                .map(spinalCoreMapper::mapToRoomControlEndpoints)
                .findFirst()
                .orElse(new RoomControlEndpoints().setEndpoints(List.of()));
    }

    private List<BasicObject> doBasicListRequest(String buildingURL, String PARAM_API) {
        RequestEntity<List<SpinalCoreBasicObject>> request = new RequestEntity<>(HttpMethod.GET, URI.create(buildingURL + PARAM_API));
        return Objects.requireNonNull(restTemplate.exchange(request, new ParameterizedTypeReference<List<SpinalCoreBasicObject>>() {})
                        .getBody())
                .stream()
                .map(spinalCoreMapper::mapToBasicObject)
                .toList();
    }

    @Cacheable(value = "building")
    public BasicObject getBuilding(String buildingURL) {
        final String PARAM_API = "building/read";
        RequestEntity<SpinalCoreBasicObject> request = new RequestEntity<>(HttpMethod.GET, URI.create(buildingURL + PARAM_API));
        var building = restTemplate.exchange(request, SpinalCoreBasicObject.class).getBody();
        return spinalCoreMapper.mapToBasicObject(building);
    }

}
