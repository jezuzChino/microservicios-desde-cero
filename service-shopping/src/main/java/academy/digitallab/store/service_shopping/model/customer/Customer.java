package academy.digitallab.store.service_shopping.model.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Long id;

    private String numberID;

    private String firstName;

    private String lastName;

    private String email;

    private String photoUrl;

    private Region region;

    private String state;
}
