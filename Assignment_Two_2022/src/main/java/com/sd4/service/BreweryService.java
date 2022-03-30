package com.sd4.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sd4.repository.BreweryRepository;
import com.sd4.model.Brewery;


@Service
public class BreweryService {

    @Autowired
    private BreweryRepository breweryRepo;

    public Optional<Brewery> findOne(Long id) {
        return breweryRepo.findById(id);
    }

    public List<Brewery> findAll() {
        return (List<Brewery>) breweryRepo.findAll();
    }

    public long count() {
        return breweryRepo.count();
    }

    public void deleteByID(long id) {
        breweryRepo.deleteById(id);
    }

    public void saveBrewery(Brewery a) {
        breweryRepo.save(a);
    }  
    
}//end class