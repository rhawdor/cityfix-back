package fr.hexagone.versailles.cityfix.connector.spinalcore.dto;

import java.util.List;

public class SpinalCoreControlEndpointResponse {

    private List<SpinalCoreControlEndpoint> endpoints;

    public List<SpinalCoreControlEndpoint> getEndpoints() {
        return endpoints;
    }

    public SpinalCoreControlEndpointResponse setEndpoints(List<SpinalCoreControlEndpoint> endpoints) {
        this.endpoints = endpoints;
        return this;
    }
}
