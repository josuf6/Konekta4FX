package App.controller.ui;

import App.Konekta4FX;
import App.models.Bonba;
import App.models.Eraldatu;
import App.models.Taula;
import App.models.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class JokoaKud implements Initializable {

    private Konekta4FX main;

    @FXML
    private Text txtTxanda;

    @FXML
    private Button btnErabili;

    @FXML
    private Button btnEzErabili;

    @FXML
    private GridPane gridPaneTaula;

    @FXML
    private Text txtKomodina11;

    @FXML
    private Text txtKomodina12;

    @FXML
    private Text txtKomodina21;

    @FXML
    private Text txtKomodina22;

    @FXML
    private Button btnZut1;

    @FXML
    private Button btnZut2;

    @FXML
    private Button btnZut3;

    @FXML
    private Button btnZut4;

    @FXML
    private Button btnZut5;

    @FXML
    private Button btnZut6;

    @FXML
    private Button btnZut7;

    @FXML
    private Text timer;

    Time time = new Time("00:00:00");

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1),
                    e -> {
                        time.oneSecondPassed();
                        timer.setText(time.getCurrentTime());
                    }));

    public void setMainApp(Konekta4FX main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timer.setText(time.getCurrentTime());

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void onClickBtnZut(ActionEvent event) throws IOException {
        if (event.getSource() == btnZut1) {
            main.kudeatuTxanda(0);
        } else if (event.getSource() == btnZut2) {
            main.kudeatuTxanda(1);
        } else if (event.getSource() == btnZut3) {
            main.kudeatuTxanda(2);
        } else if (event.getSource() == btnZut4) {
            main.kudeatuTxanda(3);
        } else if (event.getSource() == btnZut5) {
            main.kudeatuTxanda(4);
        } else if (event.getSource() == btnZut6) {
            main.kudeatuTxanda(5);
        } else if (event.getSource() == btnZut7) {
            main.kudeatuTxanda(6);
        }
    }

    @FXML
    void onClickErabili(ActionEvent event) throws IOException {
        this.main.komodinaErabili();
    }

    @FXML
    void onClickEzErabili(ActionEvent event) {
        this.main.hurrengoTxanda();
    }

    public void jokoaHasieratu() {
        this.setTxtTxanda(0);
        this.txtKomodina11.setText(main.getJokalaria(0).getIzena());
        this.txtKomodina11.setFill(getColor(main.getJokalaria(0).getKolorea()));
        this.txtKomodina12.setText("Ninguno");
        this.txtKomodina21.setText(main.getJokalaria(1).getIzena());
        this.txtKomodina21.setFill(getColor(main.getJokalaria(1).getKolorea()));
        this.txtKomodina22.setText("Ninguno");
        this.pantailaratuTaula();
    }

    public Color getColor(char kolore){
        switch (kolore){    //puede que algun color este mal escrito
            case 'A': return Color.PINK;
            case 'B': return Color.BLACK;
            case 'C': return Color.CYAN;
            case 'G': return Color.RED;
            case 'L': return Color.ORANGE;
            case 'M': return Color.PURPLE;
            case 'U': return Color.BLUE;
            case 'V': return Color.GREEN;
            case 'Y': return Color.YELLOW;
        }
        return Color.RED;
    }

    public void setTxtTxanda(int txanda) {
        this.txtTxanda.setText(main.getJokalariak()[txanda].getIzena());
       /* if (txanda == 0) {    //que mal esta esto
            txtTxanda.setFill(Color.RED);
        } else if (txanda == 1) {
            txtTxanda.setFill(Color.BLACK);
        }*/
        txtTxanda.setFill(getColor(main.getJokalaria(txanda).getKolorea()));
    }

    public void setTxtKomodina(int pKomodina, int txanda) {
        if (txanda == 0) {
            if (pKomodina == 0) {
                this.txtKomodina12.setText("Ninguno");
            } else if (pKomodina == 1) {
                this.txtKomodina12.setText("Bomba");
            } else if (pKomodina == 2) {
                this.txtKomodina12.setText("Transformar");
            }
        } else if (txanda == 1) {
            if (pKomodina == 0) {
                this.txtKomodina22.setText("Ninguno");
            } else if (pKomodina == 1) {
                this.txtKomodina22.setText("Bomba");
            } else if (pKomodina == 2) {
                this.txtKomodina22.setText("Transformar");
            }
        }
    }

    public void pantailaratuTaula() {
        for (int i=0;i<Taula.getNireTaula().getErrenkadaLuzera();i++) {
            for (int j=0;j<Taula.getNireTaula().getZutabeLuzera();j++) {
                Image irudia = null;
                String path = "File:src/main/resources/irudiak/";
                if(System.getProperty("os.name").toLowerCase().contains("win")) {
                    path = path.replace("/", "\\");
                }
                if (Taula.getNireTaula().getGelaxka(j, i) instanceof Bonba || Taula.getNireTaula().getGelaxka(j, i) instanceof Eraldatu) {
                    irudia = new Image(path + "komodin.png");
                } else if (Taula.getNireTaula().getGelaxka(j, i).getKolorea() == ' ') {
                    irudia = new Image(path + "hutsik.png");
                } else {
                    irudia = new Image(path + Taula.getNireTaula().getGelaxka(j, i).getKolorea() + ".png");
                }
                this.gridPaneTaula.add(new ImageView(irudia), j, i);
            }
        }
    }

    public void fitxaKolorezAldatu(int pZutab, int pErrenk, int pTxanda) {
        Image irudia = null;
        String path = "File:src/main/resources/irudiak/";
        if(System.getProperty("os.name").toLowerCase().contains("win")) {
            path = path.replace("/", "\\");
        }
        irudia = new Image(path + main.getJokalaria(pTxanda).getKolorea() + ".png");
        this.gridPaneTaula.add(new ImageView(irudia), pZutab, pErrenk);
    }

    public void aktibatuBtnKomodina() {
        this.btnErabili.setDisable(false);
        this.btnEzErabili.setDisable(false);
    }

    public void desaktibatuBtnKomodina() {
        this.btnErabili.setDisable(true);
        this.btnEzErabili.setDisable(true);
    }

    public void aktibatuTaula() {
        this.btnZut1.setDisable(false);
        this.btnZut2.setDisable(false);
        this.btnZut3.setDisable(false);
        this.btnZut4.setDisable(false);
        this.btnZut5.setDisable(false);
        this.btnZut6.setDisable(false);
        this.btnZut7.setDisable(false);
    }

    public void desaktibatuTaula() {
        this.btnZut1.setDisable(true);
        this.btnZut2.setDisable(true);
        this.btnZut3.setDisable(true);
        this.btnZut4.setDisable(true);
        this.btnZut5.setDisable(true);
        this.btnZut6.setDisable(true);
        this.btnZut7.setDisable(true);
    }

    public void aktibatuZentzurikEzModua() {
        this.btnEzErabili.setDefaultButton(true);
    }

    public void desaktibatuZentzurikEzModua() {
        this.btnEzErabili.setDefaultButton(false);
    }
}
