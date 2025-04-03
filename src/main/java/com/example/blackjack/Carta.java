package com.example.blackjack;

public class Carta {
    int valore;
    String seme;
    String percorso;

    public Carta(int valore, String seme) {
        this.valore = valore;
        this.seme = seme;
        this.percorso = "/images/" + seme + " (" + valore + ").png"; // Adjust the path as needed
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public String getSeme() {
        return seme;
    }

    public void setSeme(String seme) {
        this.seme = seme;
    }

    public String getPercorso() {
        return percorso;
    }

    public void setPercorso(String percorso) {
        this.percorso = percorso;
    }
}