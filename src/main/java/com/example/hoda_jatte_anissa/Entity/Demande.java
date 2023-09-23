package com.example.hoda_jatte_anissa.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demandes_stage")
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String tel;


    private LocalDate dateDebut;
    private LocalDate dateFin;


    private  LocalDate date_naissance;
    @Transient
    private MultipartFile cvFile;
    @Transient
    private MultipartFile lettreMotivationFile;

    private String email;

    private String filiere;
    private String etablissement;

    private String niveau_scolaire;
    private String genre;
    private String type;
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private List<DemandeEtat> etats = new ArrayList<>();


}
