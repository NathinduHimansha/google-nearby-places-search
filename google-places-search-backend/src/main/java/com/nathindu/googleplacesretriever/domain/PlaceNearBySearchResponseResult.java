package com.nathindu.googleplacesretriever.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class PlaceNearBySearchResponseResult {

    @JsonProperty("business_status")
    private String businessStatus;

    @JsonProperty("name")
    private String name;

    @JsonProperty("vicinity")
    private String vicinity;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty( "photos")
    private List<PlaceNearBySearchResponseImageResponse> photos;
}
