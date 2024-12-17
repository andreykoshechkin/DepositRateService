package com.example.depositrateservice.service;

import com.example.depositrateservice.domain.DepositRequestsEntity;
import com.example.depositrateservice.repository.DepositRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepositRequestsService {

    private final DepositRequestsRepository depositRequestsRepository;


    public Optional<DepositRequestsEntity> findByDepositId(String depositId) {
        return depositRequestsRepository.findByDepositId(depositId);
    }

    public List<DepositRequestsEntity> findAll() {
        return depositRequestsRepository.findAll();
    }

    public Optional<DepositRequestsEntity> getAccount(String accountId) {
     return    depositRequestsRepository.findAccountByAccId(accountId);
    }
}
