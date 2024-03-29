package com.example.hoda_jatte_anissa.Repository;

import com.example.hoda_jatte_anissa.Entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {


    List<Demande> findByNomContaining(String nom);
    List<Demande> findByEtablissementContaining(String etablissemntRecherche);

    List<Demande> findByFiliereContaining(String filiereRecherche);

    /*List<Demande> finByNom_containing(String NomRecherche);*/

    /*List<Demande> findByEtablissement(String etablissement);*/
}

    // Ajoutez des méthodes personnalisées si nécessaire

