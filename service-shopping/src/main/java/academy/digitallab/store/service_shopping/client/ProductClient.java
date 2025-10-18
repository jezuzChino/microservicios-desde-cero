package academy.digitallab.store.service_shopping.client;

import academy.digitallab.store.service_shopping.model.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "service-product", url = "localhost:8082") // Configurar el nombre y la URL del servicio de productos
@FeignClient(value = "service-product")//, url = "http://localhost:8091") // Configurar el nombre del servicio de productos para usar con Eureka
//@RequestMapping(value = "/products")// Ruta base para los endpoints del servicio de productos // Ya no es necesario porque ya está definido en el controlador del servicio de productos
public interface ProductClient {

    @GetMapping(value = "/products/{id}") // Ruta para obtener un producto por su ID
    ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @GetMapping(value = "/products/{id}/stock") // Ruta para actualizar el stock de un producto
    ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(name = "quantity", required = true) Double quantity);
}
