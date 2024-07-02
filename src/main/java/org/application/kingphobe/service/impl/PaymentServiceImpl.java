package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.PaymentDTO;
import org.application.kingphobe.model.Order;
import org.application.kingphobe.model.Payment;
import org.application.kingphobe.repository.OrderRepository;
import org.application.kingphobe.repository.PaymentRepository;
import org.application.kingphobe.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(int id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        mapDtoToPayment(paymentDTO, payment);
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> updatePayment(Integer id, PaymentDTO paymentDTO) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);

        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            mapDtoToPayment(paymentDTO, payment);
            return Optional.of(paymentRepository.save(payment));
        }

        return Optional.empty();
    }

    @Override
    public void deletePayment(int id) {
        paymentRepository.deleteById(id);
    }

    private void mapDtoToPayment(PaymentDTO paymentDTO, Payment payment) {
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(Date.from(paymentDTO.getPaymentDate().atZone(ZoneId.systemDefault()).toInstant()));

        // Fetch the order entity and set it to the payment
        Order order = orderRepository.findById(paymentDTO.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        payment.setOrder(order);
    }
}
