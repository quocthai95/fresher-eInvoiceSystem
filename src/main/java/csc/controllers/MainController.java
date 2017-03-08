package csc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	@RequestMapping("/404")
	public String pageNotFound() {
		return "index";
	}

	@ExceptionHandler(Exception.class)
	public String globalExceptionHandler(Exception e) {
		// ModelAndView modelAndView = new ModelAndView("error");
		// modelAndView.addObject("message", e.getMessage());
		// return modelAndView;
		return "index";
	}

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping(value = "/home") // , method = RequestMethod.GET
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/register") // , method = RequestMethod.GET
	public String register() {
		return "register";
	}
	
    @RequestMapping("/404.html")
    public String render404() {
        // Add model attributes
        return "login";
    }
}
