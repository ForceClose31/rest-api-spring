package com.msib.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Proyek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String namaProyek;
    private String client;
    private String tglMulai;
    private String tglSelesai;
    private String pimpinanProyek;
    private String keterangan;
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "proyek", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProyekLokasi> proyekLokasi = new ArrayList<>(); 


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String string) {
        this.tglMulai = string;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String string) {
        this.tglSelesai = string;
    }

    public String getPimpinanProyek() {
        return pimpinanProyek;
    }

    public void setPimpinanProyek(String pimpinanProyek) {
        this.pimpinanProyek = pimpinanProyek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ProyekLokasi> getProyekLokasi() {
        return proyekLokasi;
    }

    public void setProyekLokasi(List<ProyekLokasi> proyekLokasi) {
        this.proyekLokasi = proyekLokasi;
    }
}
