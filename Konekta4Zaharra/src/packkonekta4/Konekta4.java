package packkonekta4;

import java.nio.charset.MalformedInputException;
import java.sql.Array;

public class Konekta4 {
	//atributuak
	private static Konekta4 nireKonekta4=null;
	private static Jokalaria[] jokalariak=new Jokalaria[2];
	private int txanda;

	//eraikitzailea(k)
	private Konekta4() {
		this.txanda=1;
	}

	//gainontzeko metodoak
	public static Konekta4 getNireKonekta4() {
		if (nireKonekta4==null) {
			nireKonekta4=new Konekta4();
		}
		return nireKonekta4;
	}
	
	private void jokoaHasieratu() {
		int bonbaKop=3;
		int eraldatuKop=3;
		int randomZut=0;
		int randomErrenk=0;
		int zutabea=0;
		int errenkada=0;
		while (bonbaKop>0) {
			randomZut=(int)(Math.random()*7);
			randomErrenk=(int)(Math.random()*6);
			if (Taula.getNireTaula().getGelaxka(randomZut, randomErrenk)==null) {
				Taula.getNireTaula().setGelaxka(randomZut, randomErrenk, 1);
				bonbaKop--;
			}
		}
		while (eraldatuKop>0) {
			randomZut=(int)(Math.random()*7);
			randomErrenk=(int)(Math.random()*6);
			if (Taula.getNireTaula().getGelaxka(randomZut, randomErrenk)==null) {
				Taula.getNireTaula().setGelaxka(randomZut, randomErrenk, 2);
				eraldatuKop--;
			}
		}
		while (zutabea<Taula.getNireTaula().getZutabeLuzera()) {
			while (errenkada<Taula.getNireTaula().getErrenkadaLuzera()) {
				if (Taula.getNireTaula().getGelaxka(zutabea, errenkada)==null) {
					Taula.getNireTaula().setGelaxka(zutabea, errenkada, 0);
				}
				errenkada++;
			}
			errenkada=0;
			zutabea++;
		}
	}
	
	public void partidaBatJolastu() {
		System.out.println("ONGI ETORRI KONEKTA 4 EXTREME EDITION-ERA!");
		System.out.println("");
		this.jokalariakInskribatu();
		System.out.println("PARTIDA HAS DADILA!");
		this.jokoaHasieratu();
		Taula.getNireTaula().inprimatuTaula();
		while (!this.amaituta()) {
			this.hurrengoTxanda();
		}
		this.emaitza();
	}
	
	private void jokalariakInskribatu() {
		System.out.println("Lehenengo jokalaria, zure izena idatzi:");
		String izena1=Teklatua.getNireTeklatua().irakurriString();
		Konekta4.jokalariak[0]=new Jokalaria(izena1, 'G');
		System.out.println("");
		System.out.println("Bigarren jokalaria, zure izena idatzi:");
		String izena2=Teklatua.getNireTeklatua().irakurriString();
		Konekta4.jokalariak[1]=new Jokalaria(izena2, 'H');
		System.out.println("");
	}
	
