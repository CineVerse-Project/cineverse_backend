package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.cineverse.model.MovieType;

public interface MovieTypeRepository extends JpaRepository<MovieType, String> {

}
