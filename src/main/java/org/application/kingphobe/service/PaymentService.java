package org.application.kingphobe.service;

import org.application.kingphobe.dto.PaymentDTO;
import org.application.kingphobe.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayment();

    Optional<Payment> getPaymentById(int id);

    Payment createPayment(PaymentDTO payment);

    Optional<Payment> updatePayment(Integer id, PaymentDTO payment);

    void deletePayment(int id);
}