	private void hurrengoTxanda() {
		int txanda=this.norenTxanda();
		System.out.println(Konekta4.jokalariak[txanda].getIzena()+"-(r)en txanda:");
		int zutabePos;
		do {
			zutabePos=Teklatua.getNireTeklatua().irakurriOsoa();
			try {
				if (zutabePos<1 || zutabePos>7) {
					throw new ZutabeEzEgokia();
				}
				else if (Taula.getNireTaula().zutabBeteta(zutabePos-1)) {
					throw new ZutabeBeteta();
				}
			}
			catch (ZutabeEzEgokia e) {
				e.inprimatu();
			}
			catch (ZutabeBeteta e) {
				e.inprimatu();
			}
		} while (Taula.getNireTaula().zutabBeteta(zutabePos-1) || zutabePos<1 || zutabePos>7);
		int errenk=Taula.getNireTaula().getErrenkada(zutabePos-1);
		Taula.getNireTaula().fitxaKolorezAldatu(zutabePos-1, errenk, txanda);
		Taula.getNireTaula().inprimatuTaula();
		int komErabilgarria=Konekta4.jokalariak[txanda].getKomodinErabilgarria();
		if (!this.irabazleaDago() && komErabilgarria!=0) {
			System.out.println(Konekta4.jokalariak[txanda].getIzena()+", zure komodina erabili nahi duzu? (Bai 'B' edo ez 'E' aukeratu)");
			boolean komodinaErabili=this.erantzuna();
			System.out.println("");
			if (komodinaErabili) {
				try {
					if ((komErabilgarria==1 && this.bonbaErabiltzeaZentzurikEz(zutabePos-1, errenk)) || (komErabilgarria==2 && this.eraldatuErabiltzeaZentzurikEz(zutabePos-1, errenk))) {
						throw new KomodinaErabiltzeaZentzurikEz();
					}
				}
				catch (KomodinaErabiltzeaZentzurikEz e) {
					e.inprimatu();
					komodinaErabili=this.erantzuna();
				}
			}
			if (komodinaErabili) {
				if (komErabilgarria==1) {
					Taula.getNireTaula().ingurukoakDesagertu(zutabePos-1, errenk);
				}
				else if (komErabilgarria==2) {
					Taula.getNireTaula().azpikoFitxaKolorezAldatu(zutabePos-1, errenk);
				}
				Konekta4.jokalariak[txanda].setKomodinErabilgarria(0);
				Taula.getNireTaula().inprimatuTaula();
			}
		}
		if (!this.amaituta()) {
			this.txanda=this.txanda+1;
		}
	}
	
	private int norenTxanda() {
		int norenTxanda;
		if (this.txanda%2!=0) {
			norenTxanda=0;
		}
		else {
			norenTxanda=1;
		}
		return norenTxanda;
	}
	
	private boolean amaituta() {
		boolean amaituta=false;
		if (Taula.getNireTaula().beteta() || this.irabazleaDago()) {
			amaituta=true;
		}
		return amaituta;
	}
	
	private void emaitza() {
		if (this.irabazleaDago()) {
			String irabazlea=Konekta4.jokalariak[this.norenTxanda()].getIzena();
			System.out.println("Zorionak, "+irabazlea+"! Partida irabazi duzu!");
		}
		else {
			System.out.println("Partida bukatu da, baina ez da irabazlerik egon.");
		}
		System.out.println("Sakatu enter bukatzeko");
		Teklatua.getNireTeklatua().irakurriEnter();
	}
	
	private boolean erantzuna() {
		boolean erantzunarenEmaitza=false;
		boolean erantzunZuzena=false;
		char erantzuna=Teklatua.getNireTeklatua().irakurriChar();
		while (!erantzunZuzena) {
			if (erantzuna=='b' || erantzuna=='B') {
				erantzunarenEmaitza=true;
				erantzunZuzena=true;
			}
			else if (erantzuna=='e' || erantzuna=='E') {
				erantzunZuzena=true;
			}
			else {
				System.out.println("Erantzun egoki bat eman, mesedez.");
				erantzuna=Teklatua.getNireTeklatua().irakurriChar();
			}
		}
		return erantzunarenEmaitza;
	}
	
	private boolean bonbaErabiltzeaZentzurikEz(int pZutab, int pErrenk) {
		boolean zentzurikEz=false;
		if (Taula.getNireTaula().albokoakHutsik(pZutab, pErrenk) || Taula.getNireTaula().albokoakKoloreBerdina(pZutab, pErrenk)) {
			zentzurikEz=true;
		}
		return zentzurikEz;
	}
	
	private boolean eraldatuErabiltzeaZentzurikEz(int pZutab, int pErrenk) {
		boolean zentzurikEz=false;
		if (Taula.getNireTaula().azkenekoGelaxka(pErrenk) || Taula.getNireTaula().behekoaKoloreBerdina(pZutab, pErrenk)) {
			zentzurikEz=true;
		}
		return zentzurikEz;
	}
	
	private boolean irabazleaDago() {
		boolean irabazlea=false;
		char kolorea=Konekta4.jokalariak[this.norenTxanda()].getKolorea();
		if (Taula.getNireTaula().zutabeakBegiratu(kolorea) || Taula.getNireTaula().errenkadakBegiratu(kolorea) || Taula.getNireTaula().diagonalakBegiratu(kolorea)) {
			irabazlea=true;
		}
		return irabazlea;
	}
	
	public Jokalaria getJokalaria(int pTxanda) {
		return Konekta4.jokalariak[pTxanda];
	}
}