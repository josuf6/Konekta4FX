package App;

import App.controllers.ui.HasieraKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Konekta4FX extends Application {

    private Parent EurobisioaUI;
    private Stage stage;

    private Scene sceneHasiera;
    private Parent hasieraUI;
    private HasieraKud hasieraKud;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("KONEKTA 4");
        stage.setScene(sceneHasiera);
        stage.show();
    }

    public void hasieraErakutsi(){
        stage.setScene(sceneHasiera);
    }


    private void pantailakKargatu() throws IOException {
        FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/hasiera.fxml"));
        hasieraUI = (Parent) loaderHasiera.load();
        hasieraKud = loaderHasiera.getController();
        hasieraKud.setMainApp(this);
        sceneHasiera = new Scene(hasieraUI);
    }
}
