package Klaseak;

public class KomodinaErabiltzeaZentzurikEz extends Exception {
	//atributuak

	//eraikitzailea(k)
	public KomodinaErabiltzeaZentzurikEz() {
		super();
	}

	//gainontzeko metodoak
	public void inprimatu() {
		System.out.println("Ez da komenigarria zure komodina egoera honetan erabiltzea. Ziur al zaude? (Bai 'B' edo ez 'E' aukeratu)");
	}
}
