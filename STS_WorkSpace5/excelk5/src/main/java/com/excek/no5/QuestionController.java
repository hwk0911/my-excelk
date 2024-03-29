package com.excek.no5;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!SessionUtils.isLoginUser(session)) {
			return "users/loginForm";
		}
		
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!SessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User sessionUser = SessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
}
