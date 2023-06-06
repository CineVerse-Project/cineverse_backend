package fa.cineverse.service.impl;


import fa.cineverse.model.Customer;
import fa.cineverse.model.User;
import fa.cineverse.repository.CustomerRepository;
import fa.cineverse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HuuNQ
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/**
	 * @author HuuNQ
	 *
	 */
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer findCustomerByUser(String username) {
        return customerRepository.findByUser_Username(username);
    }

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
	 *s
	 */
	@Override
	public Customer findByUser(User user) {
		// TODO Auto-generated method stub
		return customerRepository.findByUser(user);
	}
//
//	@Override
//	public List<Object[]> listEarnPoints(Customer customer) {
//		// TODO Auto-generated method stub
//		return customerRepository.listEarnPointsByUsername(customer.getCustomerId());
//	}

}
