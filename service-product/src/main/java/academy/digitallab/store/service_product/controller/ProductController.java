package academy.digitallab.store.service_product.controller;

import academy.digitallab.store.service_product.entity.Category;
import academy.digitallab.store.service_product.entity.Product;
import academy.digitallab.store.service_product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) { // Anotación @Valid para validar el objeto Product y BindingResult para capturar los errores de validación
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreated = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated); // Retorna 201 Created
    }

    @PutMapping(value = "/{id}") // Anotación que indica que este metodo maneja peticiones PUT
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id); // Aseguramos que el ID del producto a actualizar sea el correcto
        Product productDB = productService.updateProduct(product);
        if (productDB == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encontró el producto
        }
        return ResponseEntity.ok(productDB); // Retorna 200 OK con el producto actualizado
    }

    @DeleteMapping(value = "/{id}") // Anotación que indica que este metodo maneja peticiones DELETE
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product productDB = productService.deleteProduct(id);
        if (productDB == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encontró el producto
        }
        return ResponseEntity.ok(productDB); // Retorna 200 OK con el producto eliminado
    }

    @GetMapping(value = "/{id}/stock") // Ruta para actualizar el stock de un producto
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(name = "quantity", required = true) Double quantity) {
        Product productDB = productService.updateStock(id, quantity);
        if (productDB == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encontró el producto
        }
        return ResponseEntity.ok(productDB); // Retorna 200 OK con el producto actualizado
    }

    private String formatMessage(BindingResult result) { // Método para formatear los mensajes de error de validación
        List<Map<String, String>> errors = result.getFieldErrors().stream() // Obtener los errores de los campos
                .map(err -> {
                    Map<String,String> error = new HashMap<>(); // Crear un mapa para cada error
                    error.put(err.getField(), err.getDefaultMessage()); // Agregar el nombre del campo y el mensaje de error al mapa
                    return error;
                }).collect(Collectors.toList()); // Colectar todos los mapas en una lista

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors) // Asignar la lista de errores al objeto ErrorMessage
                .build();
        // Convertir el objeto ErrorMessage a JSON
        ObjectMapper objectMapper = new ObjectMapper(); // Usamos ObjectMapper para convertir el objeto a JSON
        String jsonErrorMessage = "";
        try {
            jsonErrorMessage = objectMapper.writeValueAsString(errorMessage); // Convertimos el objeto ErrorMessage a JSON
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción en caso de error al convertir a JSON
        }
        return jsonErrorMessage; // Retornamos el mensaje de error en formato JSON
    }
}
