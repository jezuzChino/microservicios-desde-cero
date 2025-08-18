package academy.digitallab.store.service_product.controller;

import academy.digitallab.store.service_product.entity.Category;
import academy.digitallab.store.service_product.entity.Product;
import academy.digitallab.store.service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController // Anotación que indica que esta clase es un controlador REST
@RequestMapping (value = "/products")// Ruta base para las peticiones a este controlador
public class ProductController {

    @Autowired // Inyeccion del servicio de productos
    private ProductService productService;

    @RequestMapping // Anotación que indica que este metodo maneja peticiones GET
    public ResponseEntity<List<Product>> listProducts(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Product> products = new ArrayList<>(); // Inicializa una lista vacía de productos
        if (categoryId == null){ // Si no se proporciona un ID de categoría, se listan todos los productos
            products = productService.listAllProducts();
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 No Content si no hay productos
            }
        }else {
            products = productService.finByCategoryId(Category.builder().id(categoryId).build().getId());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no hay productos para la categoría
            }
        }
        return ResponseEntity.ok(products); // Retorna 200 OK con la lista de productos
    }

    @GetMapping(value = "/{id}") // Anotación que indica que este metodo maneja peticiones GET con un ID de producto
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) { // Anotación @PathVariable para extraer el ID del producto de la URL
        Product product = productService.getProduct(id);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productCreated = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated); // Retorna 201 Created
    }
}
