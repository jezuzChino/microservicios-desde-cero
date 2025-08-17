package academy.digitallab.store.service_product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity //Para definir nuestrsa clase como una entidad
@Table (name = "tbl_categories") //Si el nombre de nuestra tabla es distinta al nombre de la clase, para definir el nombre de nuestra tabla
@Getter
@Setter

public class Category {

    @Id //Para definir la llave primaria
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Valor autoincremental y se selecciona la estrategia de generación
    private Long id;

    private String name;
}
