package fa.cineverse.service.impl;

import fa.cineverse.model.Movie;
import fa.cineverse.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findById(String movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }
}
