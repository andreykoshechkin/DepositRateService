package com.example.depositrateservice.service;

import org.springframework.stereotype.Service;

@Service
public class DepositValidationService {

    public void validateRequestId(String reqId) {
        if ("132".equals(reqId)) {
            throw new RuntimeException("Request ID уже существует!");
        }
    }

    public void validateAnotherCondition(String value) {
        if ("invalid".equals(value)) {
            throw new RuntimeException("Некорректное значение!");
        }
    }
}
