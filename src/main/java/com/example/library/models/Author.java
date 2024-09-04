package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table("authors")
public class Author extends Person{
    @Id
    private Long authorCode;

    public Author(String lname, String fname) {
        super(lname, fname);
    }
}
