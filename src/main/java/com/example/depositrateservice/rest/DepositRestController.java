package com.example.depositrateservice.rest;

import com.example.depositrateservice.rest.resources.DepositRate;
import com.example.depositrateservice.service.DepositRequestsService;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class DepositRestController {

    private final DepositRequestsService depositRequestsService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDeposit(@RequestBody DepositRate depositRate) {
        // Выполняем валидацию депозита и аккаунта
        Validation<String, DepositRate> depositValidation = validateDepositId(depositRate);
        Validation<String, DepositRate> accountValidation = validateAccount(depositRate);

        // Проверка и возвращение ошибок валидации
        ResponseEntity<?> responseEntity = getValidationErrorResponse(depositValidation, "Ошибка: Депозит уже существует", METHOD_NOT_ALLOWED);
        if (responseEntity != null)
            return responseEntity;

        responseEntity = getValidationErrorResponse(accountValidation, "Ошибка: Твой account не уникален", NOT_ACCEPTABLE);
        if (responseEntity != null)
            return responseEntity;

        // Логирование успешного создания депозита
        log.info("Deposit creation successful for id: {}", depositRate.getDepositId());
        return ResponseEntity.ok("Депозит успешно создан.");
    }

    private ResponseEntity<?> getValidationErrorResponse(Validation<String, DepositRate> validation, String errorMessage, HttpStatus status) {
        if (validation.isInvalid()) {
            log.warn("{} validation failed: {}", errorMessage, validation.getError());
            return ResponseEntity.status(status).body(errorMessage);
        }
        return null;
    }

    private Validation<String, DepositRate> validateDepositId(DepositRate depositRate) {
        return getDeposit(depositRate.getDepositId())
                ? Validation.invalid("Депозит уже существует")
                : Validation.valid(depositRate);
    }

    private Validation<String, DepositRate> validateAccount(DepositRate depositRate) {
        return getAcc(depositRate.getAccount())
                ? Validation.invalid("Твой account не уникален")
                : Validation.valid(depositRate);
    }

    private boolean getDeposit(String depoId) {
        return depositRequestsService.findByDepositId(depoId).isPresent();
    }

    private boolean getAcc(String acc) {
        return depositRequestsService.getAccount(acc).isPresent();
    }
}
