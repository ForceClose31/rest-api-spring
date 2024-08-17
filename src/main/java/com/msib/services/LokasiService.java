package com.msib.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msib.models.Lokasi;
import com.msib.repos.LokasiRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LokasiService {
    @Autowired
    private LokasiRepository lokasiRepository;

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Lokasi saveLokasi(Lokasi lokasi) {
        if (lokasi.getNamaLokasi() == null || lokasi.getNegara() == null) {
            throw new IllegalArgumentException("NamaLokasi and Negara cannot be null");
        }
        lokasi.setCreatedAt(LocalDateTime.now());
        try {
            return lokasiRepository.save(lokasi);
        } catch (Exception e) {
            throw new RuntimeException("Error saving lokasi", e);
        }
    }

    public Optional<Lokasi> getLokasiById(Integer id) {
        return lokasiRepository.findById(id);
    }

    public Lokasi updateLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public void deleteLokasi(Integer id) {
        lokasiRepository.deleteById(id);
    }
}

