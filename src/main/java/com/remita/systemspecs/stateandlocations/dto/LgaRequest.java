package com.remita.systemspecs.stateandlocations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LgaRequest {
    @NotNull(message = "Lga Name should not be null")
    @Size(min = 3, message = "Lga name too short")
    @Size(max = 16, message = "Lga name too long")
    private String lgaName;

    @NotNull(message = "Lga Name should not be null")
    @Size(min = 3, message = "Lga name too short")
    @Size(max = 16, message = "Lga name too long")
    private String lgaSlug;

    @NotNull(message = "State id should not be null")
    private Long stateId;
}
