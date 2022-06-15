package com.surge.locationAPI.LocationAPI.controllers;

import com.surge.locationAPI.LocationAPI.dto.LgaRequest;
import com.surge.locationAPI.LocationAPI.exceptions.NotFoundException;
import com.surge.locationAPI.LocationAPI.helpers.AppResponse;
import com.surge.locationAPI.LocationAPI.services.LocalGovernmentAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/lgas")
public class LgaController {

    public final LocalGovernmentAreasService localGovernmentAreasService;

    @Autowired
    LgaController(LocalGovernmentAreasService localGovernmentAreasService) {
        this.localGovernmentAreasService = localGovernmentAreasService;
    }

    @PostMapping("/createLga")
    public ResponseEntity<AppResponse> createLga(@RequestBody @Valid LgaRequest lgaRequest) {

        AppResponse response = localGovernmentAreasService.createLga(lgaRequest);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<AppResponse> getAllStates() {
        AppResponse response = localGovernmentAreasService.getLga();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    public ResponseEntity<AppResponse> getLgaByStateSlug(@RequestParam String stateSlug){
        AppResponse response =
                localGovernmentAreasService.getLocalGovernmentAreaByStateSlug(stateSlug);

        return ResponseEntity.ok(response);
    }
}
