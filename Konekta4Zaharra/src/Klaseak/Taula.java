package Klaseak;

public class Taula {
	//atributuak
	private static Taula nireTaula=null;
	private static Gelaxka[][] taula=new Gelaxka[7][6];

	//eraikitzailea(k)
	private Taula() {
	}

	//gainontzeko metodoak
	public static Taula getNireTaula() {
		if (nireTaula==null) {
			nireTaula=new Taula();
		}
		return nireTaula;
	}
	
	public void setGelaxka(int pZutab, int pErrenk, int pId) {
		if (pId==0) {
			Taula.taula[pZutab][pErrenk]=new Gelaxka();
		}
		else if (pId==1) {
			Taula.taula[pZutab][pErrenk]=new Bonba();
		}
		else {
			Taula.taula[pZutab][pErrenk]=new Eraldatu();
		}
	}
	
	public int getZutabeLuzera() {
		return Taula.taula.length;
	}
	
	public int getErrenkadaLuzera() {
		return Taula.taula[0].length;
	}
	
	public boolean beteta() {
		boolean beteta=true;
		int zutabe=0;
		while (beteta && zutabe<this.getZutabeLuzera()) {
			if (!this.zutabBeteta(zutabe)) {
				beteta=false;
			}
			zutabe++;
		}
		return beteta;
	}
	
	public boolean zutabBeteta(int pZutab) {
		boolean beteta=true;
		int errenkada=0;
		if (pZutab>=0 && pZutab<Taula.taula.length) {
			while (beteta && errenkada<this.getErrenkadaLuzera()) {
				if (Taula.taula[pZutab][errenkada].getKolorea()==' ' || Taula.taula[pZutab][errenkada].getKolorea()=='K') {
					beteta=false;
				}
				errenkada++;
			}
		}
		else {
			beteta=false;
		}
		return beteta;
	}
	
	public void fitxaKolorezAldatu(int pZutab, int pErrenk, int pTxanda) {
		Gelaxka gelaxka=Taula.taula[pZutab][pErrenk];
		if (gelaxka instanceof Bonba) {
			Konekta4.getNireKonekta4().getJokalaria(pTxanda).setKomodinErabilgarria(1);
		}
		else if (gelaxka instanceof Eraldatu) {
			Konekta4.getNireKonekta4().getJokalaria(pTxanda).setKomodinErabilgarria(2);
		}
		Taula.taula[pZutab][pErrenk]=new Gelaxka();
		Taula.taula[pZutab][pErrenk].gelaxkaEgikaritu(pTxanda);
	}
	
	public int getErrenkada(int pZutab) {
		int errenkada=Taula.nireTaula.getErrenkadaLuzera();
		boolean aurkituta=false;
		while (!aurkituta && errenkada>0) {
			errenkada--;
			if (Taula.taula[pZutab][errenkada].getKolorea()==' ' || Taula.taula[pZutab][errenkada].getKolorea()=='K') {
				aurkituta=true;
			}
		}
		return errenkada;
	}
	
