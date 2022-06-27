package com.remita.systemspecs.stateandlocations.services;


import com.remita.systemspecs.stateandlocations.dto.CityRequest;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;

public interface CityService {

    public AppResponse createCities(CityRequest cityRequest);
}
