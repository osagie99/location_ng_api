package com.remita.systemspecs.stateandlocations.controllers;

import com.remita.systemspecs.stateandlocations.dto.LgaRequest;
import com.remita.systemspecs.stateandlocations.services.LocalGovernmentAreasService;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/lgas")
@Slf4j
public class LgaController {

    public final LocalGovernmentAreasService localGovernmentAreasService;

    @Autowired
    LgaController(LocalGovernmentAreasService localGovernmentAreasService) {
        this.localGovernmentAreasService = localGovernmentAreasService;
    }

    @PostMapping("/createLga")
    public ResponseEntity<AppResponse> createLga(@RequestBody @Valid LgaRequest lgaRequest) {
        log.info("Creating a local government in the createLga method with request : {}", lgaRequest);
        AppResponse response = localGovernmentAreasService.createLga(lgaRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<AppResponse> getAllStates() {
        log.info("Getting all lgas in the getAllStates Method");
        AppResponse response = localGovernmentAreasService.getLga();
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/state")
//    public ResponseEntity<AppResponse> getLgaByStateSlug(@RequestParam String stateSlug){
//        log.info("Getting all lgas in the getLgaByStateSlug Method");
//        AppResponse response =
//                localGovernmentAreasService.getLocalGovernmentAreaByStateSlug(stateSlug);
//        return ResponseEntity.ok(response);
//    }
}
