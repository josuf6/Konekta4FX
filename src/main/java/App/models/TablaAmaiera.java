package App.models;

public class TablaAmaiera {
    private String izena;
    private int puntuak;

    public TablaAmaiera(String izena, int puntuak) {
        this.izena = izena;
        this.puntuak = puntuak;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }
}
