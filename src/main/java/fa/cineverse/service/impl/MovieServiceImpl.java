package fa.cineverse.service.impl;


import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


//	@Override
//	public List<Movie> findAllMovieIsShowing() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Movie> findAllMoviePremiereSoon() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Movie> findAllMoviePremiered() {
//		// TODO Auto-generated method stub
//		return null;
//	}

    @Override
    public Movie findById(String movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }
}
