package org.sid.service;

import org.sid.entities.Equipe;
import org.sid.entities.Rapport;
import org.sid.entities.These;
import org.sid.entities.TheseSoutenu;
import org.sid.entities.User;
import org.sid.web.RegisterForm;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class GlobalRepositoryRestConfig extends RepositoryRestConfigurerAdapter{
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguraton) {
		repositoryRestConfiguraton.exposeIdsFor(Equipe.class, These.class, Rapport.class, TheseSoutenu.class,
				User.class, RegisterForm.class);
		repositoryRestConfiguraton.getCorsRegistry()
		.addMapping("/**")
		.allowedOrigins("http://localhost:4200") 
		.allowedHeaders("*")
		.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
		//repositoryRestConfiguraton.setDefaultMediaType(MediaType.APPLICATION_JSON);
		//repositoryRestConfiguraton.useHalAsDefaultJsonMediaType(false);
	}

}
