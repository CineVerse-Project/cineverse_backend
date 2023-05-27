package fa.cineverse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Customer;
import fa.cineverse.model.User;
import fa.cineverse.repository.CustomerRepository;
import fa.cineverse.service.CustomerService;

/**
 * @author HuuNQ
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	/**
	 * @author HuuNQ
	 *
	 */
	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}
	
	/**
	 * @author HuuNQ
	 *
	 */
	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}
	/**
	 * @author HuuNQ
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> allHistoryOrderByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.listHistoryOrderByUsername(customer.getCustomerId());
	}

	/**
	 * @author HuuNQ
	 *
	 */
	@Override
	public Customer findByUser(User user) {
		// TODO Auto-generated method stub
		return customerRepository.findByUser(user);
	}



	@Override
	public List<Object[]> listEarnPoints(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.listEarnPointsByUsername(customer.getCustomerId());
	}

}
