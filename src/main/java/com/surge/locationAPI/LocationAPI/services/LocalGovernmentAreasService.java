package com.surge.locationAPI.LocationAPI.services;


import com.surge.locationAPI.LocationAPI.dto.LgaRequest;
import com.surge.locationAPI.LocationAPI.entities.LocalGovernmentArea;
import com.surge.locationAPI.LocationAPI.entities.State;
import com.surge.locationAPI.LocationAPI.exceptions.ExtendedConstants;
import com.surge.locationAPI.LocationAPI.helpers.AppResponse;
import com.surge.locationAPI.LocationAPI.repositories.LocalGovernmentAreaRepository;
import com.surge.locationAPI.LocationAPI.repositories.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocalGovernmentAreasService {

    private final LocalGovernmentAreaRepository localGovernmentAreaRepository;
    private final StateRepository stateRepository;

    @Autowired
    LocalGovernmentAreasService(
            LocalGovernmentAreaRepository localGovernmentAreaRepository,
            StateRepository stateRepository
    ) {
        this.localGovernmentAreaRepository = localGovernmentAreaRepository;
        this.stateRepository = stateRepository;
    }

    public AppResponse getLga() {
        log.info("Inside the get lga {}", LocalGovernmentArea.class);
        AppResponse appResponse = new AppResponse();

        List<LocalGovernmentArea> localGovernmentAreas = localGovernmentAreaRepository.findAll();
        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        log.info("Local Government data gotten {}", localGovernmentAreas);
        appResponse.setData(localGovernmentAreas);

        return appResponse;

    }

    public AppResponse createLga(LgaRequest lgaRequest) {
        log.info("Inside create lga service {} ", LocalGovernmentArea.class);
        log.info("Lga Request {}", lgaRequest.getLgaName());
        log.info("Lga Request {}", lgaRequest.getStateId());


        AppResponse appResponse = new AppResponse();

        State state = stateRepository.findById(lgaRequest.getStateId()).orElse(null);
        log.info("State gotten {}", state);


        if (state != null) {
            LocalGovernmentArea localGovernmentArea =
                    LocalGovernmentArea.builder()
                            .lgaName(lgaRequest.getLgaName())
                            .lgaSlug(lgaRequest.getLgaSlug())
                            .state(state)
                            .build();

            localGovernmentAreaRepository.save(localGovernmentArea);

            appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
            appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        } else {
            appResponse.setMessage("Invalid State ID");
            appResponse.setStatus(ExtendedConstants.ResponseCode.FAILED.getCode());
        }

        return appResponse;
    }

    public AppResponse getLocalGovernmentAreaByStateSlug(String stateSlug) {
        log.info("Inside create lga service {} ", LocalGovernmentArea.class);
        log.info("Lga Request state slug {}", stateSlug);

        AppResponse appResponse = new AppResponse();
        List<LocalGovernmentArea> localGovernmentAreas =
                localGovernmentAreaRepository.getLocalGovernmentAreasByStateSlug(stateSlug);

        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        appResponse.setData(localGovernmentAreas);

        return appResponse;
    }
}
