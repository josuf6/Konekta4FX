package App;

import App.controller.db.Konekta4FXDBKud;
import App.controller.ui.AmaieraKud;
import App.controller.ui.HasieraKud;
import App.controller.ui.JokoaKud;
import App.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Konekta4FX extends Application {

    private Parent hasieraUI;
    private Parent jokoaUI;
    private Parent amaieraUI;

    private static Stage stage;

    private HasieraKud hasieraKud;
    private JokoaKud jokoaKud;
    private AmaieraKud amaieraKud;

    private static final Jokalaria[] jokalariak = new Jokalaria[2];
    private int txanda;
    private int moves=0;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        this.hasieraErakutsi();
        stage.setTitle("KONEKTA 4");
    }

    public void hasieraErakutsi() throws IOException {
        this.hasieraPantailaKargatu();
        this.hasieraKud.hustuLbl();
        this.sceneEgokitu(hasieraUI);
        stage.show();
    }

    public void jokoaErakutsi(String j1, String j2,String kolore1, String kolore2) throws IOException {
        this.jokoaPantailaKargatu();
        this.partidaBatJolastu(j1, j2, kolore1, kolore2);
        this.sceneEgokitu(jokoaUI);
        stage.show();
    }

    private void amaieraErakutsi(int pTxanda) throws IOException {
        if(pTxanda == 0){
            Konekta4FXDBKud.getInstantzia().insertPartida(this.getJokalaria(pTxanda).getIzena(),this.getJokalaria(pTxanda+1).getIzena(),moves,jokoaKud.getDenbora());
        }
        else{
            Konekta4FXDBKud.getInstantzia().insertPartida(this.getJokalaria(pTxanda-1).getIzena(),this.getJokalaria(pTxanda).getIzena(),moves,jokoaKud.getDenbora());
        }

        this.amaieraPantailaKargatu();
        if (pTxanda == -1) {
            this.amaieraKud.setTestua("No ha ganado nadie");
        } else {
            this.amaieraKud.setTestua("Ha ganado " + this.getJokalaria(pTxanda).getIzena());
            amaieraKud.setLista();
        }
        this.sceneEgokitu(amaieraUI);
        stage.show();
    }

    private void sceneEgokitu(Parent inerfazea) {
        stage.setScene(new Scene(inerfazea));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 3);
    }

    private void hasieraPantailaKargatu() throws IOException {
        FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/hasiera.fxml"));
        hasieraUI = (Parent) loaderHasiera.load();
        hasieraKud = loaderHasiera.getController();
        hasieraKud.setMainApp(this);
    }

    private void jokoaPantailaKargatu() throws IOException {
        FXMLLoader loaderJokoa = new FXMLLoader(getClass().getResource("/Jokoa.fxml"));
        jokoaUI = (Parent) loaderJokoa.load();
        jokoaKud = loaderJokoa.getController();
        jokoaKud.setMainApp(this);
    }

    private void amaieraPantailaKargatu() throws IOException {
        FXMLLoader loaderAmaiera = new FXMLLoader(getClass().getResource("/Amaiera.fxml"));
        amaieraUI = (Parent) loaderAmaiera.load();
        amaieraKud = loaderAmaiera.getController();
        amaieraKud.setMainApp(this);
    }

    public Jokalaria[] getJokalariak() {
        return jokalariak;
    }

    public Jokalaria getJokalaria(int pTxanda) {
        return jokalariak[pTxanda];
    }

    private void partidaBatJolastu(String j1, String j2, String kolore1, String kolore2) {
        this.jokalariakInskribatu(j1, j2, kolore1, kolore2);
        this.jokoaHasieratu();
        this.jokoaKud.jokoaHasieratu();
    }

    private void jokalariakInskribatu(String j1, String j2,String kolore1, String kolore2) { //hacer que en la llamada a este metodo se pasen los colores
        Konekta4FX.jokalariak[0] = new Jokalaria(j1, koloreaBegiratu(kolore1));
        Konekta4FX.jokalariak[1] = new Jokalaria(j2, koloreaBegiratu(kolore2));
    }

    private char koloreaBegiratu (String kolore){

        switch (kolore){    //puede que algun color este mal escrito
            case "Arrosa": return 'A';
            case "Beltza": return 'B';
            case "Zian": return 'C';
            case "Gorria": return 'G';
            case "Laranja": return 'L';
            case "Morea": return 'M';
            case "Urdina": return 'U';
            case "Berdea": return 'V';
            case "Horia": return 'Y';
        }
        return 'G';//esto igual habría que considerarlo de otra manera aunque nunca se va a llegar
    }

    private void jokoaHasieratu() {
        Taula.getNireTaula().taulaBerria();
        int bonbaKop=3;
        int eraldatuKop=3;
        int randomZut;
        int randomErrenk;
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
        this.txanda = 0;
    }

    public void kudeatuTxanda(int pZutab) throws IOException {
        if (!Taula.getNireTaula().zutabBeteta(pZutab)) {
            int errenk = Taula.getNireTaula().getErrenkada(pZutab);
            this.fitxaKolorezAldatu(pZutab, errenk, this.txanda);
            this.getJokalaria(this.txanda).addMugimendu();
            Taula.getNireTaula().setAzkenekoFitxaZutabea(pZutab);
            Taula.getNireTaula().setAzkenekoFitxaErrenkada(errenk);
            this.getJokalaria(this.txanda).addMugimendu();

            if (this.irabazleaDago()) {
                this.emaitza();
            } else {
                int komErabilgarria = this.getJokalaria(this.txanda).getKomodinErabilgarria();
                if (komErabilgarria != 0) {
                    this.jokoaKud.aktibatuBtnKomodina();
                    this.jokoaKud.desaktibatuTaula();
                    if ((komErabilgarria == 1 && this.bonbaErabiltzeaZentzurikEz(pZutab, errenk)) || (komErabilgarria == 2 && this.eraldatuErabiltzeaZentzurikEz(pZutab, errenk))) {
                        this.jokoaKud.aktibatuZentzurikEzModua();
                    }
                } else if (this.amaituta()) {
                    this.emaitza();
                } else {
                    this.hurrengoTxanda();
                }
            }
        }
    }

    public void hurrengoTxanda() {
        moves++;
        if (this.txanda == 0) {
            this.txanda = 1;
        } else if (this.txanda == 1) {
            this.txanda = 0;
        } else {
            this.txanda = 0;
        }
        this.jokoaKud.setTxtTxanda(this.txanda);
        this.jokoaKud.desaktibatuZentzurikEzModua();
        this.jokoaKud.desaktibatuBtnKomodina();
        this.jokoaKud.aktibatuTaula();
    }

    private void fitxaKolorezAldatu(int pZutab, int pErrenk, int pTxanda) {
        Gelaxka gelaxka = Taula.getNireTaula().getGelaxka(pZutab, pErrenk);
        if (gelaxka instanceof Bonba) {
            this.jokalariKomodinaAldatu(1, this.txanda);
            Taula.getNireTaula().setGelaxka(pZutab, pErrenk, 0);
        }
        else if (gelaxka instanceof Eraldatu) {
            this.jokalariKomodinaAldatu(2, this.txanda);
            Taula.getNireTaula().setGelaxka(pZutab, pErrenk, 0);
        }
        char kolorea = this.getJokalaria(pTxanda).getKolorea();
        Taula.getNireTaula().getGelaxka(pZutab, pErrenk).setKolorea(kolorea);
        this.jokoaKud.fitxaKolorezAldatu(pZutab, pErrenk, pTxanda);
    }

    private void jokalariKomodinaAldatu(int pKomodina, int pTxanda) {
        this.getJokalaria(pTxanda).setKomodinErabilgarria(pKomodina);
        this.jokoaKud.setTxtKomodina(pKomodina, pTxanda);
    }

    private boolean amaituta() {
        boolean amaituta = false;
        if (Taula.getNireTaula().beteta() || this.irabazleaDago()) {
            amaituta = true;
        }
        return amaituta;
    }

    private boolean irabazleaDago() {
        boolean irabazlea = false;
        char kolorea = this.getJokalaria(this.txanda).getKolorea();
        if (Taula.getNireTaula().zutabeakBegiratu(kolorea) || Taula.getNireTaula().errenkadakBegiratu(kolorea) || Taula.getNireTaula().diagonalakBegiratu(kolorea)) {
            irabazlea=true;
        }
        return irabazlea;
    }

    private void emaitza() throws IOException {
        if (this.irabazleaDago()) {
            this.amaieraErakutsi(this.txanda);
        }
        else {
            this.amaieraErakutsi(-1);
        }
    }

    private boolean bonbaErabiltzeaZentzurikEz(int pZutab, int pErrenk) {
        boolean zentzurikEz = false;
        if (Taula.getNireTaula().albokoakHutsik(pZutab, pErrenk) || Taula.getNireTaula().albokoakKoloreBerdina(pZutab, pErrenk)) {
            zentzurikEz=true;
        }
        return zentzurikEz;
    }

    private boolean eraldatuErabiltzeaZentzurikEz(int pZutab, int pErrenk) {
        boolean zentzurikEz = false;
        if (Taula.getNireTaula().azkenekoGelaxka(pErrenk) || Taula.getNireTaula().behekoaKoloreBerdina(pZutab, pErrenk)) {
            zentzurikEz = true;
        }
        return zentzurikEz;
    }

    public void komodinaErabili() throws IOException {
        int zutab = Taula.getNireTaula().getAzkenekoFitxaZutabea();
        int errenk = Taula.getNireTaula().getAzkenekoFitxaErrenkada();
        int komErabilgarria = this.getJokalaria(this.txanda).getKomodinErabilgarria();
        if (komErabilgarria == 1) {
            Taula.getNireTaula().ingurukoakDesagertu(zutab, errenk);
        } else if (komErabilgarria == 2) {
            Taula.getNireTaula().azpikoFitxaKolorezAldatu(zutab, errenk, this.getJokalaria(0).getKolorea(), this.getJokalaria(1).getKolorea());
        }
        this.jokalariKomodinaAldatu(0, this.txanda);
        this.jokoaKud.pantailaratuTaula();
        if (this.amaituta()) {
            this.emaitza();
        } else {
            this.hurrengoTxanda();
        }
    }
}
