package academy.digitallab.store.service_shopping.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product {

    private Long id;

    private String name;

    private String description;

    private Double stock;

    private Double price;

    private String status;

    private Date createAt;

    private Category category;
}
