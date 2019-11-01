package com.excek.no5;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class userController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			System.out.println("login Failure");
			return "redirect:/users/loginForm";
		}
		if (!user.matchPassword(password)) {
			System.out.println("login Failure");
			return "redirect:/users/loginForm";
		}

		session.setAttribute(SessionUtils.USER_SESSION_KEY, user);
		System.out.println("login Success");

		return "redirect:/";
	}

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@PostMapping("")
	public String create(User user, Model model) {
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (!SessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = SessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		if (SessionUtils.isLoginUser(session))) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = SessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		User user = userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(SessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
}
