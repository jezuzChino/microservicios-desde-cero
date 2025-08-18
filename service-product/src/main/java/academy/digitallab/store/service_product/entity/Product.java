package academy.digitallab.store.service_product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity //Para definir nuestra clase como una entidad
@Table (name = "tbl_products") //Si el nombre de nuestra tabla es distinta al nombre de la clase, para definir el nombre de nuestra tabla
@Getter
@Setter
@AllArgsConstructor //Para generar un constructor con todos los atributos de la clase
@NoArgsConstructor //Para generar un constructor sin atributos, es decir, un constructor por defecto
@Builder //Para generar un constructor con los atributos que se le pasen como parámetros, es decir, un constructor por builder
public class Product {

    @Id //Para definir la llave primaria
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Valor autoincremental y se selecciona la estrategia de generación
    private Long id;

    private String name;

    private String description;

    private Double stock;

    private Double price;

    private String status;

    @Column (name = "create_at") //Si el nombre del atributo de la entidad es igual al nombre de la BD no se ocupa está decoración
    @Temporal(TemporalType.TIMESTAMP) //Para trabajar con fechas (DATE = fecha, Time = hora, TIMESTAMP= fecha y hora)
    private Date createAt;

    //Definir la estrategia -- fetch EAGER carga todos los valores de todas las categorias, LAZY solo cargara en el momento que se requieran.
    @ManyToOne(fetch = FetchType.LAZY) //Mapear la entindad productos con la entidad categoria y es una relación de "Muchos a uno, Una categoria tiene muchos productos"
    @JoinColumn(name = "category_id") //Mapeo de las columnas
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Para evitar problemas de serialización con Hibernate
    private Category category;
}
