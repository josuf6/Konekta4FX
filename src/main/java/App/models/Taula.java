package App.models;

import App.Konekta4FX;

public class Taula {

    private static Taula nireTaula = null;
    private static Gelaxka[][] taula = new Gelaxka[7][6];
    private int azkenekoFitxaZutabea;
    private int azkenekoFitxaErrenkada;

    private Taula() {}

    public static Taula getNireTaula() {
        if (nireTaula == null) {
            nireTaula=new Taula();
        }
        return nireTaula;
    }

    public Gelaxka getGelaxka(int pZutab, int pErrenk) {
        return taula[pZutab][pErrenk];
    }

    public int getZutabeLuzera() {
        return taula.length;
    }

    public int getErrenkadaLuzera() {
        return taula[0].length;
    }

    public int getErrenkada(int pZutab) {
        int errenkada = nireTaula.getErrenkadaLuzera();
        boolean aurkituta = false;
        while (!aurkituta && errenkada > 0) {
            errenkada--;
            if (taula[pZutab][errenkada].getKolorea() == ' ' || taula[pZutab][errenkada].getKolorea() == 'K') {
                aurkituta=true;
            }
        }
        return errenkada;
    }

    public int getAzkenekoFitxaZutabea() {
        return this.azkenekoFitxaZutabea;
    }

    public int getAzkenekoFitxaErrenkada() {
        return this.azkenekoFitxaErrenkada;
    }

    public void setGelaxka(int pZutab, int pErrenk, int pId) {
        if (pId==0) {
            taula[pZutab][pErrenk]=new Gelaxka();
        }
        else if (pId==1) {
            taula[pZutab][pErrenk]=new Bonba();
        }
        else {
            taula[pZutab][pErrenk]=new Eraldatu();
        }
    }

    public void setAzkenekoFitxaZutabea(int pZutab) {
        this.azkenekoFitxaZutabea = pZutab;
    }

    public void setAzkenekoFitxaErrenkada(int pErrenk) {
        this.azkenekoFitxaErrenkada = pErrenk;
    }

    public void taulaBerria() {
        taula=new Gelaxka[7][6];
    }

    public boolean zutabBeteta(int pZutab) {
        boolean beteta = true;
        int errenkada = 0;
        while (beteta && errenkada < this.getErrenkadaLuzera()) {
            if (Taula.taula[pZutab][errenkada].getKolorea() == ' ' || Taula.taula[pZutab][errenkada].getKolorea() == 'K') {
                beteta = false;
            }
            errenkada++;
        }
        return beteta;
    }

    public boolean beteta() {
        boolean beteta = true;
        int zutabe = 0;
        while (beteta && zutabe < this.getZutabeLuzera()) {
            if (!this.zutabBeteta(zutabe)) {
                beteta = false;
            }
            zutabe++;
        }
        return beteta;
    }

    public boolean zutabeakBegiratu(char pKolorea) {
        boolean irabazlea = false;
        int zutabea = 0;
        int errenkada = 0;
        int jarraian = 0;
        boolean aukera = true;
        while (jarraian < 4 && zutabea < this.getZutabeLuzera()) {
            while (jarraian < 4 && aukera) {
                if (Taula.taula[zutabea][errenkada].getKolorea() == pKolorea) {
                    jarraian++;
                }
                else {
                    jarraian = 0;
                }
                errenkada++;
                try {
                    if (jarraian == 0 && errenkada > this.getErrenkadaLuzera() - 4) {
                        throw new AukerarikEz();
                    }
                }
                catch (AukerarikEz e) {
                    aukera = false;
                }
            }
            errenkada = 0;
            aukera = true;
            zutabea++;
        }
        if (jarraian == 4) {
            irabazlea = true;
        }
        return irabazlea;
    }

    public boolean errenkadakBegiratu(char pKolorea) {
        boolean irabazlea = false;
        int zutabea = 0;
        int errenkada = 0;
        int jarraian = 0;
        boolean aukera = true;
        while (jarraian < 4 && errenkada < this.getErrenkadaLuzera()) {
            while (jarraian < 4 && aukera) {
                if (Taula.taula[zutabea][errenkada].getKolorea() == pKolorea) {
                    jarraian++;
                }
                else {
                    jarraian = 0;
                }
                zutabea++;
                try {
                    if (jarraian == 0 && zutabea > this.getZutabeLuzera() - 4) {
                        throw new AukerarikEz();
                    }
                }
                catch (AukerarikEz e) {
                    aukera = false;
                }
            }
            zutabea = 0;
            aukera = true;
            errenkada++;
        }
        if (jarraian == 4) {
            irabazlea = true;
        }
        return irabazlea;
    }

