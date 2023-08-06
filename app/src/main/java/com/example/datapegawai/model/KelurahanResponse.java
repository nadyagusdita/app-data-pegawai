package com.example.datapegawai.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KelurahanResponse {
    @SerializedName("code")
    private String code;

    @SerializedName("messages")
    private String messages;

    @SerializedName("value")
    private List<Kelurahan> kelurahanList;

    public KelurahanResponse(String code, String messages, List<Kelurahan> kelurahanList) {
        this.code = code;
        this.messages = messages;
        this.kelurahanList = kelurahanList;
    }

    public class Kelurahan {
        @SerializedName("id")
        private String id;

        @SerializedName("id_kecamatan")
        private String idKecamatan;

        @SerializedName("name")
        private String name;

        public Kelurahan(String id, String idKecamatan, String name) {
            this.id = id;
            this.idKecamatan = idKecamatan;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdKecamatan() {
            return idKecamatan;
        }

        public void setIdKecamatan(String idKecamatan) {
            this.idKecamatan = idKecamatan;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<Kelurahan> getKelurahanList() {
        return kelurahanList;
    }

    public void setKelurahanList(List<Kelurahan> kelurahanList) {
        this.kelurahanList = kelurahanList;
    }
}