	public boolean albokoakHutsik(int pZutab, int pErrenk) {
		boolean hutsik=false;
		if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
			if (pZutab==0) {
				if (Taula.taula[pZutab+1][pErrenk].getKolorea()==' ' || Taula.taula[pZutab+1][pErrenk].getKolorea()=='K') {
					hutsik=true;
				}
			}
			else if (pZutab==this.getZutabeLuzera()-1) {
				if (Taula.taula[pZutab-1][pErrenk].getKolorea()==' ' || Taula.taula[pZutab-1][pErrenk].getKolorea()=='K') {
					hutsik=true;
				}
			}
			else {
				if ((Taula.taula[pZutab+1][pErrenk].getKolorea()==' ' || Taula.taula[pZutab+1][pErrenk].getKolorea()=='K') && (Taula.taula[pZutab-1][pErrenk].getKolorea()==' ' || Taula.taula[pZutab-1][pErrenk].getKolorea()=='K')) {
					hutsik=true;
				}
			}
		}
		return hutsik;
	}
	
	public boolean albokoakKoloreBerdina(int pZutab, int pErrenk) {
		boolean berdina=false;
		char kolorea=Taula.nireTaula.getGelaxka(pZutab, pErrenk).getKolorea();
		if (pZutab==0) {
			if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
				if (Taula.taula[pZutab+1][pErrenk].getKolorea()==kolorea) {
					berdina=true;
				}
			}
			else if (Taula.taula[pZutab+1][pErrenk].getKolorea()==kolorea || Taula.taula[pZutab][pErrenk+1].getKolorea()==kolorea) {
				berdina=true;
			}
		}
		else if (pZutab==this.getZutabeLuzera()-1) {
			if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
				if (Taula.taula[pZutab-1][pErrenk].getKolorea()==kolorea) {
					berdina=true;
				}
			}
			else if (Taula.taula[pZutab-1][pErrenk].getKolorea()==kolorea || Taula.taula[pZutab][pErrenk+1].getKolorea()==kolorea) {
				berdina=true;
			}
		}
		else {
			if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
				if (Taula.taula[pZutab+1][pErrenk].getKolorea()==kolorea || Taula.taula[pZutab-1][pErrenk].getKolorea()==kolorea) {
					berdina=true;
				}
			}
			else if (Taula.taula[pZutab+1][pErrenk].getKolorea()==kolorea || Taula.taula[pZutab-1][pErrenk].getKolorea()==kolorea || Taula.taula[pZutab][pErrenk+1].getKolorea()==kolorea) {
				berdina=true;
			}
		}
		return berdina;
	}
	
	public boolean azkenekoGelaxka(int pErrenk) {
		boolean azkena=false;
		if(pErrenk==this.getErrenkadaLuzera()-1){
			azkena=true;
		}
		return azkena;
	}
	
	public boolean behekoaKoloreBerdina(int pZutab, int pErrenk) {
		boolean berdina=false;
		if (!this.azkenekoGelaxka(pErrenk)) {
			if (Taula.nireTaula.getGelaxka(pZutab, pErrenk).getKolorea()==Taula.nireTaula.getGelaxka(pZutab, pErrenk+1).getKolorea()) {
				berdina=true;
			}
		}
		return berdina;
	}
	
	public boolean zutabeakBegiratu(char pKolorea) {
		boolean irabazlea=false;
		int zutabea=0;
		int errenkada=0;
		int jarraian=0;
		boolean aukera=true;
		while (jarraian<4 && zutabea<this.getZutabeLuzera()) {
			while (jarraian<4 && aukera) {
				if (Taula.taula[zutabea][errenkada].getKolorea()==pKolorea) {
					jarraian++;
				}
				else {
					jarraian=0;
				}
				errenkada++;
				try {
					if (jarraian==0 && errenkada>this.getErrenkadaLuzera()-4) {
						throw new AukerarikEz();
					}
				}
				catch (AukerarikEz e) {
					aukera=false;
				}
			}
			errenkada=0;
			aukera=true;
			zutabea++;
		}
		if (jarraian==4) {
			irabazlea=true;
		}
		return irabazlea;
	}
	
	public boolean errenkadakBegiratu(char pKolorea) {
		boolean irabazlea=false;
		int zutabea=0;
		int errenkada=0;
		int jarraian=0;
		boolean aukera=true;
		while (jarraian<4 && errenkada<this.getErrenkadaLuzera()) {
			while (jarraian<4 && aukera) {
				if (Taula.taula[zutabea][errenkada].getKolorea()==pKolorea) {
					jarraian++;
				}
				else {
					jarraian=0;
				}
				zutabea++;
				try {
					if (jarraian==0 && zutabea>this.getZutabeLuzera()-4) {
						throw new AukerarikEz();
					}
				}
				catch (AukerarikEz e) {
					aukera=false;
				}
			}
			zutabea=0;
			aukera=true;
			errenkada++;
		}
		if (jarraian==4) {
			irabazlea=true;
		}
		return irabazlea;
	}
	
	public boolean diagonalakBegiratu(char pKolorea) {
		boolean irabazlea=false;
		int zutabea=0;
		int errenkada=0;
		int zutabeAux=0;
		int errenkadaAux=0;
		int jarraian=0;
		boolean aukera=true;
		while (jarraian<4 && errenkada<this.getErrenkadaLuzera()-4) {
			while (jarraian<4 && zutabea<this.getZutabeLuzera()) {
				if (zutabea<this.getZutabeLuzera()-3) {
					while (jarraian<4 && aukera) {
						if (Taula.taula[zutabea+zutabeAux][errenkada+errenkadaAux].getKolorea()==pKolorea) {
							jarraian++;
						}
						else {
							jarraian=0;
						}
						zutabeAux++;
						errenkadaAux++;
						try {
							if (jarraian==0 && (zutabea+zutabeAux>this.getZutabeLuzera()-4 || errenkada+errenkadaAux>this.getErrenkadaLuzera()-4)) {
								throw new AukerarikEz();
							}
						}
						catch (AukerarikEz e) {
							aukera=false;
						}
					}
					zutabeAux=0;
					errenkadaAux=0;
				}
				aukera=true;
				if (jarraian==0 && zutabea>2) {
					while (jarraian<4 && aukera) {
						if (Taula.taula[zutabea-zutabeAux][errenkada+errenkadaAux].getKolorea()==pKolorea) {
							jarraian++;
						}
						else {
							jarraian=0;
						}
						zutabeAux++;
						errenkadaAux++;
						try {
							if (jarraian==0 && (zutabea-zutabeAux<3 || errenkada+errenkadaAux>this.getErrenkadaLuzera()-4)) {
								throw new AukerarikEz();
							}
						}
						catch (AukerarikEz e) {
							aukera=false;
						}
					}
					zutabeAux=0;
					errenkadaAux=0;
				}
				aukera=true;
				zutabea++;
			}
			zutabea=0;
			errenkada++;
		}
		if (jarraian==4) {
			irabazlea=true;
		}
		return irabazlea;
	}
	
	public void ingurukoakDesagertu(int pZutab, int pErrenk) {
		if (!Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
			Taula.nireTaula.getGelaxka(pZutab, pErrenk+1).setKolorea(' ');
		}
		if (pZutab>0) {
			Taula.nireTaula.getGelaxka(pZutab-1, pErrenk).setKolorea(' ');
		}
		if (pZutab<this.getZutabeLuzera()-1) {
			Taula.nireTaula.getGelaxka(pZutab+1, pErrenk).setKolorea(' ');
		}
		Taula.nireTaula.egokituTaula(pZutab, pErrenk);
	}
	
	public void azpikoFitxaKolorezAldatu(int pZutab, int pErrenk) {
		if (!Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
			Gelaxka gelaxka=Taula.nireTaula.getGelaxka(pZutab, pErrenk+1);
			if (gelaxka.getKolorea()=='G') {
				gelaxka.setKolorea('H');
			}
			else {
				gelaxka.setKolorea('G');
			}
		}
	}
	
	private void egokituTaula(int pZutab, int pErrenk) {
		if (!Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
			Taula.nireTaula.egokituZutabea(pZutab, pErrenk+1);
		}
		if (pZutab>0) {
			Taula.nireTaula.egokituZutabea(pZutab-1, pErrenk);
		}
		if (pZutab<this.getZutabeLuzera()-1) {
			Taula.nireTaula.egokituZutabea(pZutab+1, pErrenk);
		}
	}
	
	private void egokituZutabea(int pZutab, int pErrenk) {
		boolean amaituta=false;
		while (!amaituta) {
			if (pErrenk==0) {
				amaituta=true;
			}
			if (!amaituta) {
				if (Taula.taula[pZutab][pErrenk-1].getKolorea()!=' ' && Taula.taula[pZutab][pErrenk-1].getKolorea()!='K') {
					Taula.taula[pZutab][pErrenk].setKolorea(Taula.taula[pZutab][pErrenk-1].getKolorea());
					pErrenk--;
				}
				else {
					amaituta=true;
				}
			}
		}
		if (Taula.taula[pZutab][pErrenk].getKolorea()!=' ' && Taula.taula[pZutab][pErrenk].getKolorea()!='K') {
			Taula.taula[pZutab][pErrenk].setKolorea(' ');
		}
	}
	
	public Gelaxka getGelaxka(int pZutab, int pErrenk) {
		return Taula.taula[pZutab][pErrenk];
	}
	
	public void inprimatuTaula() {
		System.out.println("");
		System.out.println("   1   2   3   4   5   6   7");
		System.out.println(" _____________________________");
		for (int i=0;i<this.getErrenkadaLuzera();i++) {
			for (int j=0;j<this.getZutabeLuzera();j++) {
				System.out.print(" | "+taula[j][i].getKolorea());
			}
			System.out.println(" |");
			System.out.println(" _____________________________");
		}
		String komodin1;
		String komodin2;
		if (Konekta4.getNireKonekta4().getJokalaria(0).getKomodinErabilgarria()==0) {
			komodin1="Bat ere ez";
		}
		else if (Konekta4.getNireKonekta4().getJokalaria(0).getKomodinErabilgarria()==1) {
			komodin1="Bonba";
		}
		else {
			komodin1="Eraldatu";
		}
		if (Konekta4.getNireKonekta4().getJokalaria(1).getKomodinErabilgarria()==0) {
			komodin2="Bat ere ez";
		}
		else if (Konekta4.getNireKonekta4().getJokalaria(1).getKomodinErabilgarria()==1) {
			komodin2="Bonba";
		}
		else {
			komodin2="Eraldatu";
		}
		System.out.println("");
		System.out.println(Konekta4.getNireKonekta4().getJokalaria(0).getIzena()+"-(r)en komodin erabilgarria: "+komodin1);
		System.out.println(Konekta4.getNireKonekta4().getJokalaria(1).getIzena()+"-(r)en komodin erabilgarria: "+komodin2);
		System.out.println("");
	}
}