    public boolean diagonalakBegiratu(char pKolorea) {
        boolean irabazlea = false;
        int zutabea = 0;
        int errenkada = 0;
        int zutabeAux = 0;
        int errenkadaAux = 0;
        int jarraian = 0;
        boolean aukera = true;
        while (jarraian < 4 && errenkada < this.getErrenkadaLuzera() - 4) {
            while (jarraian < 4 && zutabea < this.getZutabeLuzera()) {
                if (zutabea < this.getZutabeLuzera() - 3) {
                    while (jarraian < 4 && aukera) {
                        if (Taula.taula[zutabea + zutabeAux][errenkada + errenkadaAux].getKolorea() == pKolorea) {
                            jarraian++;
                        }
                        else {
                            jarraian = 0;
                        }
                        zutabeAux++;
                        errenkadaAux++;
                        try {
                            if (jarraian == 0 && (zutabea + zutabeAux > this.getZutabeLuzera() - 4 || errenkada + errenkadaAux > this.getErrenkadaLuzera() - 4)) {
                                throw new AukerarikEz();
                            }
                        }
                        catch (AukerarikEz e) {
                            aukera = false;
                        }
                    }
                    zutabeAux = 0;
                    errenkadaAux = 0;
                }
                aukera = true;
                if (jarraian == 0 && zutabea > 2) {
                    while (jarraian < 4 && aukera) {
                        if (Taula.taula[zutabea - zutabeAux][errenkada + errenkadaAux].getKolorea() == pKolorea) {
                            jarraian++;
                        }
                        else {
                            jarraian = 0;
                        }
                        zutabeAux++;
                        errenkadaAux++;
                        try {
                            if (jarraian == 0 && (zutabea - zutabeAux < 3 || errenkada + errenkadaAux > this.getErrenkadaLuzera() - 4)) {
                                throw new AukerarikEz();
                            }
                        }
                        catch (AukerarikEz e) {
                            aukera = false;
                        }
                    }
                    zutabeAux = 0;
                    errenkadaAux = 0;
                }
                aukera = true;
                zutabea++;
            }
            zutabea = 0;
            errenkada++;
        }
        if (jarraian == 4) {
            irabazlea = true;
        }
        return irabazlea;
    }

