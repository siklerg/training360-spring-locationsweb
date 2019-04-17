package locationsweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationsController {

	@RequestMapping("/locations")
	@ResponseBody
	public String locations() {
		return "<html><body><h1>Locations</h1></body></html>";
	}
}
