package academy.digitallab.store.service_product.controller;

import academy.digitallab.store.service_product.entity.Product;
import academy.digitallab.store.service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Anotación que indica que esta clase es un controlador REST
@RequestMapping (value = "/products")// Ruta base para las peticiones a este controlador
public class ProductController {

    @Autowired // Inyeccion del servicio de productos
    private ProductService productService;

    @RequestMapping // Anotación que indica que este metodo maneja peticiones GET
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> products = productService.listAllProducts();
        if (products.isEmpty()){
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si no hay productos
        }
        return ResponseEntity.ok(products); // Retorna 200 OK con la lista de productos
    }
}
