package com.nathindu.googleplacesretriever.Controller;

import com.nathindu.googleplacesretriever.dto.Response;
import com.nathindu.googleplacesretriever.service.PlacesRetrievingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("api/v1/data")
public class MainController {

    @Autowired
    PlacesRetrievingService placesRetrievingService;

    @GetMapping("/places/nearby")
    public ResponseEntity<Response> getNearbyPlaces(@RequestParam String type, @RequestParam String longitude,
                                                    @RequestParam String latitude, @RequestParam String radius) {
        return new ResponseEntity<>(placesRetrievingService.getNearbyPlaces(type, longitude, latitude, radius), HttpStatus.OK);

    }
}
