package academy.digitallab.store.service_shopping.repository;

import academy.digitallab.store.service_shopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

    // Additional query methods can be defined here if needed
}
