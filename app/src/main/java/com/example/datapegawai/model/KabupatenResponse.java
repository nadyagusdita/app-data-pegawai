package com.example.datapegawai.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class KabupatenResponse {
    @SerializedName("code")
    private String code;

    @SerializedName("messages")
    private String messages;

    @SerializedName("value")
    private List<Kabupaten> kabupatenList;

    public KabupatenResponse(String code, String messages, List<Kabupaten> kabupatenList) {
        this.code = code;
        this.messages = messages;
        this.kabupatenList = kabupatenList;
    }

    public class Kabupaten {
        @SerializedName("id")
        private String id;

        @SerializedName("id_provinsi")
        private String idProvinsi;

        @SerializedName("name")
        private String name;

        public Kabupaten(String id, String idProvinsi, String name) {
            this.id = id;
            this.idProvinsi = idProvinsi;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdProvinsi() {
            return idProvinsi;
        }

        public void setIdProvinsi(String idProvinsi) {
            this.idProvinsi = idProvinsi;
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

    public List<Kabupaten> getKabupatenList() {
        return kabupatenList;
    }

    public void setKabupatenList(List<Kabupaten> kabupatenList) {
        this.kabupatenList = kabupatenList;
    }
}

