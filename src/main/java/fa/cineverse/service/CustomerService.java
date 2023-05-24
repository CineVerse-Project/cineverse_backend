package fa.cineverse.service;

import fa.cineverse.model.Customer;

public interface CustomerService {

    Customer findCustomerByUser(String username);
}
