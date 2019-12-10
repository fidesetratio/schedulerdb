package com.app.quartz.engine.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

	private String message = "welcome";

	private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("tasks", tasks);

		return "welcome";
	}

	@GetMapping("/hello")
	public String welcomeithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Model model) {
		model.addAttribute("message", name);
		return "welcome";
	}

	@GetMapping("/main")
	public String mainPage(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
		model.addAttribute("message", name);
		return "main";
	}

}