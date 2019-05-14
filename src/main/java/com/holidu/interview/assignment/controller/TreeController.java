package com.holidu.interview.assignment.controller;

import com.holidu.interview.assignment.controller.model.SearchTreeRequest;
import com.holidu.interview.assignment.service.TreeService;
import com.holidu.interview.assignment.service.impl.TreeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/trees")
@Api(value = "Street Tree Census API", description = "Query Street Tree Census Data", tags = "Tree")
public class TreeController {
    private static final Logger LOG = LoggerFactory.getLogger(TreeController.class);

    private final TreeService service;

    @Autowired
    public TreeController(TreeService service) {
        this.service = service;
    }

    @PostMapping("/search")
    @ApiOperation(value = "Aggregated search of  trees in a specific circle", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successfully"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Map<String, Long>>  search(@RequestBody @Valid SearchTreeRequest request) {
        LOG.info("Search input params: " + request);
        return ResponseEntity.ok(service.search(request.getLatitude(), request.getLongitude(), request.getRadius()));
    }
}
