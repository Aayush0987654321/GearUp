package com.example.car_management.service;

import com.example.car_management.entity.Payment;
import com.example.car_management.repository.PaymentRepository;
import com.example.car_management.response.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse createPayment(Payment payment) {

        Payment savedPayment = paymentRepository.save(payment);

        return convertToResponse(savedPayment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found with ID: " + id));

        return convertToResponse(payment);
    }

    @Override
    public PaymentResponse updatePayment(Long id, Payment payment) {

        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found with ID: " + id));

        existingPayment.setBooking(payment.getBooking());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setPaymentStatus(payment.getPaymentStatus());
        existingPayment.setTransactionId(payment.getTransactionId());
        existingPayment.setPaymentDate(payment.getPaymentDate());

        Payment updatedPayment = paymentRepository.save(existingPayment);

        return convertToResponse(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Payment not found with ID: " + id));

        paymentRepository.delete(payment);
    }

    @Override
    public PaymentResponse patchPayment(Long id, Payment changes) {

        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (changes.getAmount() != null) {
            existingPayment.setAmount(changes.getAmount());
        }

        if (changes.getPaymentMethod() != null) {
            existingPayment.setPaymentMethod(changes.getPaymentMethod());
        }

        if (changes.getPaymentStatus() != null) {
            existingPayment.setPaymentStatus(changes.getPaymentStatus());
        }

        if (changes.getTransactionId() != null) {
            existingPayment.setTransactionId(changes.getTransactionId());
        }

        if (changes.getPaymentDate() != null) {
            existingPayment.setPaymentDate(changes.getPaymentDate());
        }

        Payment saved = paymentRepository.save(existingPayment);

        return convertToResponse(saved);
    }

    private PaymentResponse convertToResponse(Payment payment) {

        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getBooking().getBookingId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getTransactionId(),
                payment.getPaymentDate());
    }
}