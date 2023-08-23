package com.springmicroservices.paymentservices.service;

import com.springmicroservices.paymentservices.model.PaymentResponse;
import com.springmicroservices.paymentservices.model.TransactionDetailsRequest;

public interface PaymentService {
    Long doPayment(TransactionDetailsRequest transactionDetailsRequest);

    PaymentResponse getPaymentDetailsByOID(long orderId);
}
