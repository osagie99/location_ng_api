package com.remita.systemspecs.stateandlocations.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    @NotNull(message = "City Name should not be null")
    @Size(min = 3, message = "City name too short")
    @Size(max = 16, message = "City name too long")
    private String cityName;

    @NotNull(message = "City Slug should not be null")
    @Size(min = 3, message = "City Slug too short")
    @Size(max = 16, message = "City Slug too long")
    private String citySlug;

    @NotNull(message = "State id should not be null")
    private Long stateId;
}
