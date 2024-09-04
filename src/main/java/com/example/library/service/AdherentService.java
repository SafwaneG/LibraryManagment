package com.example.library.service;

import com.example.library.models.Adherent;
import com.example.library.repository.AdherentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdherentService {
    private final AdherentRepository adherentRepository;
    public AdherentService(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    public Optional<Adherent> searchAdherent(Long codeAdherent) {
        return adherentRepository.findById(codeAdherent);
    }

    public Adherent addAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public Iterable<Adherent> findAllAdherents() {
        return adherentRepository.findAll();
    }
}
