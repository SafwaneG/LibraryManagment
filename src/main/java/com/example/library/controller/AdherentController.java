package com.example.library.controller;

import com.example.library.models.Adherent;
import com.example.library.service.AdherentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/library/adherents")
public class AdherentController {
    private final AdherentService adherentService;
    public AdherentController(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    @PostMapping
    public Adherent saveAdherent(@RequestBody Adherent adherent) {
        return adherentService.addAdherent(adherent);
    }
    @GetMapping
    public Iterable<Adherent> findAllAdherents() {
        return adherentService.findAllAdherents();
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findAdherent(@RequestParam(name = "id", required = false) Long id,
                                           @RequestParam(name = "title", required = false) String name)
    {
        if(id != null) {
            Optional<Adherent> adherent = adherentService.searchAdherent(id);
            return adherent.isPresent() ? ResponseEntity.ok().body(adherent) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No adherent with this id found!");
        }
        return null;
    }
}

