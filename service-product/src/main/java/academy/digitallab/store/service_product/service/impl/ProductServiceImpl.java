package academy.digitallab.store.service_product.service.impl;

import academy.digitallab.store.service_product.entity.Product;
import academy.digitallab.store.service_product.repository.ProductRepository;
import academy.digitallab.store.service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired // Autowired to inject the ProductRepository bean
    // Note: In a real application, you would typically use @Service annotation on this class
    // and @Autowired on the repository field.
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        // product es el producto que se va a actualizar
        // productDB es el producto que se va a buscar en la base de datos
        // si no se encuentra, se retorna null
        // si se encuentra, se actualizan los campos y se guarda en la base de datos
        Product productDB = getProduct(product.getId());
        if(null == productDB){
            return null; //Product no found
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());

        return productRepository.save(productDB);// Se guarda el producto de la BD con la actualización
        // y se retorna el producto actualizado
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if(null == productDB){
            return null; //Product no found
        }

        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> finByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {

        Product productDB = getProduct(id);
        if(null == productDB){
            return null; //Product no found
        }
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }
}
