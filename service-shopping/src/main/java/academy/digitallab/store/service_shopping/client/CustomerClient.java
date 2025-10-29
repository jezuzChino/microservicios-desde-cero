package academy.digitallab.store.service_shopping.client;

import academy.digitallab.store.service_shopping.model.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "service-customer", url = "localhost:8081") // Configurar el nombre y la URL del servicio de clientes
@FeignClient(value = "service-customer", fallbackFactory = CustomerClientFallbackFactory.class) // Configurar el nombre del servicio de clientes para usar con Eureka
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);

}
