package com.example.depositrateservice.rest.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(NON_NULL)
public class DocumentResponse {

    @JsonProperty("documentName")
    private String documentName;

    @JsonProperty("documentLink")
    private String documentLink;

    @JsonProperty("requestId")
    private Integer requestId;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("errorStatus")
    private String status;
}
