package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Decryption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    String pPassword;

    public String getpPassword() {
        return pPassword;
    }

    public void setpPassword(String pPassword) {
        this.pPassword = pPassword;
    }

}
