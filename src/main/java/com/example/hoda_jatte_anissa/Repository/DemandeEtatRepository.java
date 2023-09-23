package com.example.hoda_jatte_anissa.Repository;

import com.example.hoda_jatte_anissa.Entity.DemandeEtat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeEtatRepository extends JpaRepository<DemandeEtat, Long> {
    List<DemandeEtat> findByEtat(String etat);
}
