package fa.cineverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fa.cineverse.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, String> {

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:12:36 PM
	 * @Return: void
	 * @Note: ko xoa nhung chuyen trang thai is-delete tu 0 sang 1
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE province SET is_delete = 1 WHERE province_id= :id", nativeQuery = true)
	void deleteProvince(@Param("id") String id);

	/**
	 * @Author: DatNH20 
	 * @Day: May 22, 2023 | @Time: 2:16:47 AM
	 * @Return: Province
	 * @Note: find province by name
	 */
	Province findByProvinceName(String provinceName);
}
