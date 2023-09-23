package com.example.hoda_jatte_anissa.Service;

import com.example.hoda_jatte_anissa.Entity.Demande;
import com.example.hoda_jatte_anissa.Entity.DemandeEtat;
import org.springframework.core.io.Resource;

import java.time.LocalDate;
import java.util.List;

public interface DemandeService {
    List<Demande> getAllDemandes();
    void saveDemande(Demande demande);
    Demande getDemandeById(Long id);
    void deleteDemande(Long id);
    void accepterDemande(Long demandeId);
    void refuserDemande(Long demandeId);
    void mettreEnAttente(Long demandeId);

    List<DemandeEtat> getDemandesAcceptees();

    List<DemandeEtat> getDemandesRefusees();

    List<DemandeEtat> getDemandesEnAttente();

    Resource loadCVFile(Demande demande);

    Resource loadLettreFile(Demande demande);

    List<Demande> rechercherDemandesParNom(String nomRecherche);


    // Ajoutez d'autres méthodes nécessaires


}

