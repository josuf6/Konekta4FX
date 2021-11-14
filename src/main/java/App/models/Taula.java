package App.models;

public class Taula {

    private static Taula nireTaula=null;
    private static Gelaxka[][] taula=new Gelaxka[7][6];

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

    public int getZutabeLuzera() {
        return taula.length;
    }

    public int getErrenkadaLuzera() {
        return taula[0].length;
    }

    public int getErrenkada(int pZutab) {
        int errenkada = nireTaula.getErrenkadaLuzera();
        boolean aurkituta = false;
        while (!aurkituta && errenkada>0) {
            errenkada--;
            if (taula[pZutab][errenkada].getKolorea() == ' ' || taula[pZutab][errenkada].getKolorea() == 'K') {
                aurkituta=true;
            }
        }
        return errenkada;
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
                            if (jarraian == 0 && (zutabea-zutabeAux < 3 || errenkada + errenkadaAux > this.getErrenkadaLuzera() - 4)) {
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

    private static class AukerarikEz extends Exception {

        public AukerarikEz() {
            super();
        }
    }
}
