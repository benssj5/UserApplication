package com.spring.UserApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.UserApplication.dao.UserRepository;
import com.spring.UserApplication.model.User;


@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@RequestMapping("/")
	public String home(ModelMap modelMap, Integer edit) {
		List<User> users = userRepo.findAll();
		modelMap.put("users", users);
		modelMap.put("edit",edit==null?0:edit.intValue());
		return "pages/home";
	}
	
	
	@RequestMapping("/addUser")
	public String addUser(ModelMap model,  @ModelAttribute("name")String name,  @ModelAttribute("lastname")String lastname,  @ModelAttribute("age")int age) { 
		User user = new User();
		user.setAge(age);
		user.setName(name);
		user.setLastname(lastname);
		userRepo.save(user);
		
		return home(model,0);

	}
	
	
	@RequestMapping("/deleteUser")
	public String deleteUser(ModelMap model,int id) {
		
		userRepo.deleteById(id);
		
		return home(model,0);
	}
	
	/**
	 * Affiche le user selectionner pour le modifier
	 * @param model
	 * @param id
	 * @param name
	 * @param lastname
	 * @param age
	 * @return
	 */
	@RequestMapping("/printUser")
	public String printUser(ModelMap model,int id) {
		User u = userRepo.findById(id).get();
		model.put("user", u);
		
		return home(model,1);
	}
	
	@RequestMapping("/editUser")
	public String editUser(ModelMap model,int id, String name, String lastname, int age) {
		//if id==0 then we add an user else we modify an user existing
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setLastname(lastname);
		u.setAge(age);
		userRepo.save(u);
		return home(model,0);
	}
	
	
	
}
