package no.hiof.fredrivo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader fxmlInnlaster = new FXMLLoader();

        fxmlInnlaster.setLocation(getClass().getResource("view/SorteringView.fxml"));
        Parent rootNode = fxmlInnlaster.load();

        Scene hovedScene = new Scene(rootNode, 680, 400);

        primaryStage.setTitle("Sorterings Metoder");
        primaryStage.setScene(hovedScene);
        primaryStage.show();

    }

    public static void visWarningVindu(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Box");
        alert.setHeaderText("Warning");
        alert.setContentText("Antall tall er for høyt!");

        alert.showAndWait();
    }
}
