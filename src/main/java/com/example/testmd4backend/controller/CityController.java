package com.example.testmd4backend.controller;

import com.example.testmd4backend.model.City;
import com.example.testmd4backend.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cities")
@CrossOrigin("*")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping("")
    public ResponseEntity<Iterable<City>> findAllCity() {
        List<City> cities = (List<City>) cityService.findAll();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(cityOptional.get().getId());
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<City> viewCity(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        return cityOptional.map(city -> new ResponseEntity<>(city, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
