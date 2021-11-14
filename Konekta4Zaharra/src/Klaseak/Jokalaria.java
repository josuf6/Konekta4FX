package Klaseak;

public class Jokalaria {
	//atributuak
	private String izena;
	private char kolorea;
	private int komodinErabilgarria;

	//eraikitzailea(k)
	public Jokalaria(String pIzena, char pKolorea) {
		this.izena=pIzena;
		this.kolorea=pKolorea;
		this.komodinErabilgarria=0;
	}

	//gainontzeko metodoak
	public String getIzena() {
		return this.izena;
	}
	
	public char getKolorea() {
		return this.kolorea;
	}
	
	public int getKomodinErabilgarria() {
		return this.komodinErabilgarria;
	}
	
	public void setKomodinErabilgarria(int pKomEra) {
		this.komodinErabilgarria=pKomEra;
	}
}