package com.surge.locationAPI.LocationAPI.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "state")
@Table(
        name = "local_government_area",
        uniqueConstraints =
        @UniqueConstraint(
                name = "localGovernmentArea_unique",
                columnNames = {
                        "local_government_area_name",
                        "local_government_area_slug"
                }
        )
)
public class LocalGovernmentArea {
    @Id
    @SequenceGenerator(
            name = "localGovernmentArea_sequence",
            sequenceName = "localGovernmentArea_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "localGovernmentArea_sequence"
    )
    private Long localGovernmentAreaId;


    @NotNull
    @NotEmpty
    @Column(
            name = "local_government_area_name",
            nullable = false
    )
    private String lgaName;

    @NotNull
    @NotEmpty
    @Column(
            name = "local_government_area_slug",
            nullable = false
    )
    private String lgaSlug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "stateId")
    @JsonIgnore
    private State state;
}
