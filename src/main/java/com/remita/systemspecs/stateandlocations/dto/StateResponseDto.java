package com.remita.systemspecs.stateandlocations.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateResponseDto {

    private  String stateName;

    private  String stateSlug;

    private String stateCapital;
}
