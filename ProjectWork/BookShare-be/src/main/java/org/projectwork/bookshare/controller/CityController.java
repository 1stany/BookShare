package org.projectwork.bookshare.controller;

import org.projectwork.bookshare.dto.CityDto;
import org.projectwork.bookshare.model.services.implementations.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/market")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getCities() {
        return ResponseEntity.ok(cityService.getCities());
    }
}
