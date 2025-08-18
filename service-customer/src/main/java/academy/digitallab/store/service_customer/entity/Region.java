package academy.digitallab.store.service_customer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_regions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
