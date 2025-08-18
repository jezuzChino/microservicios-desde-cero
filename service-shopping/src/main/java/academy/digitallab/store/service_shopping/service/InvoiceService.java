package academy.digitallab.store.service_shopping.service;

import academy.digitallab.store.service_shopping.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);

    public Invoice updateInvoice(Invoice invoice);

    public Invoice deleteInvoice(Invoice invoice);

    public Invoice getInvoice(Long id);
}
