package com.example.testmd4backend.service.city;

import com.example.testmd4backend.model.City;
import com.example.testmd4backend.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        cityRepository.deleteById(id);
    }
}
