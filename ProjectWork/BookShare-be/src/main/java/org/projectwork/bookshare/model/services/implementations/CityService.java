package org.projectwork.bookshare.model.services.implementations;

import org.projectwork.bookshare.dto.CityDto;
import org.projectwork.bookshare.model.repositories.abstractions.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityDto> getCities() {
        return cityRepository.findAll().stream().map(CityDto::new).toList();
    }
}