package com.example.library.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
@Data
@Table("adherents")
public class Adherent extends Person{
    @Id
    private Long codeAdherent;
    private LocalDateTime date;

    public Adherent(String fname, String lname) {
        super(fname, lname);
        date =  LocalDateTime.now();
    }

    @Override
    public String toString() {
        return getFname()+"_"+getLname()+"_"+codeAdherent;
    }
}
