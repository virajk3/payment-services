package com.springmicroservices.paymentservices.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ORDER_ID")
    private long orderId;

    @Column(name = "PAYMENT_MODE")
    private String paymentMode;

    @Column(name = "REFERENCE_NUM")
    private String referenceNum;

    @Column(name = "PAYMENT_DATE")
    private Instant paymentDate;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "AMOUNT")
    private long amount;
}
