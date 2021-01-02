package org.sid.web;

import org.sid.entities.User;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AccountRestController {
	@Autowired
	private AccountService accountService;
	@PostMapping("/create-user")
	public User register(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("You must confirm your password");
		User user = accountService.findUserByUsername(userForm.getUsername());
		if(user!=null)
			throw new RuntimeException("This User already exists");
		User appUser = new User();
		appUser.setUsername(userForm.getUsername());
		appUser.setPwd(userForm.getPassword());
		appUser.setEmail(userForm.getEmail());
		appUser.setFonction(userForm.getFonction());
		appUser.setNom(userForm.getNom());
		appUser.setPrenom(userForm.getPrenom());
		accountService.saveUser(appUser);
		accountService.AddRoleToUser(userForm.getUsername(), "USER");
		
		if(userForm.isProf() == true) {
			accountService.AddRoleToUser(userForm.getUsername(), "PROF");
		}
		
		if(userForm.isAdmin() == true) {
			accountService.AddRoleToUser(userForm.getUsername(), "ADMIN");
		}
		
		if(userForm.isSuperAdmin() == true) {
			accountService.AddRoleToUser(userForm.getUsername(), "SUPERADMIN");
		}
		return appUser; 
	}
	
	@PutMapping("/update-user")
	public User update(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("You must confirm your password");
		User appUser = accountService.findUserByUsername(userForm.getUsername());
		appUser.setUsername(userForm.getUsername());
		appUser.setPwd(userForm.getPassword());
		appUser.setEmail(userForm.getEmail());
		appUser.setFonction(userForm.getFonction());
		appUser.setNom(userForm.getNom());
		appUser.setPrenom(userForm.getPrenom());
		accountService.saveUser(appUser);
		accountService.AddRoleToUser(userForm.getUsername(), "USER");
		System.out.println("**************************"+userForm.isAdmin);
		if(userForm.isAdmin == true) {
			accountService.AddRoleToUser(userForm.getUsername(), "ADMIN");
		}
		if(userForm.isSuperAdmin() == true) {
			accountService.AddRoleToUser(userForm.getUsername(), "SUPERADMIN");
		}
		return appUser; 
	}
}
