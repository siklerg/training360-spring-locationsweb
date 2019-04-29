package locationsweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

	private MessageSource messageSource;

	public LocationsController(LocationService locationService, MessageSource messageSource) {
		this.locationService = locationService;
		this.messageSource = messageSource;
	}

	@ModelAttribute
	public Location location() {
		return new Location();
	}

	// Új location létrehozás űrlap elemei számára
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

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String actualTime = dtf.format(LocalDateTime.now());

		List<Location> locations = locationService.listLocations();

		locationsObjects.put("time", actualTime);
		locationsObjects.put("locations", locations);

		return new ModelAndView("index", "locationsObjects", locationsObjects);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView saveNewLocation(@Valid LocationForm locationForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Locale locale) {

		HashMap<String, Object> locationsObjects = new HashMap<String, Object>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String actualTime = dtf.format(LocalDateTime.now());

		List<Location> locations = locationService.listLocations();

		locationsObjects.put("time", actualTime);
		locationsObjects.put("locations", locations);

		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("index", "locationsObjects", locationsObjects);
		}
		
		Double lat, lon;

		try {
			String coordinates = locationForm.getCoordinates();
			lat = Double.parseDouble(coordinates.split(", ")[0]);
			lon = Double.parseDouble(coordinates.split(", ")[1]);
		} catch (Exception e) {
			System.out.println("Hibás koordináták!");
//			ObjectError objectError = new ObjectError("locationForm", "Wrong coordinates format!");
//			bindingResult.addError(objectError);
			
			bindingResult.addError(new FieldError("locationsObjects", "coordinates", "Wrong coordinates format!"));
			return new ModelAndView("index", "locationsObjects", locationsObjects);
		}

		String coordinates = locationForm.getCoordinates();
		lat = Double.parseDouble(coordinates.split(", ")[0]);
		lon = Double.parseDouble(coordinates.split(", ")[1]);
		locationService.saveLocation(locationForm.getName(), lat, lon);

		String message = messageSource.getMessage("form.saved", new Object[] { locationForm.getName() }, locale);

		redirectAttributes.addFlashAttribute("message", message);
		return new ModelAndView("redirect:/");
	}
}
