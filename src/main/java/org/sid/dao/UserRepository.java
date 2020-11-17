package org.sid.dao;

import java.util.List;

import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findAll();
}
