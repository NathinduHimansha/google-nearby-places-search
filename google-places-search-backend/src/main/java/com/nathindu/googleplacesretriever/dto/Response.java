package com.nathindu.googleplacesretriever.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String status;
    private String message;
    private Object data;
    private List<Object> dataList;

}
