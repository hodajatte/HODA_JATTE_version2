package com.example.hoda_jatte_anissa.Service;

import com.example.hoda_jatte_anissa.Entity.Demande;
import com.example.hoda_jatte_anissa.Entity.Encadrant;
import com.example.hoda_jatte_anissa.Repository.EncadrantRepository;

import java.util.List;

public interface EncadrantService {




    public List<Encadrant> getAllEncadrants();
     Encadrant saveEncadrant(Encadrant encadrant);
    Encadrant getEncadrantById(Long id);

    void deleteEncadrant(Long id);






    Encadrant updateEncadrant(Encadrant encadrant);

    List<Encadrant> rechercherEncadrantParSpecialite(String specialiteRecherche);
}
