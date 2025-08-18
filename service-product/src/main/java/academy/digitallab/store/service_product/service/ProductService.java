package academy.digitallab.store.service_product.service;

import academy.digitallab.store.service_product.entity.Product;

import java.util.List;

// Definir los metodos que se utilizarán para manejar productos
// Este es un ejemplo de interfaz de servicio para productos en una tienda digital
// que permite listar, obtener, crear, actualizar y eliminar productos,
// así como buscar productos por categoría y actualizar el stock de un producto.
public interface ProductService {
    List<Product> listAllProducts();
    Product getProduct(Long id);

    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> finByCategoryId(Long categoryId);
    Product updateStock(Long id, Double quantity);
}
