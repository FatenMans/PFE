package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.LieuRepository;
import com.example.jwtauth.Entity.Lieu;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuService {

    @Autowired
    private LieuRepository lieuRepository;

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    public Lieu getLieuById(Long id) {
        return lieuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lieu not found with id: " + id));
    }

    public Lieu createLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    public Lieu updateLieu(Long id, Lieu updatedLieu) {
        Lieu existingLieu = getLieuById(id);
        existingLieu.setLieu(updatedLieu.getLieu());
        // Set other fields as needed
        return lieuRepository.save(existingLieu);
    }

    public void deleteLieu(Long id) {
        Lieu lieu = getLieuById(id);
        lieuRepository.delete(lieu);
    }
}

