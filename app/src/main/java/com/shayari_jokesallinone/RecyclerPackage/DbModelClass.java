package com.shayari_jokesallinone.RecyclerPackage;

import java.io.Serializable;

public class DbModelClass implements Serializable {
    String shayari;

    public DbModelClass(String shayari) {
        this.shayari = shayari;
    }

    public String getShayari() {
        return shayari;
    }

    public void setShayari(String shayari) {
        this.shayari = shayari;
    }
}
