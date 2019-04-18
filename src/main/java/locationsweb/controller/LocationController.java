package locationsweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import locationsweb.backend.LocationService;
import locationsweb.domain.Location;

@Controller
@RequestMapping("/location")
public class LocationController {
	
	private LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView findLocationById(@PathVariable("id") long id) {
		Location location = locationService.findLocationById(id);
		return new ModelAndView("location", "location", location);
	}
	

}
