package com.example.depositrateservice.rest.annotation.CheckUniqueDepositid;

import com.example.depositrateservice.domain.DepositRequestsEntity;
import com.example.depositrateservice.rest.resources.DepositRate;
import com.example.depositrateservice.service.DepositRequestsService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepositIdValidator implements ConstraintValidator<CheckUniqueDepositId, String> {

    private final DepositRequestsService depositRequestsService;

    @Override
    public void initialize(CheckUniqueDepositId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.warn("Происходит валидация depositId");

        if(depositRequestsService.findByDepositId(s).isPresent()){
            return false ;
        }
        return true;
    }


}
