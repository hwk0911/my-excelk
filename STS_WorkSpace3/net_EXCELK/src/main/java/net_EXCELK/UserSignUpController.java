package net_EXCELK;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserSignUpController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm (){
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String passWd, HttpSession session){
		User user = userRepository.findByUserId(userId);
		if(user == null){
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";			
		}
		
		if(!passWd.equals(user.getPassWd())){
			System.out.println("Login Failure!");
			return "redirect:/user/loginForm";
		}
		session.setAttribute("user", user);
				
		System.out.println("Login Success!");
		
		return "redirect:/users";
	}
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}	
	
	@PostMapping("")
	public String create(User user){
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model){
		User user = userRepository.findById(id).get();
		System.out.println(user);
		model.addAttribute("users", user);
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser){
		User user = userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
