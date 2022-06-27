package com.remita.systemspecs.stateandlocations.services;

import com.remita.systemspecs.stateandlocations.dto.StateRequest;
import com.remita.systemspecs.stateandlocations.dto.StateResponseDto;
import com.remita.systemspecs.stateandlocations.entities.State;
import com.remita.systemspecs.stateandlocations.exceptions.ExtendedConstants;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;
import com.remita.systemspecs.stateandlocations.repositories.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Autowired
    StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public AppResponse createState(StateRequest stateRequest) {
        log.info("State Service impl {}", StateServiceImpl.class);
        AppResponse appResponse = new AppResponse();
        State state = State.builder()
                .stateName(stateRequest.getStateName())
                .stateSlug(stateRequest.getStateSlug())
                .stateCapital(stateRequest.getStateCapital())
                .build();
        log.info("Creating a state in the db ");
        State savedState = stateRepository.save(state);
        log.info("Saved State: {}", savedState);
        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        return appResponse;
    }

    public AppResponse getStates() {
        log.info("In the get State service");
        AppResponse appResponse = new AppResponse();
        List<State> stateList = stateRepository.findAll();
        List<StateResponseDto> stateResponse = new ArrayList<>();

        for (State state : stateList) {
            StateResponseDto stateResponseDto1 = new StateResponseDto();
            stateResponseDto1.setStateCapital(state.getStateCapital());
            stateResponseDto1.setStateName(state.getStateName());
            stateResponseDto1.setStateSlug(state.getStateSlug());
            stateResponse.add(stateResponseDto1);
        }
        log.info("Getting the list of states, {}", stateList);
        log.info("State Response, {}", stateResponse);
        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        appResponse.setData(stateResponse);
        return appResponse;
    }

    public AppResponse getStatesByStateSlug(String stateSlug) {
        log.info("In the get state by state slug service");
        AppResponse appResponse = new AppResponse();
        log.info("State slug: {}", stateSlug);
        State state = stateRepository.getStateByStateSlug(stateSlug);
        log.info("State Found, {}", state);
        if (state == null) {
            appResponse.setMessage(ExtendedConstants.ResponseCode.ENTITY_NOT_FOUND.getDescription() + " FOR STATE WITH SLUG:  " + stateSlug);
            appResponse.setStatus(ExtendedConstants.ResponseCode.ENTITY_NOT_FOUND.getCode());
            return appResponse;
        }
        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        appResponse.setData(state);
        return appResponse;

    }
}
