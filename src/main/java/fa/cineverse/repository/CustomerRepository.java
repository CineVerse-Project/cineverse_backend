package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String username);

}
