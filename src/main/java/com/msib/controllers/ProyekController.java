package com.msib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.msib.models.Proyek;
import com.msib.services.ProyekService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Integer id) {
        Optional<Proyek> proyek = proyekService.getProyekById(id);
        return proyek.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
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
