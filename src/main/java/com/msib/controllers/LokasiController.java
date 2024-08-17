package com.msib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.msib.models.Lokasi;
import com.msib.services.LokasiService;

import java.util.List;

@RestController
@RequestMapping("/lokasi")
public class LokasiController {
    @Autowired
    private LokasiService lokasiService;

    @PostMapping
    public ResponseEntity<Lokasi> createLokasi(@RequestBody Lokasi lokasi) {
        return ResponseEntity.ok(lokasiService.saveLokasi(lokasi));
    }

    @GetMapping
    public ResponseEntity<List<Lokasi>> getAllLokasi() {
        return ResponseEntity.ok(lokasiService.getAllLokasi());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable Integer id, @RequestBody Lokasi lokasi) {
        lokasi.setId(id);
        return ResponseEntity.ok(lokasiService.updateLokasi(lokasi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokasi(@PathVariable Integer id) {
        lokasiService.deleteLokasi(id);
        return ResponseEntity.noContent().build();
    }
}