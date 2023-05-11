package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Theater;

public interface TheaterRepository extends JpaRepository<Theater, String> {

}
