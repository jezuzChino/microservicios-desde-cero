package academy.digitallab.store.service_shopping.client;

import academy.digitallab.store.service_shopping.model.customer.Customer;
import academy.digitallab.store.service_shopping.model.customer.Region;
//import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerClientFallbackFactory implements FallbackFactory<CustomerClient> {
    @Override
    public CustomerClient create(Throwable cause) {
        return new CustomerClient() {
            @Override
            public ResponseEntity<Customer> getCustomer(Long id) {
                log.error("ENTRO AL FALLBACK FACTORY. Excepción: {}", cause.toString());
                Region defaultRegion = new Region();
                Customer defaultCustomer = Customer.builder()
                        .id(1L)
                        .numberID("none")
                        .firstName("none")
                        .lastName("none")
                        .email("none")
                        .photoUrl("none")
                        .region(defaultRegion)
                        .state("none")
                        .build();
                return ResponseEntity.ok(defaultCustomer);
            }
        };
    }
}

