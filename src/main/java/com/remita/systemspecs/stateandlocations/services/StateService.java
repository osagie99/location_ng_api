package com.remita.systemspecs.stateandlocations.services;

import com.remita.systemspecs.stateandlocations.dto.StateRequest;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;

public interface StateService {

    public AppResponse createState(StateRequest stateRequest);
    public AppResponse getStates();
    public AppResponse getStatesByStateSlug(String stateSlug);
}
