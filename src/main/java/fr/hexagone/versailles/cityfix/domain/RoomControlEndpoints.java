package fr.hexagone.versailles.cityfix.domain;

import java.util.List;

public class RoomControlEndpoints {

    private String roomDynamicId;

    private String roomName;

    private List<ControlEndpoint> endpoints;

    public String getRoomDynamicId() {
        return roomDynamicId;
    }

    public RoomControlEndpoints setRoomDynamicId(String roomDynamicId) {
        this.roomDynamicId = roomDynamicId;
        return this;
    }

    public String getRoomName() {
        return roomName;
    }

    public RoomControlEndpoints setRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public List<ControlEndpoint> getEndpoints() {
        return endpoints;
    }

    public RoomControlEndpoints setEndpoints(List<ControlEndpoint> endpoints) {
        this.endpoints = endpoints;
        return this;
    }
}
