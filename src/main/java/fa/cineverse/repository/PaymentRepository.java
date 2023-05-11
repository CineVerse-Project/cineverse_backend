package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
