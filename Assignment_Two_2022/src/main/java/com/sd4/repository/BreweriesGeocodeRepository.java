package com.sd4.repository;

import com.sd4.model.Breweries_Geocode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweriesGeocodeRepository extends CrudRepository<Breweries_Geocode, Long> {
    
 
}