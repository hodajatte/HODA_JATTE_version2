package com.example.hoda_jatte_anissa.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "demande_etats")
public class DemandeEtat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;

    private String etat; // Acceptée, Refusée, En attente


    // Constructeurs, getters, setters, etc.
}
