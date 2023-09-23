package com.example.hoda_jatte_anissa.Controller;
import com.example.hoda_jatte_anissa.Entity.Demande;
import com.example.hoda_jatte_anissa.Service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class DemandeController {
    //*notification si la  demande à bien  enregistre
    private boolean isDemandeEnregistree = false;

    @Autowired
    private DemandeService demandeService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/demandeStage")
    public String showDemandeForm(Model model) {
        model.addAttribute("demande", new Demande());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        String username = authentication.getName();
        model.addAttribute("userRole", userRole);
        model.addAttribute("username", username);
        /*model.addAttribute("showNotification", isDemandeEnregistree);*/
        return "DemandeStage";
    }

    @PostMapping("/demandeStage")
    public String submitDemandeForm(@ModelAttribute("demande") Demande demande,
                                    @RequestParam("cvFile") MultipartFile cvFile,
                                    @RequestParam("lettreMotivationFile") MultipartFile lettreMotivationFile,
                                    @RequestParam("dateDebut") String dateDebutString,
                                    @RequestParam("dateFin") String dateFinString,
                                    @RequestParam("date_naissance") String dateNaissanceString,
                                    Model model) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateDebut = LocalDate.parse(dateDebutString, dateFormatter);
        LocalDate dateFin = LocalDate.parse(dateFinString, dateFormatter);
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceString, dateFormatter);

        demande.setDateDebut(dateDebut);
        demande.setDateFin(dateFin);
        demande.setDate_naissance(dateNaissance);

        try {
            // Traitement des fichiers CV et lettre de motivation
            if (!cvFile.isEmpty()) {
                String uniqueCVFileName = generateUniqueFileName(cvFile.getOriginalFilename());
                Path cvFilePath = Paths.get(uploadPath + uniqueCVFileName);
                Files.write(cvFilePath, cvFile.getBytes());
                demande.setCvFile(cvFile);
            }

            if (!lettreMotivationFile.isEmpty()) {
                String uniqueLettreMotivationFileName = generateUniqueFileName(lettreMotivationFile.getOriginalFilename());
                Path lettreMotivationFilePath = Paths.get(uploadPath + uniqueLettreMotivationFileName);
                Files.write(lettreMotivationFilePath, lettreMotivationFile.getBytes());
                demande.setLettreMotivationFile(lettreMotivationFile);
            }

            demandeService.saveDemande(demande);

            /* model.addAttribute("notification", true);*/ // Vous pouvez ajouter cet attribut pour activer la notification côté vue
            /*return "/demandeStage";*/
           return  "redirect:/demandeStage?success=true";

        } catch (IOException e) {
            // Gérer l'exception en conséquence (par exemple, afficher un message d'erreur)
            model.addAttribute("error", "Une erreur s'est produite lors du traitement des fichiers.");
            return "DemandeStage"; // Retourner à la page du formulaire avec le message d'erreur
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        // Générer un nom unique en utilisant un horodatage ou un identifiant unique
        return "unique_" + System.currentTimeMillis() + "_" + originalFileName;
    }
}
