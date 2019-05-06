package locationsweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import locationsweb.backend.LocationService;
import locationsweb.domain.Location;

@RestController
@RequestMapping("/api")
public class LocationRestController {

	private LocationService locationService;

	public LocationRestController(LocationService locationService) {
		this.locationService = locationService;
	}

	@GetMapping("/locations")
	public List<Location> locations() {
		return locationService.listLocations();
	}

	@GetMapping("/locations/{id}")
	public Location getLocationById(@PathVariable("id") long id) {
		return locationService.findLocationById(id);
	}
	
	@DeleteMapping("/locations/{id}")
	public void deleteLocationById(@PathVariable("id") long id) {
		locationService.deleteLocation(id);
	}

	@PostMapping("/locations")
	public void saveLocation(@RequestBody Location location) {
		locationService.saveLocation(location.getName(), location.getLat(), location.getLon());
	}

}
