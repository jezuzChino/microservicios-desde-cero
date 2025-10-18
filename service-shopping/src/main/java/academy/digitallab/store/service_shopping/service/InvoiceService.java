package academy.digitallab.store.service_shopping.service;

import academy.digitallab.store.service_shopping.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findInvoiceAll();

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(Invoice invoice);

    Invoice deleteInvoice(Invoice invoice);

    Invoice getInvoice(Long id);
}
