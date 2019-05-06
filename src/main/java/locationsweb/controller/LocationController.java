package locationsweb.controller;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import locationsweb.backend.LocationService;
import locationsweb.domain.Location;

@Controller
@RequestMapping("/location")
public class LocationController {
	
	private LocationService locationService;
	
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@ModelAttribute
	public Location location (@PathVariable("id") long id) {
		return locationService.findLocationById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView findLocationById(@PathVariable("id") long id) {
		Location location = locationService.findLocationById(id);
		return new ModelAndView("location", "location", location);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateLocation(@ModelAttribute Location location, RedirectAttributes redirectAttributes) {
		locationService.updateLocation(location);
		redirectAttributes.addFlashAttribute("message", "Location modified: " + location.getName());
		return "redirect:/";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteLocation(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		String name = locationService.findLocationById(id).getName();
		locationService.deleteLocation(id);
		redirectAttributes.addFlashAttribute("message", "Location deleted: " + name);
		return "redirect:/";
	}
}
