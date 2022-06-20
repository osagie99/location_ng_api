package com.remita.systemspecs.stateandlocations.repositories;

import com.remita.systemspecs.stateandlocations.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    State getStateByStateSlug(String stateSlug);

}
