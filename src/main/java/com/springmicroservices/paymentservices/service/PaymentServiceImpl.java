package com.springmicroservices.paymentservices.service;

import com.springmicroservices.paymentservices.entity.TransactionDetails;
import com.springmicroservices.paymentservices.model.PaymentMode;
import com.springmicroservices.paymentservices.model.PaymentResponse;
import com.springmicroservices.paymentservices.model.TransactionDetailsRequest;
import com.springmicroservices.paymentservices.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public Long doPayment(TransactionDetailsRequest transactionDetailsRequest) {
        log.info("Recording payment details {} : ",transactionDetailsRequest);
        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .amount(transactionDetailsRequest.getAmount())
                        .paymentMode(transactionDetailsRequest.getPaymentMode().name())
                        .referenceNum(transactionDetailsRequest.getReferenceNum())
                        .paymentStatus("SUCCESS")
                        .paymentDate(Instant.now())
                        .orderId(transactionDetailsRequest.getOrderId())
                        .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction completed with id: {}",transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOID(long orderId) {
        log.info("Getting payment details for the order id");

        TransactionDetails transactionDetails =
                transactionDetailsRepository.findByOrderId(orderId);


        PaymentResponse paymentResponse =
                PaymentResponse.builder()
                        .paymentId(transactionDetails.getId())
                        .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                        .amount(transactionDetails.getAmount())
                        .status(transactionDetails.getPaymentStatus())
                        .orderId(transactionDetails.getOrderId())
                        .build();
        return paymentResponse;
    }
}
