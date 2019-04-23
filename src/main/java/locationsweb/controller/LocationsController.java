package locationsweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import locationsweb.backend.LocationService;
import locationsweb.domain.Location;
import locationsweb.domain.LocationForm;

@Controller
public class LocationsController {

	private LocationService locationService;

	public LocationsController(LocationService locationService) {
		this.locationService = locationService;
	}

	@ModelAttribute
	public Location location() {
		return new Location();
	}

	@ModelAttribute
	public LocationForm locationForm() {
		return new LocationForm();
	}

	@RequestMapping("/locations")
	@ResponseBody
	public String weblocations() {
		return "<html><body><h1>Locations</h1></body></html>";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
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

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveNewLocation(@ModelAttribute LocationForm locationForm, RedirectAttributes redirectAttributes) {
		String coordinates = locationForm.getCoordinates();

		Double lat = Double.parseDouble(coordinates.split(", ")[0]);
		Double lon = Double.parseDouble(coordinates.split(", ")[1]);

		locationService.saveLocation(locationForm.getName(), lat, lon);
		redirectAttributes.addFlashAttribute("message", "Location added: " + locationForm.getName());
		return "redirect:/";
	}
}
