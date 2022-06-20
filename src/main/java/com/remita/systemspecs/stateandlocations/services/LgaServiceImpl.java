package com.remita.systemspecs.stateandlocations.services;

import com.remita.systemspecs.stateandlocations.dto.LgaRequest;
import com.remita.systemspecs.stateandlocations.entities.LocalGovernmentArea;
import com.remita.systemspecs.stateandlocations.entities.State;
import com.remita.systemspecs.stateandlocations.exceptions.ExtendedConstants;
import com.remita.systemspecs.stateandlocations.helpers.AppResponse;
import com.remita.systemspecs.stateandlocations.repositories.LocalGovernmentAreaRepository;
import com.remita.systemspecs.stateandlocations.repositories.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LgaServiceImpl implements LocalGovernmentAreasService {

    private final LocalGovernmentAreaRepository localGovernmentAreaRepository;
    private final StateRepository stateRepository;

    @Autowired
    LgaServiceImpl(
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
        Optional<State> state = stateRepository.findById(lgaRequest.getStateId());
        log.info("State gotten {}", state);

        if (state.isEmpty()) {
            log.info("State is empty");
            appResponse.setMessage("Invalid State ID");
            appResponse.setStatus(ExtendedConstants.ResponseCode.FAILED.getCode());
        } else {
            LocalGovernmentArea localGovernmentArea =
                    LocalGovernmentArea.builder()
                            .lgaName(lgaRequest.getLgaName())
                            .lgaSlug(lgaRequest.getLgaSlug())
                            .state(state.get())
                            .build();
            localGovernmentAreaRepository.save(localGovernmentArea);
            appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
            appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
        }
        return appResponse;
    }

//    public AppResponse getLocalGovernmentAreaByStateSlug(String stateSlug) {
//        log.info("Inside create lga service {} ", LocalGovernmentArea.class);
//        log.info("Lga Request state slug {}", stateSlug);
//        AppResponse appResponse = new AppResponse();
//        List<LocalGovernmentArea> localGovernmentAreas =
//                localGovernmentAreaRepository.getLocalGovernmentAreasByStateSlug(stateSlug);
//        appResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getDescription());
//        appResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getCode());
//        appResponse.setData(localGovernmentAreas);
//        return appResponse;
//    }
}
