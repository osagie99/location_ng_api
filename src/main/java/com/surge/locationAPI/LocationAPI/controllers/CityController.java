package com.surge.locationAPI.LocationAPI.controllers;

import com.surge.locationAPI.LocationAPI.dto.CityRequest;
import com.surge.locationAPI.LocationAPI.helpers.AppResponse;
import com.surge.locationAPI.LocationAPI.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/all")
    public ResponseEntity<AppResponse> getAllCities() {
        AppResponse response = cityService.getListOfCities();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createCity")
    public ResponseEntity<AppResponse> saveCity(@RequestBody @Valid CityRequest cityRequest){
        AppResponse response = cityService.createCities(cityRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/state")
    public ResponseEntity<AppResponse> getLgaByStateSlug(@RequestParam String stateSlug){
        AppResponse response =
                cityService.getCitiesByStateSlug(stateSlug);

        return ResponseEntity.ok(response);
    }

}
