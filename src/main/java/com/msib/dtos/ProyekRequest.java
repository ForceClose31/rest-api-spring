package com.msib.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProyekRequest {

    private Proyek proyek;
    private List<LokasiId> lokasiIds;

    
    public ProyekRequest() {
    }

    @JsonCreator
    public ProyekRequest(
        @JsonProperty("proyek") Proyek proyek,
        @JsonProperty("lokasiIds") List<LokasiId> lokasiIds) {
        this.proyek = proyek;
        this.lokasiIds = lokasiIds;
    }

    public Proyek getProyek() {
        return proyek;
    }

    public void setProyek(Proyek proyek) {
        this.proyek = proyek;
    }

    public List<LokasiId> getLokasiIds() {
        return lokasiIds;
    }

    public void setLokasiIds(List<LokasiId> lokasiIds) {
        this.lokasiIds = lokasiIds;
    }

    
    public static class Proyek {
        private String namaProyek;
        private String client;
        private LocalDateTime tglMulai;
        private LocalDateTime tglSelesai;
        private String pimpinanProyek;
        private String keterangan;

        
        public Proyek() {
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

        public LocalDateTime getTglMulai() {
            return tglMulai;
        }

        public void setTglMulai(LocalDateTime tglMulai) {
            this.tglMulai = tglMulai;
        }

        public LocalDateTime getTglSelesai() {
            return tglSelesai;
        }

        public void setTglSelesai(LocalDateTime tglSelesai) {
            this.tglSelesai = tglSelesai;
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
    }

    
    public static class LokasiId {
        private Integer id;

        public LokasiId() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
