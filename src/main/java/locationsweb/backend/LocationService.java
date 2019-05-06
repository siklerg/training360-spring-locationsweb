package locationsweb.backend;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import locationsweb.domain.Location;

@Service
public class LocationService {

	private LocationRepository locationRepository;

	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	public List<Location> listLocations() {
		return locationRepository.findAll();
	}

	public Location findLocationById(long id) {
		return locationRepository.findById(id).orElseThrow();
	}

	public void saveLocation(String name, double lat, double lon) {
		locationRepository.save(new Location(name, lat, lon));
	}

	@Transactional
	public void updateLocation(Location location) {
		Location locationToModify = locationRepository.getOne(location.getId());
		locationToModify.setName(location.getName());
		locationToModify.setLat(location.getLat());
		locationToModify.setLon(location.getLon());
	}

	public void deleteLocation(long id) {
		locationRepository.deleteById(id);
		
	}

}
