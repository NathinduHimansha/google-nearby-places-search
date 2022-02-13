package com.nathindu.googleplacesretriever.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class PlaceNearBySearchResponseImageResponse {

    @JsonProperty("photo_reference")
    private String photoReference;
}
