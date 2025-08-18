package academy.digitallab.store.service_product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //Para definir nuestrsa clase como una entidad
@Table (name = "tbl_categories") //Si el nombre de nuestra tabla es distinta al nombre de la clase, para definir el nombre de nuestra tabla
@Getter
@Setter
@AllArgsConstructor //Para generar un constructor con todos los atributos de la clase
@NoArgsConstructor //Para generar un constructor sin atributos, es decir, un constructor por defecto
@Builder //Para generar un constructor con los atributos que se le pasen como parámetros, es decir
public class Category {

    @Id //Para definir la llave primaria
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Valor autoincremental y se selecciona la estrategia de generación
    private Long id;

    private String name;
}
