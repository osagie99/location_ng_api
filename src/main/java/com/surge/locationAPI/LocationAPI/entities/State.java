package com.surge.locationAPI.LocationAPI.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "state",
        uniqueConstraints =
        @UniqueConstraint(
                name = "state_unique",
                columnNames = {
                        "state_name",
                        "state_capital",
                        "state_slug"
                }
        )
)
public class State {

    @Id
    @SequenceGenerator(
            name = "state_sequence",
            sequenceName = "state_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "state_sequence"
    )
    private Long stateId;

    @Column(
            name = "state_name",
            nullable = false
    )
    private String stateName;

    @Column(
            name = "state_capital",
            nullable = false
    )
    private String stateCapital;

    @Column(
            name = "state_slug",
            nullable = false
    )
    private String stateSlug;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<LocalGovernmentArea> localGovernmentAreas;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<City> city;


}
