package com.example.depositrateservice.rest.resources;

import com.example.depositrateservice.rest.annotation.CheckUniqueDepositid.CheckUniqueDepositId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest {

    @NotBlank(message = "Отсутствует параметр requestId")
    @JsonProperty(value = "requestId", required = true)
    private String requestId;


    @NotBlank(message = "Отсутствует documentId")
    @JsonProperty(value = "documentId", required = true)
    private Integer documentId;

}
