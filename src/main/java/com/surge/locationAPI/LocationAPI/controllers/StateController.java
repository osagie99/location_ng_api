package com.surge.locationAPI.LocationAPI.controllers;


import com.surge.locationAPI.LocationAPI.dto.StateRequest;
import com.surge.locationAPI.LocationAPI.helpers.AppResponse;
import com.surge.locationAPI.LocationAPI.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/states")
public class StateController {
    private final StateService stateService;

    @Autowired
    StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/createState")
    public ResponseEntity<AppResponse> createState(@RequestBody @Valid StateRequest stateRequest) {

        AppResponse response = stateService.createState(stateRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse> getStates() {

        AppResponse response = stateService.getStates();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    public ResponseEntity<AppResponse> getStates(@RequestParam String stateSlug) {

        AppResponse response = stateService.getStatesByStateSlug(stateSlug);

        return ResponseEntity.ok(response);
    }
}
