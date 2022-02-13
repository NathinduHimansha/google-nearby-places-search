package com.nathindu.googleplacesretriever.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NearByPlacesDto {

    private String businessStatus;
    private String name;
    private String address;
    private String rating;
    private String photoUrl;

}
