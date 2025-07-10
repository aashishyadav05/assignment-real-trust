package com.flipr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.flipr.modal.Client;
import com.flipr.modal.Project;
import com.flipr.repository.ClientRepository;
import com.flipr.repository.ProjectRepository;

@Controller
public class HomeController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping({"/","/home"})
	public String showHomePage(Model model) {
		
		List<Project> project = projectRepository.findAll();
		List<Client> clients = clientRepository.findAll();
		model.addAttribute("projects",project);
		model.addAttribute("clients",clients);
		return "home";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "admin_login";
	}
	
}
