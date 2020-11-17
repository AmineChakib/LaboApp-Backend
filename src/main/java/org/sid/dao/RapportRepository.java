package org.sid.dao;

import org.sid.entities.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RapportRepository extends JpaRepository<Rapport, Long>{
    @Query("SELECT MAX(id) FROM Rapport")
    Long findMax();
}
