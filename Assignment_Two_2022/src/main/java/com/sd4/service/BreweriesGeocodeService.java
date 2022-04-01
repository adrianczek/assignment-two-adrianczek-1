package com.sd4.service;

import com.sd4.model.Breweries_Geocode;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sd4.repository.BreweriesGeocodeRepository;


@Service
public class BreweriesGeocodeService {

    @Autowired
    private BreweriesGeocodeRepository breweriesGeocodeRepo;

    public Optional<Breweries_Geocode> findOne(Long id) {
        return breweriesGeocodeRepo.findById(id);
    }

    public List<Breweries_Geocode> findAll() {
        return (List<Breweries_Geocode>) breweriesGeocodeRepo.findAll();
    }

    public long count() {
        return breweriesGeocodeRepo.count();
    }

    public void deleteByID(long id) {
        breweriesGeocodeRepo.deleteById(id);
    }

    public void saveBreweryGeocode(Breweries_Geocode a) {
        breweriesGeocodeRepo.save(a);
    }  
    
    
    
}//end class