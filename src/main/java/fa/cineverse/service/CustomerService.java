package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.Customer;
import fa.cineverse.model.User;

public interface CustomerService {

	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	List<Object[]> allHistoryOrderByCustomer(Customer customer);

	Customer findByUser(User user);

	List<Object[]> listEarnPoints(Customer customer);

}
