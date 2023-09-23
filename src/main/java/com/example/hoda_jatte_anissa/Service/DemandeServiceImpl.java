package com.example.hoda_jatte_anissa.Service;
import com.example.hoda_jatte_anissa.Entity.Demande;
import com.example.hoda_jatte_anissa.Entity.DemandeEtat;
import com.example.hoda_jatte_anissa.Repository.DemandeEtatRepository;
import com.example.hoda_jatte_anissa.Repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class DemandeServiceImpl implements DemandeService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DemandeEtatRepository demandeEtatRepository;

    @Override
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }


    @Override
    public void saveDemande(Demande demande) {
        demandeRepository.save(demande);
    }

    @Override
    public Demande getDemandeById(Long id) {

        return demandeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }

    @Override
    public void accepterDemande(Long demandeId) {
        Demande demande = demandeRepository.findById(demandeId).orElse(null);
        if (demande != null) {
            // Mettez à jour les dates et l'état de la demande
            DemandeEtat etat = new DemandeEtat();
            etat.setEtat("Acceptée");
            etat.setDemande(demande);
            demande.getEtats().add(etat);
            // Enregistrez les dates de début et de fin de stage
            demandeRepository.save(demande);
        }
    }

    @Override
    public void refuserDemande(Long demandeId) {
        Demande demande = demandeRepository.findById(demandeId).orElse(null);
        if (demande != null) {
            // Mettez à jour l'état de la demande
            DemandeEtat etat = new DemandeEtat();
            etat.setEtat("Refusée");
            etat.setDemande(demande);
            demande.getEtats().add(etat);
            demandeRepository.save(demande);
        }
    }

    @Override
    public void mettreEnAttente(Long demandeId) {
        Demande demande = demandeRepository.findById(demandeId).orElse(null);
        if (demande != null) {
            // Mettez à jour l'état de la demande
            DemandeEtat etat = new DemandeEtat();
            etat.setEtat("En attente");
            etat.setDemande(demande);
            demande.getEtats().add(etat);
            demandeRepository.save(demande);
        }
    }

    @Override
    public List<DemandeEtat> getDemandesAcceptees() {
        return demandeEtatRepository.findByEtat("Acceptée");
    }

    @Override
    public List<DemandeEtat> getDemandesRefusees() {
        return demandeEtatRepository.findByEtat("Refusée");
    }

    @Override
    public List<DemandeEtat> getDemandesEnAttente() {
        return demandeEtatRepository.findByEtat("En attente");
    }


    @Override
    public Resource loadCVFile(Demande demande) {
        String cvFileName = demande.getCvFile().getOriginalFilename();
        Path cvFilePath = Paths.get(uploadPath).resolve(cvFileName);
        return new FileSystemResource(cvFilePath);
    }

    @Override
    public Resource loadLettreFile(Demande demande) {
        String lettreFileName = demande.getLettreMotivationFile().getOriginalFilename();
        Path lettreFilePath = Paths.get(uploadPath).resolve(lettreFileName);
        return new FileSystemResource(lettreFilePath);
    }



    public List<Demande> rechercherDemandesParNom(String nomRecherche) {
        // Utilisez votre repository pour rechercher les demandes par nom
        return demandeRepository.findByNomContaining(nomRecherche);
    }



}


