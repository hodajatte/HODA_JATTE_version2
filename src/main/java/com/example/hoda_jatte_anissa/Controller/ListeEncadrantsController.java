package com.example.hoda_jatte_anissa.Controller;

import com.example.hoda_jatte_anissa.Entity.Encadrant;
import com.example.hoda_jatte_anissa.Service.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListeEncadrantsController {

    @Autowired
    private EncadrantService encadrantService;

    @GetMapping("/liste-encadrants")
    public String listEncadrants(Model model) {
        List<Encadrant> encadrants = encadrantService.getAllEncadrants();
        model.addAttribute("listeEncadrants", encadrants);
        return "liste-encadrants"; // Assurez-vous que le nom du modèle correspond à votre template Thymeleaf
    }
    @GetMapping("/rechercher-encadrants")
    public String rechercherEncadrants(@RequestParam(name = "specialite") String specialite, Model model) {
        // Écrivez le code pour rechercher les demandes en fonction du nom ici
        List<Encadrant> encadrants = encadrantService.rechercherEncadrantParSpecialite(specialite); // Remplacez cela par votre logique de recherche réelle
        model.addAttribute("listeEncadrants", encadrants);
        return "liste-encadrants";
    }





}
