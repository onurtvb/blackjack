package com.example.blackjack;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IntroController {

    public void Gioca(MouseEvent mouseEvent) {
        Stage stage = null;
        Main.launch();
    }

    public void Esci(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
