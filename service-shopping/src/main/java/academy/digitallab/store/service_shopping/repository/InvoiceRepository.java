package academy.digitallab.store.service_shopping.repository;

import academy.digitallab.store.service_shopping.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // busca por customerId
    // y devuelve una lista de facturas
    List<Invoice> findByCustomerId(Long customerId );

    // busca por numero de factura
    // y devuelve una factura
    Invoice findByNumberInvoice(String numberInvoice);
}
