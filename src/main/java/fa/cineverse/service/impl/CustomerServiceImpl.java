package fa.cineverse.service.impl;

import fa.cineverse.model.Customer;
import fa.cineverse.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer findCustomerByUser(String username) {
        return customerRepository.findByUser_Username(username);
    }
}
