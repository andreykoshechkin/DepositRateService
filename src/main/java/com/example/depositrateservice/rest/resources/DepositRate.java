package com.example.depositrateservice.rest.resources;

import com.example.depositrateservice.rest.annotation.CheckUniqueDepositid.CheckUniqueDepositId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositRate {

    @CheckUniqueDepositId
    @NotBlank(message = "Отсутствует параметр depositId")
    @JsonProperty(value = "depositId", required = true)
    private String depositId;


    @NotBlank(message = "Отсутствует inputSource")
    @JsonProperty(value = "inputSource", required = true)
    private String inputSource;


    @NotBlank(message = "Отсутствует параметр depositId")
    @JsonProperty(value = "contractTerms", required = true)
    @DecimalMin(value = "1", message = "Срок размещения депозита не может быть меньше 1 дня")
    @DecimalMax(value = "365", message = "Срок размещения депозита не может быть больше 365 дней")
    private String contractTerms;

    @NotBlank(message = "Отсутствует параметр account")
    @JsonProperty(value = "account", required = true)
    private String account;
}
