package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
