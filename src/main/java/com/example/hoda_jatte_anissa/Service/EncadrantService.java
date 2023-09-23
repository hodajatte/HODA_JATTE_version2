package com.example.hoda_jatte_anissa.Service;

import com.example.hoda_jatte_anissa.Entity.Encadrant;

import java.util.List;

public interface EncadrantService {
    public List<Encadrant> getAllEncadrants();
    public Encadrant saveEncadrant(Encadrant encadrant);
    Encadrant getEncadrantById(Long id);

    void deleteEncadrant(Long id);

    public List<Encadrant> rechercherEncadrantParSpecialite(String SpecialiteRecherche);
}
