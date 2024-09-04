package com.example.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Author extends Person{
    @Id
    private Long authorCode;

    public Author(String lname, String fname) {
        super(lname, fname);
    }
}
