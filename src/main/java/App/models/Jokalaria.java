package App.models;

public class Jokalaria {

    private final String izena;
    private final char kolorea;
    private int komodinErabilgarria;
    private int mugimenduak;

    public Jokalaria(String pIzena, char pKolorea) {
        this.izena=pIzena;
        this.kolorea=pKolorea;
        this.komodinErabilgarria=0;
    }

    public String getIzena() {
        return this.izena;
    }

    public char getKolorea() {
        return this.kolorea;
    }

    public int getMugimenduak() {
        return this.mugimenduak;
    }

    public void addMugimendu() {
        this.mugimenduak++;
    }

    public int getKomodinErabilgarria() {
        return this.komodinErabilgarria;
    }

    public void setKomodinErabilgarria(int pKomEra) {
        this.komodinErabilgarria = pKomEra;
    }
}
