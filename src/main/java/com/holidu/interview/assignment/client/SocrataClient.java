package com.holidu.interview.assignment.client;

import com.holidu.interview.assignment.config.AppProperties;
import com.holidu.interview.assignment.model.client.TreeData;
import com.holidu.interview.assignment.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class SocrataClient {

    private final RestTemplate restTemplate;
    private final AppProperties properties;

    @Autowired
    public SocrataClient(RestTemplate restTemplate, AppProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public List<TreeData> searchForTrees(Coordinates coordinatesMin, Coordinates coordinatesMax) {
        String whereCondition = String.format("latitude >= %s AND latitude <= %s AND longitude >= %s AND longitude <= %s", coordinatesMin.getLatitude(), coordinatesMax.getLatitude(), coordinatesMin.getLongitude(), coordinatesMax.getLongitude());
        URI uri = UriComponentsBuilder.fromUriString(properties.getSocrataUri())
                .queryParam("$select", "spc_common,latitude,longitude")
                .queryParam("$where", whereCondition)
                .queryParam("$limit", Integer.MAX_VALUE)
                .build().toUri();

        ResponseEntity<List<TreeData>> result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<TreeData>>(){});
        return result.getBody();
    }
}
