package App.controller.ui;

import App.Konekta4FX;
import App.models.Bonba;
import App.models.Eraldatu;
import App.models.Taula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JokoaKud implements Initializable {

    private Konekta4FX main;

    @FXML
    private Text txtTxanda;

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

    public void setMainApp(Konekta4FX main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    void onClickBtnZut(ActionEvent event) throws IOException {
        if (event.getSource() == btnZut1) {
            main.hurrengoTxanda(0);
        } else if (event.getSource() == btnZut2) {
            main.hurrengoTxanda(1);
        } else if (event.getSource() == btnZut3) {
            main.hurrengoTxanda(2);
        } else if (event.getSource() == btnZut4) {
            main.hurrengoTxanda(3);
        } else if (event.getSource() == btnZut5) {
            main.hurrengoTxanda(4);
        } else if (event.getSource() == btnZut6) {
            main.hurrengoTxanda(5);
        } else if (event.getSource() == btnZut7) {
            main.hurrengoTxanda(6);
        }
    }

    public void jokoaHasieratu() {
        this.setTxtTxanda(0);
        this.txtKomodina11.setText(main.getJokalaria(0).getIzena());
        this.txtKomodina12.setText("Ninguno");
        this.txtKomodina21.setText(main.getJokalaria(1).getIzena());
        this.txtKomodina22.setText("Ninguno");
        this.pantailaratuTaula();
    }

    public void setTxtTxanda(int txanda) {
        this.txtTxanda.setText(main.getJokalariak()[txanda].getIzena());
        if (txanda == 0) {
            txtTxanda.setFill(Color.RED);
        } else if (txanda == 1) {
            txtTxanda.setFill(Color.BLACK);
        }
    }

    public void pantailaratuTaula() {
        for (int i=0;i<Taula.getNireTaula().getErrenkadaLuzera();i++) {
            for (int j=0;j<Taula.getNireTaula().getZutabeLuzera();j++) {
                Image irudia;
                String path = "File:src/main/resources/irudiak/";
                if(System.getProperty("os.name").toLowerCase().contains("win")) {
                    path = path.replace("/", "\\");
                }
                if (Taula.getNireTaula().getGelaxka(j, i) instanceof Bonba || Taula.getNireTaula().getGelaxka(j, i) instanceof Eraldatu) {
                    irudia = new Image(path + "komodin.png");
                } else {
                    irudia = new Image(path + "hutsik.png");
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
       /* if (main.getJokalaria(pTxanda).getKolorea() == 'G') {
            irudia = new Image(path + "G.png");
        } else if (main.getJokalaria(pTxanda).getKolorea() == 'B') {
            irudia = new Image(path + "B.png");
        }
*/
        irudia = new Image(path + main.getJokalaria(pTxanda).getKolorea()+ ".png");
        this.gridPaneTaula.add(new ImageView(irudia), pZutab, pErrenk);
    }

    public void setTxtKomodina(int pKomodina, int txanda) {
        if (txanda == 0) {
            if (pKomodina == 1) {
                this.txtKomodina12.setText("Bomba");
            } else if (pKomodina == 2) {
                this.txtKomodina12.setText("Transformar");
            }
        } else if (txanda == 1) {
            if (pKomodina == 1) {
                this.txtKomodina22.setText("Bomba");
            } else if (pKomodina == 2) {
                this.txtKomodina22.setText("Transformar");
            }
        }
    }
}
