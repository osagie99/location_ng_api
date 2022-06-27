package com.remita.systemspecs.stateandlocations.controllers;

import com.remita.systemspecs.stateandlocations.dto.CityRequest;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;
import com.remita.systemspecs.stateandlocations.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cities")
@Slf4j
public class CityController {

    private final CityService cityService;

    CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/createCity")
    public ResponseEntity<AppResponse> saveCity(@RequestBody @Valid CityRequest cityRequest) {
        log.info("In the city controller");
        AppResponse response = cityService.createCities(cityRequest);
        return ResponseEntity.ok(response);
    }


}
