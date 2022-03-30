package com.sd4.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sd4.repository.BeerRepository;
import com.sd4.model.Beer;


@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepo;

    public Optional<Beer> findOne(Long id) {
        return beerRepo.findById(id);
    }

    public List<Beer> findAll() {
        return (List<Beer>) beerRepo.findAll();
    }

    public long count() {
        return beerRepo.count();
    }

    public void deleteByID(long id) {
        beerRepo.deleteById(id);
    }

    public void saveBeer(Beer a) {
        beerRepo.save(a);
    }  
    
}//end class