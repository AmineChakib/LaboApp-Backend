package org.sid.web;

import java.util.List;

import org.sid.dao.UserRepository;
import org.sid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestControler {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/tasks")
	public List<User> listTasks(){
		return userRepository.findAll();
	}
	@PostMapping("/tasks")
	public User save(@RequestBody User u) {
		return userRepository.save(u);
	}
}
