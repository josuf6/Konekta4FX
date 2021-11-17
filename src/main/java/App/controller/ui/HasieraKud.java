package App.controller.ui;

import App.Konekta4FX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private Label lblWarning;

    @FXML
    void onClick(ActionEvent event) throws IOException {
        if((combo1.getValue()==null || combo2.getValue() == null)) {
            lblWarning.setText("Mesedez bi kolore aukeratu");
        }
        else if(combo1.getValue() == combo2.getValue()){
            lblWarning.setText("Mesedez bi kolore ezberdin aukeratu");
        }
        else{
            main.jokoaErakutsi(txt1.getText(), txt2.getText(), combo1.getValue().toString(), combo2.getValue().toString());
        }
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
        addTextLimiter(txt1, 10);
        addTextLimiter(txt2, 10);
        btnBotoia.setDisable(true);
    }

    private void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
                if (txt1.getText().length() >= 1 && txt2.getText().length() >= 1){
                    btnBotoia.setDisable(false);
                }else{
                    btnBotoia.setDisable(true);
                }
            }
        });
    }
}
