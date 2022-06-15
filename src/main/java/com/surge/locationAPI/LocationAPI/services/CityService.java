package com.surge.locationAPI.LocationAPI.services;


import com.surge.locationAPI.LocationAPI.dto.CityRequest;
import com.surge.locationAPI.LocationAPI.entities.City;
import com.surge.locationAPI.LocationAPI.entities.State;
import com.surge.locationAPI.LocationAPI.exceptions.ExtendedConstants;
import com.surge.locationAPI.LocationAPI.helpers.AppResponse;
import com.surge.locationAPI.LocationAPI.repositories.CityRepository;
import com.surge.locationAPI.LocationAPI.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    CityService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public AppResponse getListOfCities() {
        AppResponse appResponse = new AppResponse();

        List<City> cities = cityRepository.findAll();
        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        appResponse.setData(cities);

        return appResponse;
    }

    public AppResponse getCitiesByStateSlug(String stateSlug) {
        AppResponse appResponse = new AppResponse();

        List<City> cities = cityRepository.getCitiesByStateSlug(stateSlug);
        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        appResponse.setData(cities);

        return appResponse;
    }

    public AppResponse createCities(CityRequest cityRequest) {
        AppResponse appResponse = new AppResponse();

        State state = stateRepository.findById(cityRequest.getStateId()).orElse(null);

        if (state != null) {
            City city =
                    City.builder()
                            .cityName(cityRequest.getCityName())
                            .citySlug(cityRequest.getCitySlug())
                            .state(state)
                            .build();

            cityRepository.save(city);
            appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
            appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        } else {
            appResponse.setMessage("Invalid State ID");
            appResponse.setStatus(ExtendedConstants.ResponseCode.FAILED.getCode());
        }

        return appResponse;

    }
}
