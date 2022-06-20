package com.remita.systemspecs.stateandlocations.services;


import com.remita.systemspecs.stateandlocations.dto.CityRequest;
import com.remita.systemspecs.stateandlocations.entities.City;
import com.remita.systemspecs.stateandlocations.entities.State;
import com.remita.systemspecs.stateandlocations.exceptions.ExtendedConstants;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;
import com.remita.systemspecs.stateandlocations.repositories.CityRepository;
import com.remita.systemspecs.stateandlocations.repositories.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CityServiceImpl implements CityService {


    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    CityServiceImpl(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public AppResponse createCities(CityRequest cityRequest) {
        log.info("In the create city service...");
        AppResponse appResponse = new AppResponse();

        Optional<State> state = stateRepository.findById(cityRequest.getStateId());

        if (state.isEmpty()) {
            log.info("state is empty, with {} as state id", cityRequest.getStateId());
            appResponse.setMessage("Invalid State ID");
            appResponse.setStatus(ExtendedConstants.ResponseCode.FAILED.getCode());
        } else {
            City city =
                    City.builder()
                            .cityName(cityRequest.getCityName())
                            .citySlug(cityRequest.getCitySlug())
                            .state(state.get())
                            .build();
            log.info("Saving City in the db");
            cityRepository.save(city);
            appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
            appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        }
        return appResponse;

    }
}
