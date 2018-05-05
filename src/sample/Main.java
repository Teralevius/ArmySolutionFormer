package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.Controller;
import sample.view.mainWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow wind = new mainWindow(primaryStage);
        Controller controll = new Controller(wind);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
