package com.msib.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.msib.models.Proyek;
import com.msib.services.ProyekService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyek")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            JsonNode rootNode;
            try {
                String unescapedPayload = objectMapper.readValue(payload, String.class);
                rootNode = objectMapper.readTree(unescapedPayload);
            } catch (Exception e) {
                rootNode = objectMapper.readTree(payload);
            }

            JsonNode proyekNode = rootNode.path("proyek");
            JsonNode lokasiIdsNode = rootNode.path("lokasiIds");

            Proyek proyek = new Proyek();
            proyek.setNamaProyek(proyekNode.path("namaProyek").asText());
            proyek.setClient(proyekNode.path("client").asText());
            proyek.setTglMulai(proyekNode.path("tglMulai").asText());
            proyek.setTglSelesai(proyekNode.path("tglSelesai").asText());
            proyek.setPimpinanProyek(proyekNode.path("pimpinanProyek").asText());
            proyek.setKeterangan(proyekNode.path("keterangan").asText());

            List<Integer> lokasiIdsList = new ArrayList<>();
            if (lokasiIdsNode.isArray()) {
                Iterator<JsonNode> elements = lokasiIdsNode.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    lokasiIdsList.add(element.path("id").asInt());
                }
            }
            return ResponseEntity.ok(proyekService.saveProyek(proyek, lokasiIdsList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Integer id, @RequestBody String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

            JsonNode rootNode;
            try {
                String unescapedPayload = objectMapper.readValue(payload, String.class);
                rootNode = objectMapper.readTree(unescapedPayload);
            } catch (Exception e) {
                rootNode = objectMapper.readTree(payload);
            }

            JsonNode proyekNode = rootNode.path("proyek");
            JsonNode lokasiIdsNode = rootNode.path("lokasiIds");

            Proyek proyek = new Proyek();
            proyek.setId(id);
            proyek.setNamaProyek(proyekNode.path("namaProyek").asText());
            proyek.setClient(proyekNode.path("client").asText());
            proyek.setTglMulai(proyekNode.path("tglMulai").asText());
            proyek.setTglSelesai(proyekNode.path("tglSelesai").asText());
            proyek.setPimpinanProyek(proyekNode.path("pimpinanProyek").asText());
            proyek.setKeterangan(proyekNode.path("keterangan").asText());

            List<Integer> lokasiIdsList = new ArrayList<>();
            if (lokasiIdsNode.isArray()) {
                Iterator<JsonNode> elements = lokasiIdsNode.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    lokasiIdsList.add(element.path("id").asInt());
                }
            }

            return ResponseEntity.ok(proyekService.updateProyek(proyek, lokasiIdsList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Integer id) {
        proyekService.deleteProyek(id);
        return ResponseEntity.noContent().build();
    }
}
