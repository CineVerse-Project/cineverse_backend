package fa.cineverse.service;

import fa.cineverse.model.Customer;

public interface CustomerService {

	void saveCustomer(Customer customer);

	Customer findByUsername(String username);

	void updateCustomer(Customer customer);


}
