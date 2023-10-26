package com.nissanusa.helios.ownerservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Encryption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    String sPassword;

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

}
