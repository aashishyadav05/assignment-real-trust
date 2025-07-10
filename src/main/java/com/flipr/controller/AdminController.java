package com.flipr.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flipr.modal.Client;
import com.flipr.modal.Contact;
import com.flipr.modal.Project;
import com.flipr.modal.Subcriber;
import com.flipr.repository.ClientRepository;
import com.flipr.repository.ContactRepository;
import com.flipr.repository.ProjectRepository;
import com.flipr.repository.SubcriberRepository;
import com.flipr.service.CloudinaryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private ProjectRepository projectRepo;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private SubcriberRepository subcriberRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CloudinaryService cloudinaryService;

    private final String uploadDir = "src/main/resources/static/image/";

   
	
	@GetMapping("/project/addform")
	public String showAddPojectForm() {
		return "admin/add_project";
	}
	
	//for project add process
	@PostMapping("/project/add")
	public String saveProject(@RequestParam String name,
	                          @RequestParam String description,
	                          @RequestParam("image") MultipartFile file,
	                          RedirectAttributes ra) throws IOException {

	    String imageUrl = cloudinaryService.upload(file);   // ⬅ Cloudinary
	    Project project = new Project();
	    project.setName(name);
	    project.setDescription(description);
	    project.setImage(imageUrl);

	    projectRepo.save(project);
	    ra.addFlashAttribute("msg", "Project Added Successfully!");
	    return "redirect:/admin/project/addform";
	}

	
	// for contact
		@PostMapping("/contact")
		public String handleContact(@ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
			System.out.println(contact);
			contactRepository.save(contact);
			
		    redirectAttributes.addFlashAttribute("successMessage", "Your Details submitted successfully!");
		  
		    return "redirect:/home";
		}
		//for view contacts
		@GetMapping("/contacts")
		public String viewContact(Model model) {
			
			List<Contact> contacts = contactRepository.findAll();
			
			model.addAttribute("contacts",contacts);
			
			return "admin/admin_contact";
		}
	//
		
	//for Subscriber user email.
		@PostMapping("/subscriber")
		public String handleSubscriber(@RequestParam("email") String email,Model model,RedirectAttributes redirectAttributes) {
			Subcriber subcriber = new Subcriber();
			subcriber.setEmail(email);
			subcriberRepository.save(subcriber);
			System.out.println(subcriber);
			redirectAttributes.addFlashAttribute("msg", "Your Email is Subscribed..");
			return "redirect:/home";
		}
		
		@GetMapping("/view_email")
		public String viewSubscriber(Model model) {
			List<Subcriber> email = subcriberRepository.findAll();
			model.addAttribute("subscriber",email);
			return "admin/admin_subscriber";
		}
	//
		
	// for happy Client
		@GetMapping("/client/add")
		public String addClientForm() {
			return "admin/add_client";
		}
		
		@PostMapping("/client/save")
		public String saveClient(@RequestParam String name,
		                         @RequestParam String designation,
		                         @RequestParam String description,
		                         @RequestParam("image") MultipartFile file,
		                         RedirectAttributes ra) throws IOException {

		    String imageUrl = cloudinaryService.upload(file);  // ⬅ Cloudinary call

		    Client client = new Client();
		    client.setName(name);
		    client.setDesignation(designation);
		    client.setDescription(description);
		    client.setImage(imageUrl);  // ⬅ Store full URL

		    clientRepository.save(client);
		    ra.addFlashAttribute("msg", "Client added successfully!");
		    
		    return "redirect:/admin/client/add";
		}

	
	// loginprocess of  admin 
		@PostMapping("/login")
	    public String processLogin(@RequestParam("username") String username,
	                               @RequestParam("password") String password,
	                               HttpSession session,
	                               RedirectAttributes attributes) {

	        if ("admin".equals(username) && "admin123".equals(password)) {
	            session.setAttribute("isAdminLoggedIn", true);
	            return "redirect:/admin/dashboard";
	        } else {
	            attributes.addFlashAttribute("error", "Invalid credentials");
	            return "redirect:/login";
	        }
	    }
		
	
	// after login admin in dashboard page for all admin related access
		  @GetMapping("/dashboard")
		    public String adminDashboard(HttpSession session) {
		        Boolean isLoggedIn = (Boolean) session.getAttribute("isAdminLoggedIn");
		        if (isLoggedIn != null && isLoggedIn) {
		            return "admin/admin_dashboard";
		        }
		        return "redirect:/login";
		    }	
		  
		  //for logout admin
		  @GetMapping("/logout")
		    public String logout(HttpSession session) {
		        session.invalidate();
		        return "redirect:/home";
		    }
		
		
		
		
		
}




















