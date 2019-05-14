package com.holidu.interview.assignment.service;

import java.util.Map;

public interface TreeService {
    Map<String, Long> search(double latitude, double longitude, double radius);
}
