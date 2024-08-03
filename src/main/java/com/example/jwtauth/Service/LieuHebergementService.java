package com.example.jwtauth.Service;


import com.example.jwtauth.DAO.LieuHebergementRepository;
import com.example.jwtauth.Entity.LieuHebergement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LieuHebergementService {

    @Autowired
    private LieuHebergementRepository lieuHebergementRepository;

    public List<LieuHebergement> getAllLieuxHebergement() {
        return lieuHebergementRepository.findAll();
    }

    public Optional<LieuHebergement> getLieuHebergementById(Long id) {
        return lieuHebergementRepository.findById(id);
    }

    public Optional<LieuHebergement> findByLieuheb(String lieuheb) {
        return lieuHebergementRepository.findByLieuheb(lieuheb);
    }

    public LieuHebergement saveLieuHebergement(LieuHebergement lieuHebergement) {
        Optional<LieuHebergement> existingLieuHebergement = lieuHebergementRepository.findByLieuheb(lieuHebergement.getLieuheb());
        if (existingLieuHebergement.isPresent() && !existingLieuHebergement.get().getId().equals(lieuHebergement.getId())) {
            throw new IllegalArgumentException("Un lieu d'hébergement avec ce nom existe déjà.");
        }
        return lieuHebergementRepository.save(lieuHebergement);
    }


    public void deleteLieuHebergement(Long id) {
        lieuHebergementRepository.deleteById(id);
    }
}


