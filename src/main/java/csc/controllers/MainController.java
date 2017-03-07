package csc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {
	@RequestMapping(value = "/") // , method = RequestMethod.GET
	public String index() {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "403";
	}

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/home") // , method = RequestMethod.GET
	public String home() {
		return "index";
	}
}
