package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.MovieType;
import fa.cineverse.repository.MovieTypeRepository;
import fa.cineverse.service.MovieTypeService;

@Service
public class MovieTypeServiceImpl implements MovieTypeService {

	@Autowired
	private MovieTypeRepository	movieTypeRepository;

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:26:42 AM
	 * @Note: save
	 */
	@Override
	public void save(MovieType movieType) {
		this.movieTypeRepository.save(movieType);
		
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:26:49 AM
	 * @Note: list all
	 */
	@Override
	public List<MovieType> listAll() {
		
		return (List<MovieType>) movieTypeRepository.findAll();
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:26:57 AM
	 * @Note: get by id
	 */
	@Override
	public MovieType get(String movieTypeId) {
	
		return movieTypeRepository.findById(movieTypeId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:27:02 AM
	 * @Note: delete 
	 */
	@Override
	public void delete(String movieTypeId) {
		movieTypeRepository.deleteMovieType(movieTypeId);
		
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:27:11 AM
	 * @Note: update
	 */
	@Override
	public MovieType updateMovieType(MovieType movieType) {
		LocalDateTime now =  LocalDateTime.now();
		movieType.setUpdatedAt(now);
		return this.movieTypeRepository.save(movieType);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:27:31 AM
	 * @Note: create
	 */
	@Override
	public MovieType createMovieType(MovieType movieType) {
		LocalDateTime nowDateTime = LocalDateTime.now();
		movieType.setCreatedAt(nowDateTime);
		movieType.setUpdatedAt(null);
		return movieTypeRepository.save(movieType);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 7:27:36 AM
	 * @Note: find by name
	 */
	@Override
	public MovieType findByMovieTypeName(String movieTypeName) {
		
		return movieTypeRepository.findByMoveTypeName(movieTypeName);
	}

}
