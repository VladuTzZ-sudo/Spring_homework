package com.example.tema_bean.service;

import com.example.tema_bean.model.Payment;
import com.example.tema_bean.model.PaymentMethod;
import com.example.tema_bean.repository.PaymentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Log
@Qualifier("cardPaymentService_dev")
public class CardPaymentService_dev implements IPaymentService{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Double totalValue) {
        log.info("[DEV] - createPayment(" + totalValue + ")" + " - PaymentMethod=CARD");
        return paymentRepository.save(Payment.builder().paymentMethod(PaymentMethod.CARD).totalValue(totalValue).build());
    }
}
