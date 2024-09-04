package com.example.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
public class Adherent extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeAdherent;
    private LocalDateTime date;
    public Adherent() {
        date =  LocalDateTime.now();
    }


    @Override
    public String toString() {
        return getFname()+"_"+getLname()+"_"+codeAdherent;
    }
}
