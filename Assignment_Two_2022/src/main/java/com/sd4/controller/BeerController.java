package com.sd4.controller;

import com.sd4.model.Beer;
import com.sd4.model.Brewery;
import com.sd4.service.BeerService;
import com.sd4.service.BreweryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/beers")
public class BeerController extends RepresentationModel<BeerController>{

    @Autowired
    private BeerService beerService;
    
    @Autowired
    private BreweryService breweryService;
     
     @GetMapping(value ="/hateoas/", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<Beer> getAllBeersWithHATEOAS () {
        
        List<Beer> aList = beerService.findAll();
        
        for (final Beer b : aList) {
            long id = b.getId();
            Link selfLink = new Link("http://localhost:8890/beers/hateoas/" + id).withSelfRel();
            b.add(selfLink);
            
            Link anotherLink = new Link("http://localhost:8890/beers/hateoas/beerDetails/" + id).withRel("MoreDetails");
            b.add(anotherLink);
        }
               
        Link link = linkTo(BeerController.class).withSelfRel();
        CollectionModel<Beer> result = CollectionModel.of(aList, link);
        
        return result;        
    } 
     
    @GetMapping(value ="/hateoas/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Beer> getOneBeerWithHATEOAS (@PathVariable long id) {
        Optional<Beer> b = beerService.findOne(id);
        if (!b.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            Link selfLink = new Link("http://localhost:8890/beers/hateoas/").withSelfRel();
            b.get().add(selfLink);
            return ResponseEntity.ok(b.get());
        }
    }  
    
@GetMapping(value = "/hateoas/beerDetails/{id}", produces = MediaTypes.HAL_JSON_VALUE)
     public Map<String,String> beerDetails(@PathVariable long id){
         
         Optional<Beer> b = beerService.findOne(id);
         HashMap<String,String> map = new HashMap<>();

         if (!b.isPresent()){
            map.put("error", "error");
        }
        else{
            String name = b.get().getName();
            String desc = b.get().getDescription();
            Long breweryID = b.get().getBrewery_id();
            
            Optional<Brewery> brew = breweryService.findOne(breweryID);    
            String breweryName = brew.get().getName();   
                         
            map.put("Name: ", name);
            map.put("Description: ", desc);
            map.put("Brewery: ", breweryName);
         }        
         return map;
     }
    
    
    @GetMapping(value ="", produces = MediaTypes.HAL_JSON_VALUE)
     public ModelAndView getAllBeers(){
         return new ModelAndView("/viewBeers", "beerslist", beerService.findAll());
     }
    
    @GetMapping("{id}")
    public ResponseEntity<Beer> getOne(@PathVariable long id) {
       Optional<Beer> o =  beerService.findOne(id);
       
       if (!o.isPresent()) 
            return new ResponseEntity(HttpStatus.NOT_FOUND);
         else 
            return ResponseEntity.ok(o.get());
    }
    
    @GetMapping("/count")
    public long getCount() {
        return beerService.count();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        beerService.deleteByID(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity add(@RequestBody Beer a) {
        beerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/beers/")
    public ResponseEntity edit(@RequestBody Beer a) { //the edit method should check if the Beer object is already in the DB before attempting to save it.
        beerService.saveBeer(a);
        return new ResponseEntity(HttpStatus.OK);
    }    
}