package academy.digitallab.store.service_shopping.impl;

import academy.digitallab.store.service_shopping.client.CustomerClient;
import academy.digitallab.store.service_shopping.client.ProductClient;
import academy.digitallab.store.service_shopping.entity.Invoice;
import academy.digitallab.store.service_shopping.entity.InvoiceItem;
import academy.digitallab.store.service_shopping.model.customer.Customer;
import academy.digitallab.store.service_shopping.model.product.Product;
import academy.digitallab.store.service_shopping.repository.InvoiceItemsRepository;
import academy.digitallab.store.service_shopping.repository.InvoiceRepository;
import academy.digitallab.store.service_shopping.service.InvoiceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    CustomerClient customerClient; // Feign Client

    @Autowired
    ProductClient productClient; // Feign Client

    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }


    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach( invoiceItem -> {
            productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });

        return invoiceDB;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    @CircuitBreaker(name = "service-customer") // Elimina fallbackMethod, ahora el fallback es manejado por Feign
    public Invoice getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice != null) {
            var cus = invoice.getCustomerId();
            log.info("EL VALOR DEL ID DEL CLIENTE ES:" + cus);
            try {
                Customer customer = customerClient.getCustomer(cus).getBody();
                invoice.setCustomer(customer);
            } catch (Exception e) {
                log.error("Error al obtener el cliente desde CustomerClient. Se asigna cliente por defecto. Excepción: {}", e.toString());
                Customer defaultCustomer = Customer.builder()
                        .id(1L)
                        .numberID("none")
                        .firstName("none")
                        .lastName("none")
                        .email("none")
                        .photoUrl("none")
                        .region(null)
                        .state("none")
                        .build();
                invoice.setCustomer(defaultCustomer);
            }
            List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }
        return invoice;
    }
}
