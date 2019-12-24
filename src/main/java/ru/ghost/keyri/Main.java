package ru.ghost.keyri;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("IR");
        primaryStage.setScene(new Scene(root, 300, 120));
        primaryStage.setResizable(false);//Отключаем кнопку "развернуть на весь экран"
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
