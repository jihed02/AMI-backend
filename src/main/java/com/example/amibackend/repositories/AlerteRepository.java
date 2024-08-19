package com.example.amibackend.repositories;

import com.example.amibackend.entities.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlerteRepository extends JpaRepository<Alerte,Long> {
}
