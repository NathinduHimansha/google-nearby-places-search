package com.nathindu.googleplacesretriever.service.impl;

import com.nathindu.googleplacesretriever.common.Constants;
import com.nathindu.googleplacesretriever.domain.PlaceNearBySearchResponseResult;
import com.nathindu.googleplacesretriever.dto.NearByPlacesDto;
import com.nathindu.googleplacesretriever.dto.Response;
import com.nathindu.googleplacesretriever.domain.PlaceNearBySearchResponse;
import com.nathindu.googleplacesretriever.service.PlacesRetrievingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class PlacesRetrievingServiceImpl implements PlacesRetrievingService {

    @Value("${google.cloud.services.api.key}")
    private String googleCloudServicesApiKey;

    @Value("${google.maps.nearby.places.api}")
    private String nearbyPlacesApi;

    @Value("${google.maps.place.image.api}")
    private String mapsPlaceImageApi;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Response getNearbyPlaces(String type, String longitude, String latitude, String radius) {
        try {
            log.info("PlacesRetrievingServiceImpl - getNearbyPlaces called()");

            String url = String.format("%s/json?location=%s,%s&radius=%s&type=%s&key=%s",
                    nearbyPlacesApi, longitude, latitude, radius, type, googleCloudServicesApiKey);
            List<NearByPlacesDto> nearByPlacesDto = convertResponseIntoNearByPlacesDto(
                    restTemplate.getForObject(url, PlaceNearBySearchResponse.class));

            log.info("PlacesRetrievingServiceImpl - getNearbyPlaces completed()");
            return new Response(Constants.SUCCESS, "SUCCESSFULLY RETRIEVED NEARBY PLACES",
                    nearByPlacesDto, null);

        } catch (Exception exception) {
            log.error(String.format("PlacesRetrievingServiceImpl - getNearbyPlaces exception: %s", exception.getMessage()));
            return new Response(Constants.ERROR, "RETRIEVING NEARBY PLACES UNSUCCESSFUL",
                    null, null);
        }
    }

    private List<NearByPlacesDto> convertResponseIntoNearByPlacesDto(PlaceNearBySearchResponse nearByPlacesResponse) {
        List<NearByPlacesDto> nearByPlacesDtoList = new ArrayList<>();
        if (nearByPlacesResponse != null && nearByPlacesResponse.getResults() != null &&
                !nearByPlacesResponse.getResults().isEmpty()) {

            for (PlaceNearBySearchResponseResult result : nearByPlacesResponse.getResults()) {
                NearByPlacesDto nearByPlacesDto = new NearByPlacesDto();
                nearByPlacesDto.setBusinessStatus(result.getBusinessStatus());
                nearByPlacesDto.setName(result.getName());
                nearByPlacesDto.setAddress(result.getVicinity());
                nearByPlacesDto.setRating(result.getRating()!= null ?result.getRating():"N/A");
                nearByPlacesDto.setPhotoUrl(result.getPhotos() != null ?
                        String.format("%s?photoreference=%s&sensor=false&maxheight=300&key=%s&maxwidth=250"
                                , mapsPlaceImageApi, result.getPhotos().get(0).getPhotoReference(),
                                googleCloudServicesApiKey) : result.getIcon());

                nearByPlacesDtoList.add(nearByPlacesDto);
            }
            return nearByPlacesDtoList;
        } else {
            return Collections.emptyList();
        }
    }
}
