package com.so.controller;

import com.so.domain.Payment;
import com.so.dto.PaymentRequest;
import com.so.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    private PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity pay(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = new Payment(paymentRequest.getBidCode(), paymentRequest.getAmount());
        try {
            return new ResponseEntity(paymentService.pay(payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
