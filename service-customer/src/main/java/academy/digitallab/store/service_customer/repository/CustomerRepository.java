package academy.digitallab.store.service_customer.repository;

import academy.digitallab.store.service_customer.entity.Customer;
import academy.digitallab.store.service_customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNumberID(String numberID);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByRegion(Region region);

}
