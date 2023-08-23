package com.springmicroservices.paymentservices.controller;

import com.springmicroservices.paymentservices.entity.TransactionDetails;
import com.springmicroservices.paymentservices.model.PaymentResponse;
import com.springmicroservices.paymentservices.model.TransactionDetailsRequest;
import com.springmicroservices.paymentservices.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
//@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody TransactionDetailsRequest transactionDetailsRequest){
       Long paymentId =  paymentService.doPayment(transactionDetailsRequest);

       return new ResponseEntity<>(paymentId, HttpStatus.OK);
    }

    @GetMapping("/getPaymentDetails/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("orderId") long orderId){

        return new ResponseEntity<>(paymentService.getPaymentDetailsByOID(orderId),HttpStatus.OK);
    }
}
