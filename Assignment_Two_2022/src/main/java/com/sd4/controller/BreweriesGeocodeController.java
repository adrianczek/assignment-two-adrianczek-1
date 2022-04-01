package com.sd4.controller;

import com.sd4.model.Breweries_Geocode;
import com.sd4.service.BreweriesGeocodeService;;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/brewerygeocode")
public class BreweriesGeocodeController {

    @Autowired
    private BreweriesGeocodeService breweriesGeocodeService;
    
    @GetMapping(value ="", produces = MediaTypes.HAL_JSON_VALUE)
     public ModelAndView getAllBreweriesGeocode(){
         return new ModelAndView("/viewBreweriesGeocode", "breweriesgeocodelist", breweriesGeocodeService.findAll());
     }
    
    @GetMapping("/brewerygeocode/{id}")
    public ResponseEntity<Breweries_Geocode> getOne(@PathVariable long id) {
       Optional<Breweries_Geocode> o =  breweriesGeocodeService.findOne(id);
       
       if (!o.isPresent()) 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
         else 
            return ResponseEntity.ok(o.get());
    }
    
    @GetMapping("/brewerygeocode/count")
    public long getCount() {
        return breweriesGeocodeService.count();
    }
    
    @DeleteMapping("/brewerygeocode/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        breweriesGeocodeService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/brewerygeocode/")
    public ResponseEntity add(@RequestBody Breweries_Geocode a) {
        breweriesGeocodeService.saveBreweryGeocode(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/brewerygeocode/")
    public ResponseEntity edit(@RequestBody Breweries_Geocode a) { //the edit method should check if the Breweries_Geocode object is already in the DB before attempting to save it.
        breweriesGeocodeService.saveBreweryGeocode(a);
        return new ResponseEntity(HttpStatus.OK);
    }
}