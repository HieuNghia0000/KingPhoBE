package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.InvoiceDTO;
import org.application.kingphobe.model.Invoice;
import org.application.kingphobe.model.Order;
import org.application.kingphobe.repository.InvoiceRepository;
import org.application.kingphobe.repository.OrderRepository;
import org.application.kingphobe.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        mapDtoToInvoice(invoiceDTO, invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> updateInvoice(Integer id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> existingInvoice = invoiceRepository.findById(id);

        if (existingInvoice.isPresent()) {
            Invoice invoice = existingInvoice.get();
            mapDtoToInvoice(invoiceDTO, invoice);
            return Optional.of(invoiceRepository.save(invoice));
        }

        return Optional.empty();
    }

    @Override
    public void deleteInvoice(int id) {
        invoiceRepository.deleteById(id);
    }

    private void mapDtoToInvoice(InvoiceDTO invoiceDTO, Invoice invoice) {
        invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setStatus(invoiceDTO.getStatus());

        // Fetch the order entity and set it to the invoice
        Order order = orderRepository.findById(invoiceDTO.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        invoice.setOrder(order);
    }
}
