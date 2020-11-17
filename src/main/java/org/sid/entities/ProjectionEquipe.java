package org.sid.entities;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1", types = {org.sid.entities.Equipe.class})
public interface ProjectionEquipe {
	public Long getId();
	public Collection<User> getUsers();
}
