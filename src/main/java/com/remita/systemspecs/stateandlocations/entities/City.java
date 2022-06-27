package com.remita.systemspecs.stateandlocations.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "state")
@Table(
        name = "city",
        uniqueConstraints =
        @UniqueConstraint(
                name = "city_unique",
                columnNames = {
                        "city_name",
                        "city_slug"
                }
        )
)
public class City {

    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )
    private Long cityId;

    @Column(
            name = "city_name",
            nullable = false
    )
    private String cityName;

    @Column(
            name = "city_slug",
            nullable = false
    )
    private String citySlug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "stateId")
    @JsonIgnore
    private State state;
}
