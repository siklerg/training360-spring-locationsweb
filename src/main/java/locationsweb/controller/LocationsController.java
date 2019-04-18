package locationsweb.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import locationsweb.domain.Location;

@Controller
public class LocationsController {

	@RequestMapping("/locations")
	@ResponseBody
	public String weblocations() {
		return "<html><body><h1>Locations</h1></body></html>";
	}

	@RequestMapping("/")
	public ModelAndView locations() {

		List<Object> locationsObjects = new ArrayList<>();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime actualTime = LocalDateTime.now();

		String header = "Locations";

		Location loc1 = new Location((long) 1,"Pécs", 1, 1);
		Location loc2 = new Location((long) 2,"Csorna", 2, 2);
		Location loc3 = new Location((long) 3,"Tomaj", 3, 3);
		
		List<Location> locations = new ArrayList<>();
		locations.add(loc1);
		locations.add(loc2);
		locations.add(loc3);
		

		locationsObjects.add(actualTime);
		locationsObjects.add(header);
		locationsObjects.add(locations);

		return new ModelAndView("index", "locationsObjects", locationsObjects);
	}
}
