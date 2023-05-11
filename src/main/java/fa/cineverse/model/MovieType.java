package fa.cineverse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MovieType {
	@Id
	private String movieTypeId;   
}
