package com.blackjack.model;

import com.blackjack.controller.IntroController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IntroApplication {

    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(IntroController.class.getResource("intro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load() ,600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
