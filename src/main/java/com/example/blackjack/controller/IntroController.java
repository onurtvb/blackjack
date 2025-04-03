package com.example.blackjack.controller;

import com.example.blackjack.model.MainApplication;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IntroController {

    public void Gioca(MouseEvent mouseEvent) {
        Stage stage = null;
        MainApplication.launch();
    }

    public void Esci(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
