package com.example.hoda_jatte_anissa.Repository;
import com.example.hoda_jatte_anissa.Entity.Demande;
import com.example.hoda_jatte_anissa.Entity.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {
    List<Encadrant> findBySpecialiteContaining(String specialite);

}
