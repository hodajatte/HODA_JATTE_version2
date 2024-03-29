package com.example.hoda_jatte_anissa.Controller;
import com.example.hoda_jatte_anissa.Entity.DemandeEtat;
import com.example.hoda_jatte_anissa.Service.DemandeService;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.example.hoda_jatte_anissa.Entity.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Controller
public class AdminController {
    @Autowired
    private DemandeService demandeService;
    @GetMapping("/demandes")
    public String listDemandes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();
        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        List<Demande> demandes = demandeService.getAllDemandes();
        model.addAttribute("demandes", demandes);
        return "demandes";
    }

    @PostMapping("/demande/{demandeId}/accepter")
    public String accepterDemande(@PathVariable Long demandeId) {
        demandeService.accepterDemande(demandeId);
        return "redirect:/demandes?Success=True";

    }

    @PostMapping("/demande/{demandeId}/refuser")
    public String refuserDemande(@PathVariable Long demandeId) {
        demandeService.refuserDemande(demandeId);
        return "redirect:/demandes?Success=True";
    }

    @PostMapping("/demande/{demandeId}/attente")
    public String mettreEnAttente(@PathVariable Long demandeId) {
        demandeService.mettreEnAttente(demandeId);
        return "redirect:/demandes?Success=true";
    }

    @GetMapping("/demandes-acceptees")
    public String getDemandesAcceptees(Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        List<DemandeEtat> demandesAcceptees = demandeService.getDemandesAcceptees();
        model.addAttribute("demandesAcceptees", demandesAcceptees);
        return "demandes-acceptees";
    }
    @GetMapping("/demandes-refusees")
    public String getDemandesRefusees(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        List<DemandeEtat> demandesRefusees = demandeService.getDemandesRefusees();
        model.addAttribute("demandesRefusees", demandesRefusees);
        return "demandes-refusees";
    }

    @GetMapping("/demandes-en-attente")
    public String getDemandesEnAttente(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        List<DemandeEtat> demandesEnAttente = demandeService.getDemandesEnAttente();
        model.addAttribute("demandesEnAttente", demandesEnAttente);
        return "demandes-en-attente";
    }

    @GetMapping("/rechercher-demandes")
    public String rechercherDemandesParNom(@RequestParam(name = "nom") String nomRecherche, Model model) {
        List<Demande> resultatsRecherche = demandeService.rechercherDemandesParNom(nomRecherche);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        model.addAttribute("resultatsRecherche", resultatsRecherche);

        return "resultats";
    }

    @GetMapping("/demande/{demandeId}/download-cv")
    public ResponseEntity<Resource> downloadCV(@PathVariable Long demandeId) {
        Demande demande = demandeService.getDemandeById(demandeId);
        Resource cvFile = demandeService.loadCVFile(demande);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + cvFile.getFilename() + "\"")
                .body(cvFile);
    }

    @GetMapping("/demande/{demandeId}/download-lettre")
    public ResponseEntity<Resource> downloadLettre(@PathVariable Long demandeId) {
        Demande demande = demandeService.getDemandeById(demandeId);
        Resource lettreFile = demandeService.loadLettreFile(demande);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lettreFile.getFilename() + "\"")
                .body(lettreFile);
    }


    @GetMapping("/rechercher-demandes-acceptees")
    public String rechercherDemandesParEtablissement(@RequestParam(name = "etablissement")String etablissementRecherche, Model model) {
        List<Demande> resultatsRechercheAcceptees = demandeService.rechercherDemandesParEtablissement(etablissementRecherche);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        model.addAttribute("resultatsRechercheAcceptees", resultatsRechercheAcceptees);

        return "resultats-recherches-acceptees";
    }


    @GetMapping("/rechercher-demandes-refusees")
    public String rechercherDemandesParFiliere(@RequestParam(name = "filiere")String FiliereRecherche, Model model) {
        List<Demande> resultatsRechercheRefusees = demandeService.rechercherDemandesParfiliere(FiliereRecherche);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        model.addAttribute("resultatsRechercheRefusees", resultatsRechercheRefusees);

        return "resultats-recherches-Refusees";
    }



    /*@GetMapping("/rechercher-demandes-enAttente")
    public String rechercherDemandesParnom(@RequestParam(name = "nom")String NomRecherche, Model model) {
        List<Demande> resultatsRechercheEnAttente = demandeService.rechercherDemandesPar_nom(NomRecherche);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();

        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        model.addAttribute("resultatsRechercheEnAttente", resultatsRechercheEnAttente);

        return "resultats-recherches-enAttente";
    }*/








}