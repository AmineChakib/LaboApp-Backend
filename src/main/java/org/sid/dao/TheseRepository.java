package org.sid.dao;

import org.sid.entities.These;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TheseRepository extends JpaRepository<These, Long> {
	
}
