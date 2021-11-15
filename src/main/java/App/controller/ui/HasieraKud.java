package App.controller.ui;

import App.Konekta4FX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements Initializable {

    private Konekta4FX main;

    @FXML
    private ComboBox combo1;

    @FXML
    private ComboBox combo2;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private Button btnBotoia;

    @FXML
    void onClick(ActionEvent event) throws IOException {
        main.jokoaErakutsi(txt1.getText(), txt2.getText());
    }

    public void setMainApp(Konekta4FX main) {
        this.main = main;
    }

    public void hustuLbl() {
        this.txt1.clear();
        this.txt2.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> koloreak = FXCollections.observableArrayList("Arrosa","Beltza","Zian","Gorria","Laranja","Morea","Urdina","Berdea","Horia");
        combo1.setItems(koloreak);
        combo2.setItems(koloreak);
    }
}
