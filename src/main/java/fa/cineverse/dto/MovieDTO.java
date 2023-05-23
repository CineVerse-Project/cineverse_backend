package fa.cineverse.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import fa.cineverse.model.MovieType;
import fa.cineverse.model.Schedule;

public class MovieDTO {

	private String movieId;

	private String movieName;

	private LocalDate startDate;

	private LocalDate endDate;

	private String actor;

	private String description;

	private String director;

	private String filmStudio;

	private Float duration;

	private String imageUrl;

	private String trailerUrl;

	private String version;

	private boolean isDelete;

	private int status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private MovieType movieType;

	private List<Schedule> schedules;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getFilmStudio() {
		return filmStudio;
	}

	public void setFilmStudio(String filmStudio) {
		this.filmStudio = filmStudio;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public MovieType getMovieType() {
		return movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public MovieDTO(String movieId, String movieName, LocalDate startDate, LocalDate endDate, String actor,
			String description, String director, String filmStudio, Float duration, String imageUrl, String trailerUrl,
			String version, boolean isDelete, int status, LocalDateTime createdAt, LocalDateTime updatedAt,
			MovieType movieType, List<Schedule> schedules) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.actor = actor;
		this.description = description;
		this.director = director;
		this.filmStudio = filmStudio;
		this.duration = duration;
		this.imageUrl = imageUrl;
		this.trailerUrl = trailerUrl;
		this.version = version;
		this.isDelete = isDelete;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.movieType = movieType;
		this.schedules = schedules;
	}

	public MovieDTO() {
		super();
	}
	
	
}
