package fa.cineverse.service.impl;


import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import fa.cineverse.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findById(String movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }
    

    /**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: save
	 */
	@Override
	public void save(Movie movie) {
		this.movieRepository.save(movie);
		
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: list All
	 */
	@Override
	public List<Movie> listAll() {
		// TODO Auto-generated method stub
		return (List<Movie>) movieRepository.findAll();
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: get
	 */
	@Override
	public Movie get(String movieId) {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId).orElse(null);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: delete
	 */
	@Override
	public void delete(String movieId) {
		// TODO Auto-generated method stub
		movieRepository.deleteMovie(movieId);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: update 
	 */
	@Override
	public Movie updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		LocalDateTime nowDateTime =  LocalDateTime.now();
		movie.setUpdatedAt(nowDateTime);
		return this.movieRepository.save(movie);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: create
	 */
	@Override
	public Movie createMovie(Movie movie) {
		LocalDateTime nowDateTime =  LocalDateTime.now();
		movie.setCreatedAt(nowDateTime);
		movie.setUpdatedAt(null);
		return movieRepository.save(movie);
	}

	/**
	 * @Author: DatNH20 
	 * @Day: May 23, 2023 | @Time: 10:25:04 AM
	 * @Return: find by movie name
	 */
	@Override
	public Movie findByMovieName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.findByMovieName(movieName);
	}
}
