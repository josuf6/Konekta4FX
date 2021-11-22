package App.controller.ui;

import App.Konekta4FX;
import App.controller.db.Konekta4FXDBKud;
import App.models.TablaAmaiera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AmaieraKud implements Initializable {

    private Konekta4FX main;

    @FXML
    private Text txtTestua;

    @FXML
    private ListView<String> rankingList;


    @FXML
    private TableView<TablaAmaiera> tblTaula;

    @FXML
    private TableColumn<TablaAmaiera, Integer> tblPuntuak;

    @FXML
    private TableColumn<TablaAmaiera, String> tblizena;

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

    public void setLista(List<TablaAmaiera> pKargatzekoa){
        ObservableList<TablaAmaiera> tb = FXCollections.observableArrayList(pKargatzekoa);

        tblTaula.setItems(tb);
        tblTaula.setEditable(true);

        tblizena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        tblPuntuak.setCellValueFactory(new PropertyValueFactory<>("puntuak"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
