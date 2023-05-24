package fa.cineverse.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.cineverse.dto.ProvinceDTO;
import fa.cineverse.model.Province;
import fa.cineverse.service.ProvinceService;

@RestController
@RequestMapping("/province")
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:16:01 PM
	 * @Return: ResponseEntity<?>
	 * @Note: list all province and check if that list empty
	 */
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<Province> provinceList = provinceService.listAll();
		if (provinceList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(provinceList, HttpStatus.OK);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:14:54 PM
	 * @Return: ResponseEntity<?>
	 * @Note: create a province and check validate input
	 */
	@PostMapping("")
	public ResponseEntity<?> createProvince(@Validated @RequestBody ProvinceDTO provinceDTO,
			BindingResult bindingResult) {
		Province provinceVal = provinceService.findByProvinceName(provinceDTO.getProvinceName());
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		if (provinceVal != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Province province = new Province();
		BeanUtils.copyProperties(provinceDTO, province);
		provinceService.createProvince(province);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * @Author: DatNH20
	 * @Day: May 19, 2023 | @Time: 2:13:48 PM
	 * @Return: ResponseEntity<Province>
	 * @Note: delete by id check id exited or not if it does then delete it(transfer
	 *        to 1)
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Province> deleteProvince(@PathVariable("id") String id) {
		Province province = this.provinceService.get(id);
		if (province == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			if ("".equals(id) || "null".equals(id)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			this.provinceService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * @Author: DatNH20 
	 * @Day: May 19, 2023 | @Time: 4:17:55 PM
	 * @Return: ResponseEntity<?>
	 * @Note: update province moi
	 */
	@PatchMapping(value = "/{provinceId}")
	public ResponseEntity<?> updateProvince(@PathVariable String provinceId,
			@Validated @RequestBody ProvinceDTO provinceDTO, BindingResult bindingResult) {
		Province provinceVal = provinceService.findByProvinceName(provinceDTO.getProvinceName());
		
		if (bindingResult.hasFieldErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		Province provinceOptional = provinceService.get(provinceId);
		if (provinceOptional==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (provinceVal != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		provinceOptional.setProvinceId(provinceId);
		provinceOptional.setProvinceName(provinceDTO.getProvinceName());
		Province provinceUpdate = this.provinceService.updateProvince(provinceOptional);
		return new ResponseEntity<>(provinceUpdate,HttpStatus.OK);
	}

}
