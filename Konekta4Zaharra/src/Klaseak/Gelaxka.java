package Klaseak;

public class Gelaxka {
	//atributuak
	protected char kolorea;

	//eraikitzailea(k)
	public Gelaxka() {
		this.kolorea=' ';
	}

	//gainontzeko metodoak
	public void gelaxkaEgikaritu(int pTxanda) {
		char kolorea=Konekta4.getNireKonekta4().getJokalaria(pTxanda).getKolorea();
		this.kolorea=kolorea;
	}
	
	public char getKolorea() {
		return this.kolorea;
	}
	
	public void setKolorea(char pKolorea) {
		this.kolorea=pKolorea;
	}
}