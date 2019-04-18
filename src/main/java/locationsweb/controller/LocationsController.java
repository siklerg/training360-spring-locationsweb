package locationsweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import locationsweb.backend.LocationService;
import locationsweb.domain.Location;

@Controller
public class LocationsController {
	
	private LocationService locationService;

	public LocationsController(LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping("/locations")
	@ResponseBody
	public String weblocations() {
		return "<html><body><h1>Locations</h1></body></html>";
	}

	@RequestMapping("/")
	public ModelAndView locations() {

		HashMap<String, Object> locationsObjects = new HashMap<String, Object>();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String actualTime = dtf.format(LocalDateTime.now());

		String header = "--- Locations Main Page ---";

		List<Location> locations = locationService.listLocations();

		locationsObjects.put("time", actualTime);
		locationsObjects.put("header", header);
		locationsObjects.put("locations", locations);

		return new ModelAndView("index", "locationsObjects", locationsObjects);
	}
}
