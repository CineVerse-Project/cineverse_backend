package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Theater;
import fa.cineverse.repository.TheaterRepository;
import fa.cineverse.service.TheaterService;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:49 AM
	 * @Note: save
	 */
	@Override
	public void save(Theater theater) {
		this.theaterRepository.save(theater);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:54 AM
	 * @Note: list all
	 */
	@Override
	public List<Theater> listAll() {
		return (List<Theater>) theaterRepository.findAll();
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:23:00 AM
	 * @Note: get by id
	 */
	@Override
	public Theater get(String theaterId) {
		return theaterRepository.findById(theaterId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:23:07 AM
	 * @Note: delete (turn status 0 -> 1)
	 */
	@Override
	public void delete(String theaterId) {
		theaterRepository.deleteTheater(theaterId);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:23:28 AM
	 * @Note: update
	 */
	@Override
	public Theater updateTheater(Theater theater) {
		LocalDateTime now = LocalDateTime.now();
		theater.setUpdatedAt(now);
		return this.theaterRepository.save(theater);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:23:38 AM
	 * @Note: create
	 */
	@Override
	public Theater createTheater(Theater theater) {
		LocalDateTime now = LocalDateTime.now();
		theater.setCreatedAt(now);
		theater.setUpdatedAt(null);
		return theaterRepository.save(theater);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:23:53 AM
	 * @Note: find by its name
	 */
	@Override
	public Theater findByTheaterName(String theaterName) {
		return theaterRepository.findByTheaterName(theaterName);
	}
}
