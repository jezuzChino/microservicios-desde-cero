package academy.digitallab.store.service_product.repository;

import academy.digitallab.store.service_product.entity.Category;
import academy.digitallab.store.service_product.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@DataJpaTest // Descorador que indica que se testea un JPA
@Sql(scripts = "/data.sql") // Carga el script SQL antes de ejecutar las pruebas
public class ProductRepositoryMockTest {

    @Autowired // este decorador inyecta el repositorio de productos
    private ProductRepository productRepository; // Se inyecta ProductRepository

    //@PersistenceContext // Este decorador inyecta el EntityManager, que es la interfaz principal para interactuar con la base de datos
    //private EntityManager entityManager; // Se inyecta EntityManager para acceder a la base de datos

    @Test // Decorador que indica que este es un método de prueba
    // Este método se ejecutará como una prueba unitaria
    public void wheFindByCategory_thenReturnListProduct() {

        // Aquí se implementaría el test para verificar que al buscar por categoría,
        // se devuelven los productos correspondientes.
        // Este metodo debería contener la lógica de prueba y las aserciones necesarias.
        // Ejemplo de uso:
        // List<Product> products = productRepository.findByCategoryId(1L);
        // Assertions.assertThat(products).isNotEmpty();

        //Category category = entityManager.find(Category.class, 3L); // Obtener la categoría existente

        Product product = Product.builder()
                .name("computer")
                .category(Category.builder().id(3L).build())
                .description("High performance computer")
                .stock(10.0)
                .price(1240.99)
                .status("CREATED")
                .createAt(new Date())
                .build();
        productRepository.save(product); // Guardar el producto en la base de datos

        List<Product> founds = productRepository.findByCategoryId(3L); // Buscar productos por ID de categoría (total 3 productos de esa categoria)

        //Assert to find categoryId 3
        Assert.notNull(founds, "The founds list should not be null");
        Assertions.assertEquals(3, founds.size(), "There should be one product found for category ID 3");
        founds.forEach(p -> System.out.println("Found product: " + p.getName() + " - Category ID: " + p.getCategory().getId()));
        Assertions.assertEquals("computer", founds.get(2).getName(), "The product name should be 'computer'");
        Assertions.assertEquals(3L, founds.get(0).getCategory().getId(), "The category ID should be 3");

    }
}
