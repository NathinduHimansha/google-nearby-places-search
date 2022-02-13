package com.nathindu.googleplacesretriever.service;

import com.nathindu.googleplacesretriever.dto.Response;
import org.springframework.stereotype.Service;

@Service
public interface PlacesRetrievingService {

    Response getNearbyPlaces(String type, String longitude, String latitude, String radius);
}
