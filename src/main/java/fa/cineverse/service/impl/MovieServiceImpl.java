package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import fa.cineverse.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieRepository movieRepository;

	@Override
	public void save(Movie movie) {
		this.movieRepository.save(movie);
		
	}

	@Override
	public List<Movie> listAll() {
		// TODO Auto-generated method stub
		return (List<Movie>) movieRepository.findAll();
	}

	@Override
	public Movie get(String movieId) {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId).orElse(null);
	}

	@Override
	public void delete(String movieId) {
		// TODO Auto-generated method stub
		movieRepository.deleteMovie(movieId);
	}

	@Override
	public Movie updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		LocalDateTime nowDateTime =  LocalDateTime.now();
		movie.setUpdatedAt(nowDateTime);
		return this.movieRepository.save(movie);
	}

	@Override
	public Movie createMovie(Movie movie) {
		LocalDateTime nowDateTime =  LocalDateTime.now();
		movie.setCreatedAt(nowDateTime);
		movie.setUpdatedAt(null);
		return movieRepository.save(movie);
	}

	@Override
	public Movie findByMovieName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.findByMovieName(movieName);
	}

}
