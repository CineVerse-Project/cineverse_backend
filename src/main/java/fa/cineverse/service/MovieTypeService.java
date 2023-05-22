package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.MovieType;


public interface MovieTypeService {
	
	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:45:58 AM
	 * @Return: void
	 * @Note: save movie
	 */
	public void save(MovieType movieType);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:46:59 AM
	 * @Return: List<Province>
	 * @Note: findAll movie type
	 */
	public List<MovieType> listAll();

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:47:27 AM
	 * @Return: Province
	 * @Note: findById of Province
	 */
	public MovieType get(String movieTypeId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:48:16 AM
	 * @Return: void
	 * @Note: deleteById of movietype
	 */
	public void delete(String movieTypeId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:11 PM
	 * @Return: void
	 * @Note: update movie type
	 */
	public MovieType updateMovieType(MovieType movieType);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:44:27 PM
	 * @Return: Province
	 * @Note: create movie type
	 */
	public MovieType createMovieType(MovieType movieType);

	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:06:03 AM
	 * @Return: Province
	 * @Note: find movie type by name
	 */
	public MovieType findByMovieTypeName(String movieTypeName);
}
