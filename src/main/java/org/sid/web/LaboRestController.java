package org.sid.web;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sid.dao.EquipeRepository;
import org.sid.dao.RapportRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Rapport;
import org.sid.entities.User;
import org.sid.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LaboRestController {
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private EquipeRepository equipeRepository;
	@Autowired
	private RapportRepository rapportRepository;
	@Autowired
	private FileUploadService fileUploadService;
	// Create user Rest api
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@PostMapping("postFile")
	public void uploadFile(/*@RequestParam("file")*/@RequestBody MultipartFile file, @RequestParam("titre") String titre) throws IllegalStateException, IOException {
		Rapport rapport = new Rapport();
		//System.out.println(titre.toString());
		//System.out.println("Amine");
		rapport.setPath(file.getOriginalFilename());
		rapport.setTitre(titre);
		rapportRepository.save(rapport);
		fileUploadService.uploadFile(file, rapportRepository.findMax());
	}
	
	@GetMapping(path="/rapport/{id}", produces=MediaType.APPLICATION_PDF_VALUE)
	public byte[] file(@PathVariable (name="id")Long id) throws Exception{
		Rapport rapport=rapportRepository.findById(id).get();
		Long rapportId = rapport.getId();
		File file= new File(System.getProperty("user.home")+"/labo/rapports/"+rapportId+".pdf");
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}

}
