package com.example.datapegawai.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProvinsiResponse {
    @SerializedName("code")
    private String code;

    @SerializedName("messages")
    private String messages;

    @SerializedName("value")
    private List<Provinsi> provinsiList;

    public class Provinsi {
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

    public List<Provinsi> getProvinsiList() {
        return provinsiList;
    }

    public void setProvinsiList(List<Provinsi> provinsiList) {
        this.provinsiList = provinsiList;
    }
}

