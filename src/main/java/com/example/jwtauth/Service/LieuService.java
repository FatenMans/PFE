package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.LieuRepository;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Lieu;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Optional<Lieu> findByLieu(String lieu) {
        return lieuRepository.findByLieu(lieu);
    }

    public Lieu saveLieu(Lieu lieu) {
        Optional<Lieu> existingLieu = lieuRepository.findByLieu(lieu.getLieu());
        if (existingLieu.isPresent() && !existingLieu.get().getId().equals(lieu.getId())) {
            throw new IllegalArgumentException("Un lieu avec ce nom existe déjà.");
        }
        return lieuRepository.save(lieu);
    }

    public Lieu updateLieu(Long id, Lieu updatedLieu) {
        Lieu existingLieu = getLieuById(id);
        existingLieu.setLieu(updatedLieu.getLieu());
        existingLieu.setCreatedBy(updatedLieu.getCreatedBy());
        existingLieu.setCreationDate(updatedLieu.getCreationDate());
        existingLieu.setLastModifiedBy(updatedLieu.getLastModifiedBy());
        existingLieu.setLastModifiedDate(updatedLieu.getLastModifiedDate());
        // Set other fields as needed
        return lieuRepository.save(existingLieu);
    }

    public void deleteLieu(Long id) {
        Lieu lieu = getLieuById(id);
        lieuRepository.delete(lieu);
    }
}

