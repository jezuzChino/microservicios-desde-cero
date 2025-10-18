package academy.digitallab.store.service_shopping.client;

import academy.digitallab.store.service_shopping.model.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "service-customer", url = "localhost:8081") // Configurar el nombre y la URL del servicio de clientes
@FeignClient(value = "service-customer")//, url = "http://localhost:8092") // Configurar el nombre del servicio de clientes para usar con Eureka
//@RequestMapping("/customers") // Ruta base para los endpoints del servicio de clientes // Ya no es necesario porque ya está definido en el controlador del servicio de clientes
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);

}
