package locationsweb.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import locationsweb.domain.Location;

@Service
public class LocationService {

	private AtomicInteger id = new AtomicInteger();

	private List<Location> locations = new ArrayList<Location>(List.of(
					new Location((long) id.incrementAndGet(), "Pécs", 1, 2), 
					new Location((long) id.incrementAndGet(), "Csorna", 3, 4), 
					new Location((long) id.incrementAndGet(), "Tomaj", 5, 6)));
	
	public void saveLocation(String name, double lat, double lon) {
		locations.add(new Location((long) id.incrementAndGet(), name, lat, lon));
	}

	public List<Location> listLocations() {
		return new ArrayList<>(locations);
	}

	public Location findLocationById(long id) {
		return locations.stream().filter(e -> id == e.getId()).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Can not find location: " + id));
	}

	public void updateLocation(Location location) {
		Location locationToModify = findLocationById(location.getId());
		locationToModify.setName(location.getName());
	}

	public void reset() {
		id.set(0);
		locations.clear();
	}
}
