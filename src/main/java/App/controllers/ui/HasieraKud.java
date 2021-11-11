package App.controllers.ui;

import App.Konekta4FX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements Initializable{

    private Konekta4FX main;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private Button btnBotoia;

    @FXML
    void onClick(ActionEvent event) {
        main.jokoaErakutsi();
    }

    public void setMainApp(Konekta4FX main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