    public boolean albokoakHutsik(int pZutab, int pErrenk) {
        boolean hutsik = false;
        if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
            if (pZutab == 0) {
                if (Taula.taula[pZutab + 1][pErrenk].getKolorea() == ' ' || Taula.taula[pZutab + 1][pErrenk].getKolorea() == 'K') {
                    hutsik=true;
                }
            }
            else if (pZutab == this.getZutabeLuzera()-1) {
                if (Taula.taula[pZutab - 1][pErrenk].getKolorea() == ' ' || Taula.taula[pZutab - 1][pErrenk].getKolorea() == 'K') {
                    hutsik = true;
                }
            }
            else {
                if ((Taula.taula[pZutab + 1][pErrenk].getKolorea() == ' ' || Taula.taula[pZutab + 1][pErrenk].getKolorea() == 'K') && (Taula.taula[pZutab - 1][pErrenk].getKolorea() == ' ' || Taula.taula[pZutab - 1][pErrenk].getKolorea() == 'K')) {
                    hutsik = true;
                }
            }
        }
        return hutsik;
    }

    public boolean azkenekoGelaxka(int pErrenk) {
        boolean azkena = false;
        if(pErrenk == this.getErrenkadaLuzera() - 1){
            azkena = true;
        }
        return azkena;
    }

    public boolean albokoakKoloreBerdina(int pZutab, int pErrenk) {
        boolean berdina = false;
        char kolorea = Taula.nireTaula.getGelaxka(pZutab, pErrenk).getKolorea();
        if (pZutab == 0) {
            if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
                if (Taula.taula[pZutab + 1][pErrenk].getKolorea() == kolorea) {
                    berdina = true;
                }
            }
            else if (Taula.taula[pZutab + 1][pErrenk].getKolorea() == kolorea || Taula.taula[pZutab][pErrenk + 1].getKolorea() == kolorea) {
                berdina = true;
            }
        }
        else if (pZutab == this.getZutabeLuzera() - 1) {
            if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
                if (Taula.taula[pZutab - 1][pErrenk].getKolorea() == kolorea) {
                    berdina=true;
                }
            }
            else if (Taula.taula[pZutab - 1][pErrenk].getKolorea() == kolorea || Taula.taula[pZutab][pErrenk + 1].getKolorea() == kolorea) {
                berdina = true;
            }
        }
        else {
            if (Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
                if (Taula.taula[pZutab + 1][pErrenk].getKolorea() == kolorea || Taula.taula[pZutab - 1][pErrenk].getKolorea() == kolorea) {
                    berdina = true;
                }
            }
            else if (Taula.taula[pZutab + 1][pErrenk].getKolorea() == kolorea || Taula.taula[pZutab - 1][pErrenk].getKolorea() == kolorea || Taula.taula[pZutab][pErrenk + 1].getKolorea() == kolorea) {
                berdina = true;
            }
        }
        return berdina;
    }

    public boolean behekoaKoloreBerdina(int pZutab, int pErrenk) {
        boolean berdina = false;
        if (!this.azkenekoGelaxka(pErrenk)) {
            if (Taula.nireTaula.getGelaxka(pZutab, pErrenk).getKolorea() == Taula.nireTaula.getGelaxka(pZutab, pErrenk + 1).getKolorea()) {
                berdina = true;
            }
        }
        return berdina;
    }

    public void ingurukoakDesagertu(int pZutab, int pErrenk) {
        if (!Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
            Taula.nireTaula.getGelaxka(pZutab, pErrenk + 1).setKolorea(' ');
        }
        if (pZutab > 0) {
            Taula.nireTaula.getGelaxka(pZutab - 1, pErrenk).setKolorea(' ');
        }
        if (pZutab < this.getZutabeLuzera() - 1) {
            Taula.nireTaula.getGelaxka(pZutab + 1, pErrenk).setKolorea(' ');
        }
        Taula.nireTaula.egokituTaula(pZutab, pErrenk);
    }

    public void azpikoFitxaKolorezAldatu(int pZutab, int pErrenk, char kolore1, char kolore2) {
        if (!Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
            Gelaxka gelaxka = Taula.nireTaula.getGelaxka(pZutab, pErrenk + 1);
            if (gelaxka.getKolorea() == kolore1) {
                gelaxka.setKolorea(kolore2);
            }
            else {
                gelaxka.setKolorea(kolore1);
            }
        }
    }

    private void egokituTaula(int pZutab, int pErrenk) {
        if (!Taula.nireTaula.azkenekoGelaxka(pErrenk)) {
            Taula.nireTaula.egokituZutabea(pZutab, pErrenk + 1);
        }
        if (pZutab > 0) {
            Taula.nireTaula.egokituZutabea(pZutab - 1, pErrenk);
        }
        if (pZutab < this.getZutabeLuzera() - 1) {
            Taula.nireTaula.egokituZutabea(pZutab + 1, pErrenk);
        }
    }

    private void egokituZutabea(int pZutab, int pErrenk) {
        boolean amaituta = false;
        while (!amaituta) {
            if (pErrenk == 0) {
                amaituta = true;
            }
            if (!amaituta) {
                if (Taula.taula[pZutab][pErrenk - 1].getKolorea() != ' ' && Taula.taula[pZutab][pErrenk - 1].getKolorea() != 'K') {
                    Taula.taula[pZutab][pErrenk].setKolorea(Taula.taula[pZutab][pErrenk - 1].getKolorea());
                    pErrenk--;
                }
                else {
                    amaituta = true;
                }
            }
        }
        if (Taula.taula[pZutab][pErrenk].getKolorea() != ' ' && Taula.taula[pZutab][pErrenk].getKolorea() != 'K') {
            Taula.taula[pZutab][pErrenk].setKolorea(' ');
        }
    }

    private static class AukerarikEz extends Exception {

        public AukerarikEz() {
            super();
        }
    }
}
