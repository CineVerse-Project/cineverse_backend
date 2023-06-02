package fa.cineverse.service;

import java.util.List;

import fa.cineverse.model.Province;

public interface ProvinceService {

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:45:58 AM
	 * @Return: void
	 * @Note: save Province
	 */
	public void save(Province province);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:46:59 AM
	 * @Return: List<Province>
	 * @Note: findAll Province
	 */
	public List<Province> listAll();

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:47:27 AM
	 * @Return: Province
	 * @Note: findById of Province
	 */
	public Province get(String provinceId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 7:48:16 AM
	 * @Return: void
	 * @Note: deleteById of Province
	 */
	public void delete(String provinceId);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:06:11 PM
	 * @Return: void
	 * @Note: update province
	 */
	public Province updateProvince(Province province);

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 3:44:27 PM
	 * @Return: Province
	 * @Note: create province
	 */
	public Province createProvince(Province province);

	
	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:06:03 AM
	 * @Return: Province
	 * @Note: find province by name
	 */
	public Province findByProvinceName(String provinceName);
}
