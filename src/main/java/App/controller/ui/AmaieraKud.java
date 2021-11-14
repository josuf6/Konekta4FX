package App.controller.ui;

import App.Konekta4FX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AmaieraKud implements Initializable {

    private Konekta4FX main;

    @FXML
    private Text txtTestua;


    public void setMainApp(Konekta4FX main) {
        this.main = main;
    }

    @FXML
    void onClick(ActionEvent event) throws IOException {
        main.hasieraErakutsi();
    }

    public void setTestua(String pTestua) {
        this.txtTestua.setText(pTestua);
        txtTestua.setTextAlignment(TextAlignment.CENTER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
