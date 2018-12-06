package com.shelby.beltexambb.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shelby.beltexambb.models.Task;
import com.shelby.beltexambb.models.User;
import com.shelby.beltexambb.services.SiteService;
import com.shelby.beltexambb.validator.UserValidator;

@Controller
public class SiteController {

	private final UserValidator UV;
	private final SiteService ss;
	
	public SiteController(UserValidator UV, SiteService ss) {
		this.UV = UV;
		this.ss = ss;
	}
	
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("newUser", new User());
		return "index.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User u, BindingResult br, HttpSession session, Model model) {
		System.out.println("INSIDE CONTROLLER");
		UV.validate(u, br);
		if(br.hasErrors()) {
			System.out.println("VALIDATION FAILED");
			model.addAttribute("newUser", new User());
			model.addAttribute("errors", br.getAllErrors());
			return "index.jsp";
		} else {
			ss.registerUSer(u);
			System.out.println("USER CREATED");
			session.setAttribute("user_id", u.getId());
			return "redirect:/tasks";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(ss.authenticateUser(email, password)) {
			System.out.println("LOGIN SUCCESSFUL");
			User u = ss.findByEmail(email);
			session.setAttribute("user_id", u.getId());
			return "redirect:/tasks";
		} else {
			System.out.println("LOGIN UNSUCCESSFUL");
			model.addAttribute("error", "Login Failed");
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
	}
	
	@GetMapping("/tasks")
	public String taskHome(Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		model.addAttribute("user", ss.findUserById((Long)session.getAttribute("user_id")));
		model.addAttribute("alltasks", ss.findAllTasks());
//		System.out.println(ss.allTaskDesc());
//		System.out.println(ss.allTaskAsc());
		return "taskPage.jsp";
	}
	@GetMapping("/tasks/asc")
	public String taskHomeAsc(Model model, HttpSession session) {
		model.addAttribute("user", ss.findUserById((Long)session.getAttribute("user_id")));
		model.addAttribute("alltasks", ss.allTaskAsc());
//		System.out.println(ss.allTaskDesc());
//		System.out.println(ss.allTaskAsc());
		return "taskPage.jsp";
	}
	@GetMapping("/tasks/desc")
	public String taskHomeDesc(Model model, HttpSession session) {
		model.addAttribute("user", ss.findUserById((Long)session.getAttribute("user_id")));
		model.addAttribute("alltasks", ss.allTaskDesc());
//		System.out.println(ss.allTaskDesc());
//		System.out.println(ss.allTaskAsc());
		return "taskPage.jsp";
	}
	
	@GetMapping("/createTask")
	public String createPage(Model model, HttpSession session) {
		model.addAttribute("allusers", ss.findAllUsers());
		model.addAttribute("newTask", new Task());
		return "newTask.jsp";
	}
	
	@PostMapping("/createTask")
	public String createTask(@Valid @ModelAttribute("newTask") Task newTask, BindingResult br, HttpSession session, Model model) {
		if(br.hasErrors()) {
			System.out.println("ERRORS ON CREATION");
			model.addAttribute("allusers", ss.findAllUsers());
			model.addAttribute("newTask", new Task());
			model.addAttribute("errors", br.getAllErrors());
			System.out.println(br.getAllErrors());
			return "newTask.jsp";
		} else {
			ss.addNewTask(newTask, (Long) session.getAttribute("user_id"));
			System.out.println("TASK CREATED");
			return "redirect:/tasks";
		}
	}
	
	@GetMapping("/task/{id}")
	public String showTask(@PathVariable("id")Long tid, Model model, HttpSession session) {
		Task task = ss.findTaskById(tid);
		model.addAttribute("task", task);
		return "showTask.jsp";
	}
	
	@GetMapping("/task/{id}/edit")
	public String editTask(@PathVariable("id") Long tid, Model model, HttpSession session) {
		Task task = ss.findTaskById(tid);
		if(task.getCreator().getId() != (Long)session.getAttribute("user_id")) {
			return "redirect:/tasks";
		}
		model.addAttribute("allusers", ss.findAllUsers());
		model.addAttribute("task", task);
		return "editTask.jsp";
	}
	
	@PostMapping("/task/{id}/edit")
	public String editTask(@PathVariable("id") Long tid, @Valid @ModelAttribute("task")Task task, BindingResult br, Model model, HttpSession session) {
		if(br.hasErrors()) {
			System.out.println("ERRORS ON CREATION");
			model.addAttribute("allusers", ss.findAllUsers());
			model.addAttribute("task", task);
			model.addAttribute("errors", br.getAllErrors());
			System.out.println(br.getAllErrors());
			return "editTask.jsp";
		} else {
			ss.addNewTask(task, (Long) session.getAttribute("user_id"));
			System.out.println("TASK updated");
			return "redirect:/tasks";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/task/{id}/delete")
	public String deleteTask(@PathVariable("id") Long id, Model model, HttpSession session) {
		ss.deleteTask(id);
		return "redirect:/tasks";
	}
	
	@GetMapping("/task/{id}/complete")
	public String complete(@PathVariable("id") Long id, Model model, HttpSession session) {
		ss.deleteTask(id);
		return "redirect:/tasks";
	}
	
	
	
	
	
	
	
	
}
