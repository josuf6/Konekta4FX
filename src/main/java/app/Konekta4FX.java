package app;

import app.controller.ui.HasieraKud;
import app.controller.ui.JokoaKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Konekta4FX extends Application {

    private Stage stage;

    private Scene sceneHasiera;
    private Scene sceneJokoa;
    private Parent hasieraUI;
    private Parent jokoaUI;
    private HasieraKud hasieraKud;
    private JokoaKud jokoaKud;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("KONEKTA 4");
        stage.setScene(sceneHasiera);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/hasiera.fxml"));
        hasieraUI = (Parent) loaderHasiera.load();
        hasieraKud = loaderHasiera.getController();
        hasieraKud.setMainApp(this);
        sceneHasiera = new Scene(hasieraUI);

        FXMLLoader loaderJokoa = new FXMLLoader(getClass().getResource("/jokoa.fxml"));
        jokoaUI = (Parent) loaderJokoa.load();
        jokoaKud = loaderJokoa.getController();
        jokoaKud.setMainApp(this);
    }

    public void jokoaErakutsi(String j1, String j2){
        sceneJokoa = new Scene(jokoaKud.createContent(j1,j2));
        stage.setScene(sceneJokoa);
    }

}
