package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.model.Coordinates;

public class CoordinatesOps {
    // number of km per degree = ~111km (111.32 in google maps)
    //1km in degree = 1 / 111.32km = 0.0089
    //1m in degree = 0.0089 / 1000 = 0.0000089
    public static final Double METER_IN_DEGREE =  0.0000089;
    public static final Double PI_DIVIDED_180 = 0.018;


    public static Coordinates getCoordinatesMin(double latitude, double longitude, double radius) {
        double coef = radius * METER_IN_DEGREE;
        double latitudeMin = latitude - coef;
        double longitudeMin = longitude - coef/Math.cos(latitude * PI_DIVIDED_180);

        return new Coordinates(latitudeMin, longitudeMin);
    }

    public static Coordinates getCoordinatesMax(double latitude, double longitude, double radius) {
        double coef = radius * METER_IN_DEGREE;

        double latitudeMax = latitude + coef;
        double longitudeMax = longitude + coef/Math.cos(latitude * PI_DIVIDED_180);

        return new Coordinates(latitudeMax, longitudeMax);

    }

    public static double getDistanceBetweenPoints(Coordinates center,  Coordinates coordinates) {

        double R = 6371000; // m
        double dLat = Math.toRadians(center.getLatitude() - coordinates.getLatitude());
        double dLon = Math.toRadians(center.getLongitude() - coordinates.getLongitude());
        double lat1 = Math.toRadians(coordinates.getLatitude());
        double lat2 = Math.toRadians(center.getLatitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return d;
    }

    public static boolean insideCircle(Coordinates center, Coordinates coordinates, double radius) {
        if(getDistanceBetweenPoints(center, coordinates) <= radius) {
            return true;
        } else {
            return false;
        }
    }

}
