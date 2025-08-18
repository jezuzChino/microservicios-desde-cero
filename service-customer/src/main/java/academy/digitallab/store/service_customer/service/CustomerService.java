package academy.digitallab.store.service_customer.service;

import academy.digitallab.store.service_customer.entity.Customer;
import academy.digitallab.store.service_customer.entity.Region;

import java.util.List;

public interface CustomerService {
    List<Customer> findCustomerAll();
    List<Customer> findCustomersByRegion(Region region);

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteCustomer(Customer customer);
    Customer getCustomer(Long id);
}
