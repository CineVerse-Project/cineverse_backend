package fa.cineverse.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OneToMany;

import fa.cineverse.model.Movie;

public class MovieTypeDTO {
	private String movieTypeId;

	private String moveTypeName;

	private boolean isDelete;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public String getMovieTypeId() {
		return movieTypeId;
	}

	public void setMovieTypeId(String movieTypeId) {
		this.movieTypeId = movieTypeId;
	}

	public String getMoveTypeName() {
		return moveTypeName;
	}

	public void setMoveTypeName(String moveTypeName) {
		this.moveTypeName = moveTypeName;
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

	public List<Movie> getMovie() {
		return movie;
	}

	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}

	private List<Movie> movie;

	public MovieTypeDTO(String movieTypeId, String moveTypeName, boolean isDelete, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<Movie> movie) {
		super();
		this.movieTypeId = movieTypeId;
		this.moveTypeName = moveTypeName;
		this.isDelete = isDelete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.movie = movie;
	}

	public MovieTypeDTO() {
		super();
	}

}
