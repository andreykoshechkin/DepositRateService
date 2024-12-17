package com.example.depositrateservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "deposit_requests")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepositRequestsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deposit_id")
    private String depositId;

    @Column(name = "account")
    private String accountId;

    @Column(name = "create_dt")
    private LocalDateTime depositDate;

    @Column(name = "amount")
    private String amount;

    @Column(name = "curtype")
    private String curType;
}
