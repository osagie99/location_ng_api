package com.surge.locationAPI.LocationAPI.repositories;


import com.surge.locationAPI.LocationAPI.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    @Query("select city from City city where city.state.stateSlug =:stateSlug")
    public List<City> getCitiesByStateSlug(String stateSlug);
}
