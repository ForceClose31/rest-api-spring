package com.msib.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msib.models.Lokasi;
import com.msib.models.Proyek;
import com.msib.models.ProyekLokasi;
import com.msib.repos.LokasiRepository;
import com.msib.repos.ProyekRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProyekService {
    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Proyek saveProyek(Proyek proyek, List<Integer> lokasiIds) {
        proyek.setCreatedAt(LocalDateTime.now());
        Proyek savedProyek = proyekRepository.save(proyek);

        for (Integer lokasiId : lokasiIds) {
            Lokasi lokasi = lokasiRepository.findById(lokasiId).orElseThrow();
            ProyekLokasi proyekLokasi = new ProyekLokasi();
            proyekLokasi.setProyek(savedProyek);
            proyekLokasi.setLokasi(lokasi);
            savedProyek.getProyekLokasi().add(proyekLokasi);
        }

        return proyekRepository.save(savedProyek);
    }

    public Optional<Proyek> getProyekById(Integer id) {
        return proyekRepository.findById(id);
    }

    public Proyek updateProyek(Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    public void deleteProyek(Integer id) {
        proyekRepository.deleteById(id);
    }
}
