package com.holidu.interview.assignment.service.impl;

import com.holidu.interview.assignment.client.SocrataClient;
import com.holidu.interview.assignment.model.Coordinates;
import com.holidu.interview.assignment.model.client.TreeData;
import com.holidu.interview.assignment.service.CoordinatesOps;
import com.holidu.interview.assignment.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl implements TreeService {

    private final SocrataClient client;

    @Autowired
    public TreeServiceImpl(SocrataClient client) {
        this.client = client;
    }

    @Override
    public Map<String, Long> search(double latitude, double longitude, double radius) {
        Coordinates coordinatesMin = CoordinatesOps.getCoordinatesMin(latitude, longitude, radius);
        Coordinates coordinatesMax = CoordinatesOps.getCoordinatesMax(latitude, longitude, radius);

        return client.searchForTrees(coordinatesMin, coordinatesMax).stream()
                .filter(data -> data.getName() != null)
                .filter(data -> CoordinatesOps.insideCircle(new Coordinates(latitude, longitude), new Coordinates(data.getLatitude(), data.getLongitude()), radius))
                .collect(Collectors.groupingBy(TreeData::getName, Collectors.counting()));

    }
}
