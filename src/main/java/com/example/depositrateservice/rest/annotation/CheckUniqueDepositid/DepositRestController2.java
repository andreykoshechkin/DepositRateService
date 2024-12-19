package com.example.depositrateservice.rest.annotation.CheckUniqueDepositid;

import com.example.depositrateservice.rest.resources.DepositRate;
import com.example.depositrateservice.service.DepositValidationService;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class DepositRestController2 {

    private final DepositValidationService depositValidationService;

    @PostMapping(value = "/getAmount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDeposit(@RequestBody DepositRate depositRate) {

        double percentage = 0.05; // Пример процента (5%)

        return Try.of(() -> {
                    depositValidationService.validateRequestId(depositRate.getDepositId());
                    double result = calculatePercentage(depositRate.getAmount(), percentage);
                    return ResponseEntity.ok("Депозит успешно создан. Процент от суммы: " + result);
                })
                .recover(RuntimeException.class, err -> ResponseEntity.badRequest().body("Ошибка: " + err.getMessage()))
                .get();
    }

    private double calculatePercentage(double amount, double percentage) {
        return amount * percentage;
    }
}
