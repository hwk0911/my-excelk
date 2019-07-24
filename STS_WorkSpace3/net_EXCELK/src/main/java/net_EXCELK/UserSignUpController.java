package net_EXCELK;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignUpController {
	private List<Users> user = new ArrayList<Users>();
	
	@PostMapping("/create")
	public String create(Users users){
		user.add(users);
		System.out.println(users);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model){
		model.addAttribute("users", user);
		return "list";
	}
}
