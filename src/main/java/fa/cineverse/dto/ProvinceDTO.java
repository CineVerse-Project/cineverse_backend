package fa.cineverse.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.UniqueElements;

import fa.cineverse.model.Theater;

public class ProvinceDTO {
	private String provinceId;
	@NotNull
	@Column(unique = true)
	private String provinceName;
	private boolean isDelete;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<Theater> theaters;

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Theater> getTheaters() {
		return theaters;
	}

	public void setTheaters(List<Theater> theaters) {
		this.theaters = theaters;
	}

	public ProvinceDTO(String provinceId, String provinceName, boolean isDelete, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<Theater> theaters) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.theaters = theaters;
	}

	public ProvinceDTO() {
		super();
	}

}
