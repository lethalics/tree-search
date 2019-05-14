package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.model.Coordinates;
import org.junit.Assert;
import org.junit.Test;

public class CoordinatesOpsTest {

    @Test
    public void testGetCoordinatesMin() {
        Double latitudeMin = 39.99555;
        Double longitudeMin = 69.99408091767924;
        Coordinates result = CoordinatesOps.getCoordinatesMin(40, 70, 500);
        Assert.assertEquals(latitudeMin, result.getLatitude());
        Assert.assertEquals(longitudeMin, result.getLongitude());
    }

    @Test
    public void testGetCoordinatesMax() {
        Double latitudeMin = 40.00445;
        Double longitudeMin = 70.00591908232076;
        Coordinates result = CoordinatesOps.getCoordinatesMax(40, 70, 500);
        Assert.assertEquals(latitudeMin, result.getLatitude());
        Assert.assertEquals(longitudeMin, result.getLongitude());
    }

    @Test
    public void testGetDistanceBetweenPoints() {
        Double distance = 494.81742356812083;
        Double result = CoordinatesOps.getDistanceBetweenPoints(new Coordinates(40d, 70d), new Coordinates(40.00445d, 70d));

        Assert.assertEquals(distance, result);
    }

    @Test
    public void testInsideCircle() {
        boolean insideCircle = CoordinatesOps.insideCircle(new Coordinates(40d, 70d), new Coordinates(40.00444d, 70d), 500d);
        Assert.assertTrue(insideCircle);

        boolean outsideCircle = CoordinatesOps.insideCircle(new Coordinates(40d, 70d), new Coordinates(40.00454d, 70d), 500d);

        Assert.assertFalse(outsideCircle);
    }
}