package fa.cineverse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Customer;
import fa.cineverse.repository.CustomerRepository;
import fa.cineverse.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

	@Override
	public Customer findByUsername(String username) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(username);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

}
