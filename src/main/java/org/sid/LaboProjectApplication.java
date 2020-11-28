package org.sid;


import java.util.Date;

import org.sid.dao.EquipeRepository;
import org.sid.dao.RapportRepository;
import org.sid.dao.TheseRepository;
import org.sid.dao.TheseSoutenuRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.Equipe;
import org.sid.entities.User;
import org.sid.service.AccountService;
import org.sid.entities.Fonction;
import org.sid.entities.Rapport;
import org.sid.entities.These;
import org.sid.entities.TheseSoutenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LaboProjectApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EquipeRepository equipeRepository;
	
	@Autowired
	private TheseRepository theseRepository;
	
	@Autowired
	private TheseSoutenuRepository theseSoutenuRepository;
	
	@Autowired
	private RapportRepository rapportRepository;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LaboProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new User(null, "DA92376","Chakib","Amine", "amine@gmail.com","1234",Fonction.Administrateur));
		//accountService.saveUser(new AppUser(null,"user","1234",null));
		accountService.saveRole(new AppRole(null, "SUPERADMIN"));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		
		accountService.AddRoleToUser("DA92376", "USER");
		accountService.AddRoleToUser("DA92376", "ADMIN");
		accountService.AddRoleToUser("DA92376", "SUPERADMIN");
		User us2 = new User();
		us2.setNom("Mohammed");
		us2.setUsername("M20142151");
		us2.setEmail("mohammed@gmail.com");
		//Equipe e1 = equipeRepository.save(us2);
		Equipe e1 = equipeRepository.save(new Equipe(null, null, null));
		Equipe e2 = equipeRepository.save(new Equipe(null, null, null));
		
		
		These t1 = new These(null, "Titre", null);
		
		TheseSoutenu ts1 = new TheseSoutenu(null, "These Soutenu 1", new Date(), null);
		
		
		
		/*User us = new User(null, "N137416519", "Chakib", "Amine", "amine@gmail.com", "01041970", Fonction.Doctorant, e1, null);
		User us1 = new User(null, "M13741625", "Rochdi", "Mina", "mina@gmail.com", "05032568", Fonction.Enseignant, e2, null);
		User us3 = new User(null, "D13741625", "Ekko", "Mohammed", "mina@gmail.com", "05032568", Fonction.Doctorant, e1, null);
		User us4 = new User(null, "K13741625", "Benani", "Samir", "mina@gmail.com", "05032568", Fonction.Doctorant, e2, null);
		userRepository.save(us);
		userRepository.save(us1);
		userRepository.save(us3);
		userRepository.save(us4);
		equipeRepository.save(e1);
		equipeRepository.save(e2);*/
		theseRepository.save(t1);
		theseSoutenuRepository.save(ts1);
		
		
	}

}
