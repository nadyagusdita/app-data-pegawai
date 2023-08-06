package com.example.datapegawai.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KecamatanResponse {
    @SerializedName("code")
    private String code;

    @SerializedName("messages")
    private String messages;

    @SerializedName("value")
    private List<Kecamatan> kecamatanList;

    public KecamatanResponse(String code, String messages, List<Kecamatan> kecamatanList) {
        this.code = code;
        this.messages = messages;
        this.kecamatanList = kecamatanList;
    }

    public class Kecamatan {
        @SerializedName("id")
        private String id;

        @SerializedName("id_kabupaten")
        private String idKabupaten;

        @SerializedName("name")
        private String name;

        public Kecamatan(String id, String idKabupaten, String name) {
            this.id = id;
            this.idKabupaten = idKabupaten;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdKabupaten() {
            return idKabupaten;
        }

        public void setIdKabupaten(String idKabupaten) {
            this.idKabupaten = idKabupaten;
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

    public List<Kecamatan> getKecamatanList() {
        return kecamatanList;
    }

    public void setKecamatanList(List<Kecamatan> kecamatanList) {
        this.kecamatanList = kecamatanList;
    }
}

