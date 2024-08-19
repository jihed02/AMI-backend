package com.example.amibackend.repositories;

import com.example.amibackend.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {

    @Query("SELECT r FROM Reclamation r JOIN FETCH r.client")
    List<Reclamation> findAllWithClientDetails();

}
