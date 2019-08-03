package net.excelk.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomController {
	@GetMapping("/helloworld")
	public String welcome(String name, Model model, int age) {
		System.out.println(name + " " +age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "welcome";
	}
}
