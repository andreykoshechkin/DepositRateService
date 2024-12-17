package com.example.depositrateservice.repository;

import com.example.depositrateservice.domain.DepositRequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DepositRequestsRepository extends JpaRepository<DepositRequestsEntity, Long> {

    @Query("select en from DepositRequestsEntity en where en.depositId =:depositId")
    Optional<DepositRequestsEntity> findByDepositId(@Param("depositId") String depositId);

    @Query("select en.accountId from DepositRequestsEntity en where en.accountId =:account")
    Optional<DepositRequestsEntity> findAccountByAccId(@Param("account") String account);
}
