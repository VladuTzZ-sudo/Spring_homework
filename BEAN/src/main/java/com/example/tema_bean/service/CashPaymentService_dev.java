package com.example.tema_bean.service;

import com.example.tema_bean.model.Payment;
import com.example.tema_bean.model.PaymentMethod;
import com.example.tema_bean.repository.PaymentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Log
@Profile("dev")
@Qualifier("cashPaymentService_dev")
public class CashPaymentService_dev implements IPaymentService{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Double totalValue) {
        log.info("[DEV] - createPayment(" + totalValue + ")" + " - PaymentMethod=CASH");
        return paymentRepository.save(Payment.builder().paymentMethod(PaymentMethod.CASH).totalValue(totalValue).build());
    }
}