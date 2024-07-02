package org.application.kingphobe.service;

import org.application.kingphobe.dto.InvoiceDTO;
import org.application.kingphobe.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> getAllInvoice();

    Optional<Invoice> getInvoiceById(int id);

    Invoice createInvoice(InvoiceDTO invoiceDTO);

    Optional<Invoice> updateInvoice(Integer id, InvoiceDTO invoiceDTO);

    void deleteInvoice(int id);
}
