package academy.digitallab.store.service_product.service.impl;

import academy.digitallab.store.service_product.entity.Product;
import academy.digitallab.store.service_product.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> listAllProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> finByCategoryId(Long categoryId) {
        return List.of();
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        return null;
    }
}
