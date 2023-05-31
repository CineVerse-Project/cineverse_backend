package fa.cineverse.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.cineverse.model.Province;
import fa.cineverse.repository.ProvinceRepository;
import fa.cineverse.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:59:45 AM
	 * @Note: Service save a province
	 */
	@Override
	public void save(Province province) {
		this.provinceRepository.save(province);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:11 AM
	 * @Note: Service list all province
	 */
	@Override
	public List<Province> listAll() {
		return (List<Province>) provinceRepository.findAllActiveList();
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:34 AM
	 * @Note: Service get a province by id
	 */
	@Override
	public Province get(String provinceId) {
		return provinceRepository.findById(provinceId).orElse(null);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 8:00:47 AM
	 * @Note: Service delete a province by id
	 */
	@Override
	public void delete(String provinceId) {
		provinceRepository.deleteProvince(provinceId);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:53 PM
	 * @Note: update province
	 */
	@Override
	public Province updateProvince(Province province) {
		LocalDateTime now = LocalDateTime.now();
		province.setUpdatedAt(now);
		return this.provinceRepository.save(province);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:27 AM
	 * @Note: create province
	 */
	@Override
	public Province createProvince(Province province) {
		LocalDateTime now = LocalDateTime.now();
		province.setCreatedAt(now);
		province.setUpdatedAt(null);
		return provinceRepository.save(province);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 22, 2023 | @Time: 2:22:35 AM
	 * @Note: find province by name
	 */
	@Override
	public Province findByProvinceName(String provinceName) {
		return provinceRepository.findByProvinceName(provinceName);
	}

}
