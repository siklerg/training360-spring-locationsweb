package locationsweb.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LocationForm {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String coordinates;
	
	public LocationForm() {
	}

	public LocationForm(String name, String coordinates) {
		this.name = name;
		this.coordinates = coordinates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	
	

}
