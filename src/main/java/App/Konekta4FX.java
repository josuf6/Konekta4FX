package App;

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

    public void jokoaErakutsi(String j1, String j2) throws IOException {
        this.jokoaPantailaKargatu();
        this.partidaBatJolastu(j1, j2);
        this.sceneEgokitu(jokoaUI);
        stage.show();
    }

    private void amaieraErakutsi(int pTxanda) throws IOException {
        this.amaieraPantailaKargatu();
        if (pTxanda == -1) {
            this.amaieraKud.setTestua("No ha ganado nadie");
        } else {
            this.amaieraKud.setTestua("Ha ganado " + this.getJokalaria(pTxanda).getIzena());
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

    private void partidaBatJolastu(String j1, String j2) {
        this.jokalariakInskribatu(j1, j2);
        this.jokoaHasieratu();
        this.jokoaKud.jokoaHasieratu();
    }

    private void jokalariakInskribatu(String j1, String j2) {
        Konekta4FX.jokalariak[0] = new Jokalaria(j1, 'G');
        Konekta4FX.jokalariak[1] = new Jokalaria(j2, 'B');
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

    public void hurrengoTxanda(int zutab) throws IOException {
        if (!Taula.getNireTaula().zutabBeteta(zutab)) {
            int errenk = Taula.getNireTaula().getErrenkada(zutab);
            this.fitxaKolorezAldatu(zutab, errenk, this.txanda);

            if (!this.amaituta()) {

                //TODO Aqui va lo de poder usar el comodin y que pasaria al usarlo (solo si el jugador tiene comodin)
                //TODO (importante hacerlo antes de que se cambie el valor de this.txanda)
                //TODO (y tambien importante comprobar de nuevo si despues de usar el comodin se ha acabado la partida)

                if (this.txanda == 0) {
                    this.txanda = 1;
                } else if (this.txanda == 1) {
                    this.txanda = 0;
                } else {
                    this.txanda = 0;
                }
            } else {
                this.emaitza();
            }
            this.jokoaKud.setTxtTxanda(this.txanda);
        }
    }

    private void fitxaKolorezAldatu(int pZutab, int pErrenk, int pTxanda) {
        Gelaxka gelaxka = Taula.getNireTaula().getGelaxka(pZutab, pErrenk);
        if (gelaxka instanceof Bonba) {
            this.getJokalaria(this.txanda).setKomodinErabilgarria(1);
            this.jokoaKud.setTxtKomodina(1, this.txanda);
            Taula.getNireTaula().setGelaxka(pZutab, pErrenk, 0);
        }
        else if (gelaxka instanceof Eraldatu) {
            this.getJokalaria(this.txanda).setKomodinErabilgarria(2);
            this.jokoaKud.setTxtKomodina(2, this.txanda);
            Taula.getNireTaula().setGelaxka(pZutab, pErrenk, 0);
        }
        char kolorea = this.getJokalaria(pTxanda).getKolorea();
        Taula.getNireTaula().getGelaxka(pZutab, pErrenk).setKolorea(kolorea);
        this.jokoaKud.fitxaKolorezAldatu(pZutab, pErrenk, pTxanda);
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
}
