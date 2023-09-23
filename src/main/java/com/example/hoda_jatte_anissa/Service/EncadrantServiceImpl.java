package com.example.hoda_jatte_anissa.Service;
import com.example.hoda_jatte_anissa.Entity.Encadrant;
import com.example.hoda_jatte_anissa.Repository.EncadrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class EncadrantServiceImpl implements EncadrantService{
    @Autowired
    private EncadrantRepository encadrantRepository;
    @Override
    public List<Encadrant> getAllEncadrants() {

        return encadrantRepository.findAll();
    }
    @Override
    public Encadrant saveEncadrant(Encadrant encadrant) {
        return encadrantRepository.save(encadrant);
    }
    @Override
    public Encadrant getEncadrantById(Long id) {

        return encadrantRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteEncadrant(Long id) {

        encadrantRepository.deleteById(id);
    }


    public List<Encadrant> rechercherEncadrantParSpecialite(String SpecialiteRecherche) {
        // Utilisez votre repository pour rechercher les demandes par nom
        return encadrantRepository.findBySpecialiteContaining(SpecialiteRecherche);
    }
}
