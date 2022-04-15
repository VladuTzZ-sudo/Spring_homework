package com.example.tema_bean.service;

import com.example.tema_bean.model.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public interface IPaymentService {
    Payment createPayment(Double totalValue);
}
