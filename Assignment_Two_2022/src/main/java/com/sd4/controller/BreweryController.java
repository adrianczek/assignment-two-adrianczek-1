package com.sd4.controller;

import com.sd4.model.Breweries_Geocode;
import com.sd4.model.Brewery;
import com.sd4.service.BreweriesGeocodeService;
import com.sd4.service.BreweryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/breweries")
public class BreweryController {

    @Autowired
    private BreweryService breweryService;
    
    @Autowired
    private BreweriesGeocodeService breweriesGeocodeService;
    
    @GetMapping(value ="", produces = MediaTypes.HAL_JSON_VALUE)
     public ModelAndView getAllBreweries(){
         return new ModelAndView("/viewBreweries", "brewerieslist", breweryService.findAll());
     }
    
    @GetMapping("/breweries/{id}")
    public ResponseEntity<Brewery> getOne(@PathVariable long id) {
       Optional<Brewery> o =  breweryService.findOne(id);
       
       if (!o.isPresent()) 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
         else 
            return ResponseEntity.ok(o.get());
    }
    
    @GetMapping("/breweries/count")
    public long getCount() {
        return breweryService.count();
    }
    
    @DeleteMapping("/breweries/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        breweryService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/breweries/")
    public ResponseEntity add(@RequestBody Brewery a) {
        breweryService.saveBrewery(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/breweries/")
    public ResponseEntity edit(@RequestBody Brewery a) { //the edit method should check if the Brewery object is already in the DB before attempting to save it.
        breweryService.saveBrewery(a);
        return new ResponseEntity(HttpStatus.OK);
    }
    
     @GetMapping(value ="/maps/{id}", produces = MediaType.TEXT_HTML_VALUE)
     public String getMapForBrewery(@PathVariable long id){
         
        Optional<Brewery> brew = breweryService.findOne(id); 
        
        if (!brew.isPresent()){
            return "There has been an ERROR!";
        }
        else{
            Long breweryGeocodeID = brew.get().getId();
            Optional<Breweries_Geocode> geo = breweriesGeocodeService.findOne(breweryGeocodeID);            
            Double latitude = geo.get().getLatitude();
            Double longitude = geo.get().getLongitude();
            
            String name = brew.get().getName();
            String address1 = brew.get().getAddress1();
            String address2 = brew.get().getAddress2();
            String city = brew.get().getCity();
            String state = brew.get().getState();            
            String fullAddress = name + ", " + address1 + " " + address2 + ", " + city + " " + state;
                     
            return "<!DOCTYPE html>" + "<html>" + "<head>"+
            "<title>Brewery Map</title>"+
            "<style>" + "#map {" + "height: 600px;" + "width: 55%;" + "}" + "</style>" +
            "<script type=\"text/javascript\">"+
            "function initMap() {"+
              "const TheBrewery = { lat: " + latitude + ", lng: " + longitude + "};"+
              "const map = new google.maps.Map(document.getElementById(\"map\"), {"+
                "zoom: 16," + "center: TheBrewery," + "});"+
              "const marker = new google.maps.Marker({" + "position: TheBrewery," + "map: map," + "});" + "}"+
              "</script>"+
              "</head>"+
              "<body>"+
                "<h2>" + fullAddress + "</h2>"+
                "<div id=\"map\"></div>"+
                "<script" + " " + "src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyCtf_3BtBuaB3AXGx3bMGOSO0noIRGLxTM&callback=initMap\">" + " " + "async" + "></script>"+
              "</body>" + "</html>";
          }
     }
}