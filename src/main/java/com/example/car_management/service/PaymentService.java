package com.example.car_management.service;

import com.example.car_management.entity.Payment;
import com.example.car_management.response.PaymentResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(Payment payment);

    PaymentResponse patchPayment(Long id, Payment changes);

    List<PaymentResponse> getAllPayments();

    PaymentResponse getPaymentById(Long id);

    PaymentResponse updatePayment(Long id, Payment payment);

    void deletePayment(Long id);
}