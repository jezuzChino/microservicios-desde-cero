package academy.digitallab.store.service_shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_invoices")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // de uno a muchos, cascade all para que se guarde en cascada
    private List<InvoiceItem> items;

    private String state;

//    @Transient
//    private Customer customer;

    public Invoice(){
        items = new ArrayList<>();
    }

    @PrePersist // indica que se ejecuta antes de guardar en la base de datos
    public void prePersist() {
        this.createAt = new Date();
    }
}
