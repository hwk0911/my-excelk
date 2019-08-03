package net.excelk.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {
	private List<User> users = new ArrayList<User>();
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@GetMapping("/form")
	public String form(){
		return "form";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println(user);
		users.add(user);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "list";
	}
}
