package com.nathindu.googleplacesretriever.service.impl;

import com.nathindu.googleplacesretriever.common.Constants;
import com.nathindu.googleplacesretriever.dto.Response;
import com.nathindu.googleplacesretriever.domain.PlaceNearBySearchResponse;
import com.nathindu.googleplacesretriever.service.PlacesRetrievingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PlacesRetrievingServiceImpl implements PlacesRetrievingService {

    @Value("${google.cloud.services.api.key}")
    private String googleCloudServicesApiKey;

    @Value("${google.maps.nearby.places.api}")
    private String nearbyPlacesApi;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Response getNearbyPlaces(String type, String longitude, String latitude, String radius) {
        try {
            log.info("PlacesRetrievingServiceImpl - getNearbyPlaces called()");

            String url = String.format("%s/json?location=%s,%s&radius=%s&type=%s&key=%s",
                    nearbyPlacesApi, longitude, latitude, radius, type, googleCloudServicesApiKey);
            PlaceNearBySearchResponse nearByPlacesResponse =
                    restTemplate.getForObject(url, PlaceNearBySearchResponse.class);

            log.info("PlacesRetrievingServiceImpl - getNearbyPlaces completed()");
            return new Response(Constants.SUCCESS, "SUCCESSFULLY RETRIEVED NEARBY PLACES",
                    nearByPlacesResponse, null);

        } catch (Exception exception) {
            log.error(String.format("PlacesRetrievingServiceImpl - getNearbyPlaces exception: %s", exception.getMessage()));
            return new Response(Constants.ERROR, "RETRIEVING NEARBY PLACES UNSUCCESSFUL",
                    null, null);
        }
    }
}
