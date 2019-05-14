package com.holidu.interview.assignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value("${socrata.cityofnewyork.uri}")
    private String socrataUri;

    public String getSocrataUri() {
        return socrataUri;
    }

    public void setSocrataUri(String socrataUri) {
        this.socrataUri = socrataUri;
    }
}
