package fa.cineverse.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity 
@Data
public class MovieType {
	@Id
	private String movieTypeId;   
}
