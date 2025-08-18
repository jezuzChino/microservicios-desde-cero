package academy.digitallab.store.service_product.service;

import academy.digitallab.store.service_product.entity.Category;
import academy.digitallab.store.service_product.entity.Product;
import academy.digitallab.store.service_product.repository.ProductRepository;
import academy.digitallab.store.service_product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest // Indica que es una prueba de integración que carga el contexto de Spring
public class ProductServiceImplMockTest {

    @Mock // Indica que este campo será un mock creado por Mockito
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach // Este método se ejecuta antes de cada prueba
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Inicializa los mocks anotados con @Mock
        // Aquí se inicializa el servicio con el repositorio mockeado
        productService = new ProductServiceImpl(productRepository); // Inyecta el mock en el servicio

        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build()) //builder sirve para crear objetos de forma mas sencilla y build sirve para construir el objeto final
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
    }
}
