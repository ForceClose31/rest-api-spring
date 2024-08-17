package com.msib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.msib.models.Proyek;
import com.msib.services.ProyekService;

import java.util.List;

@RestController
@RequestMapping("/proyek")
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody Proyek proyek, @RequestParam List<Integer> lokasiIds) {
        return ResponseEntity.ok(proyekService.saveProyek(proyek, lokasiIds));
    }

    @GetMapping
    public ResponseEntity<List<Proyek>> getAllProyek() {
        return ResponseEntity.ok(proyekService.getAllProyek());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Integer id, @RequestBody Proyek proyek) {
        proyek.setId(id);
        return ResponseEntity.ok(proyekService.updateProyek(proyek));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Integer id) {
        proyekService.deleteProyek(id);
        return ResponseEntity.noContent().build();
    }
}
