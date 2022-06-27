package com.remita.systemspecs.stateandlocations.repositories;

import com.remita.systemspecs.stateandlocations.entities.LocalGovernmentArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalGovernmentAreaRepository extends JpaRepository<LocalGovernmentArea, Long> {

    @Query("" +
            "select localGovernmentArea " +
            "from LocalGovernmentArea localGovernmentArea " +
            "where localGovernmentArea.state.stateSlug =:stateSlug " +
            ""
    )
    List<LocalGovernmentArea> getLocalGovernmentAreasByStateSlug(String stateSlug);
}
