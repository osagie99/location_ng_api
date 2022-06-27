package com.remita.systemspecs.stateandlocations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateRequest {


    @NotNull(message = "State Name should not be null")
    @NotEmpty(message = "State Name should not be empty")
    @Size(min = 3, message = "State Name too short")
    private  String stateName;

    @NotNull(message = "State Slug should not be null")
    @NotEmpty(message = "State slug should not be empty")
    @Size(min = 3, message = "State slug too short")
    private  String stateSlug;

    @NotNull(message = "State Capital should not be null")
    @NotEmpty(message = "State Capital should not be empty")
    @Size(min = 3, message = "State Capital too short")
    private String stateCapital;
}
