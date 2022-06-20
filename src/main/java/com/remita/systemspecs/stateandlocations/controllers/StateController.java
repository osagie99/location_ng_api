package com.remita.systemspecs.stateandlocations.controllers;


import com.remita.systemspecs.stateandlocations.dto.StateRequest;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;
import com.remita.systemspecs.stateandlocations.services.StateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/states")
@Slf4j
public class StateController {
    private final StateServiceImpl stateService;

    @Autowired
    StateController(StateServiceImpl stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/createState")
    public ResponseEntity<AppResponse> createState(@RequestBody @Valid StateRequest stateRequest) {
        log.info("In the State controller");
        AppResponse response = stateService.createState(stateRequest);
        log.info("Creating states");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse> getStates() {
        log.info("Getting all states in the getStates method");
        AppResponse response = stateService.getStates();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    public ResponseEntity<AppResponse> getStateByStateSlug(@RequestParam String stateSlug) {
        log.info("In the get by state slug method");
        AppResponse response = stateService.getStatesByStateSlug(stateSlug);
        return ResponseEntity.ok(response);
    }
}
