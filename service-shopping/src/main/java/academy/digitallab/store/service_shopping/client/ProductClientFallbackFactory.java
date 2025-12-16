package academy.digitallab.store.service_shopping.client;

import academy.digitallab.store.service_shopping.model.product.Category;
import academy.digitallab.store.service_shopping.model.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {
    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {
            @Override
            public ResponseEntity<Product> getProduct(Long id) {
                log.error("ENTRO AL FALLBACK FACTORY. Excepción: {}", cause.toString());
                Category defaultCategory = new Category();
                Product defaultProduct = Product.builder()
                        .id(1L)
                        .name("none")
                        .description("none")
                        .price(0.0)
                        .stock(0.0)
                        .status("none")
                        .build();
                return ResponseEntity.ok(defaultProduct);
            }

            @Override
            public ResponseEntity<Product> updateStockProduct(Long id, Double quantity) {
                return null;
            }
        };
    }
}

