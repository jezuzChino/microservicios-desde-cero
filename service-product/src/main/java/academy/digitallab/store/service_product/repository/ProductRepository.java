package academy.digitallab.store.service_product.repository;

import academy.digitallab.store.service_product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// extends JpaRepository<Product, Long> para heredar los métodos de acceso a la base de datos
// JpaRepository es una interfaz de Spring Data JPA que proporciona métodos para realizar operaciones CRUD
// Product es la entidad que representa la tabla en la base de datos
// Long es el tipo de dato de la llave primaria de la entidad Product
// No es necesario implementar los métodos, Spring Data JPA lo hace automáticamente
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Metodo para buscar productos por el ID de la categoría
     * Este metodo se generará automáticamente por Spring Data JPA
     * List<Product> findByCategory(Category category); // Este metodo no es necesario,
     * ya que podemos usar el ID de la categoría directamente
     */
    List<Product> findByCategoryId(Long categoryId);
}
