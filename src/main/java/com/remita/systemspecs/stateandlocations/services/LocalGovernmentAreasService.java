package com.remita.systemspecs.stateandlocations.services;


import com.remita.systemspecs.stateandlocations.dto.LgaRequest;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;

public interface LocalGovernmentAreasService {
    public AppResponse getLga();

    public AppResponse createLga(LgaRequest lgaRequest);

//    public AppResponse getLocalGovernmentAreaByStateSlug(String stateSlug);
}
