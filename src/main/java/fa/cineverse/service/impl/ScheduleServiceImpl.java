package fa.cineverse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Schedule;
import fa.cineverse.repository.ScheduleRepository;
import fa.cineverse.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Page<Schedule> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return scheduleRepository.findAll(pageable);
	}

}
