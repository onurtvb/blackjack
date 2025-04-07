package com.blackjack.model;

import com.blackjack.Carta;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.util.*;

public class MainApplication extends Application {
    final int WIDTH = 120;
    final int HEIGHT = 150;

    static int punteggioBanco = 0;
    static int punteggioGiocatore = 0;
    static int i = 0;
    static List<Carta> mazzo = new ArrayList<>();
    static HBox boxGiocatore = new HBox();
    static HBox boxBanco = new HBox();
    static Label testoBanco = new Label("");
    static Label testoGiocatore = new Label("");
    static Button btnChiama = new Button("Chiama");
    static Pane root = new Pane();
    static Scene scene = new Scene(root, 857, 500);
    static Button btnStai = new Button("Stai");
/*
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

 */

    public void start(Stage stage){
        stage.setTitle("BlackJack");
        stage.getIcons().add(new Image(getClass().getResource("/icon.jpg").toString()));
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        inizio();
    }

    public void inizio() {
        costruttori();
        Image cartaBanco = new Image(getClass().getResource("/carta.png").toString());
        ImageView cartaB = new ImageView(cartaBanco);
        cartaB.setFitHeight(HEIGHT);
        cartaB.setFitWidth(WIDTH);
        boxBanco.getChildren().add(cartaB);

        cartaBanco = new Image(getClass().getResource(mazzo.get(0).getPercorso()).toString());
        cartaB = new ImageView(cartaBanco);
        cartaB.setFitHeight(HEIGHT);
        cartaB.setFitWidth(WIDTH);
        boxBanco.getChildren().add(cartaB);

        for (i = 2; i < 4; i++) {
            Image cartaGiocatore = new Image(getClass().getResource(mazzo.get(i).getPercorso()).toString());
            ImageView cardG = new ImageView(cartaGiocatore);
            cardG.setFitHeight(HEIGHT);
            cardG.setFitWidth(WIDTH);
            boxGiocatore.getChildren().add(cardG);
        }

        punteggioBanco = mazzo.get(0).getValore() + mazzo.get(1).getValore();
        punteggioGiocatore = mazzo.get(2).getValore() + mazzo.get(3).getValore();
        i = 4;

        if ((mazzo.get(0).getValore() == 1 && mazzo.get(1).getValore() == 10) || (mazzo.get(0).getValore() == 10 && mazzo.get(1).getValore() == 1)) {
            punteggioGiocatore = 21;
            testoGiocatore.setText("BlackJack!");
            btnChiama.setDisable(true);
            btnStai.setDisable(true);
            reset();
            return;
        }
        if(punteggioGiocatore > 21){
            JOptionPane.showMessageDialog(null, "Hai sballato! Hai perso.");
            PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(event -> reset());
            pausa.play();
            reset();
            return;
        }

        testoGiocatore.setText("Punteggio: " + punteggioGiocatore);
        testoBanco.setText("Punteggio: ?");

        btnChiama.setOnAction(e -> {
            turnoGiocatore();
        });

        btnStai.setOnAction(e -> {
            btnChiama.setDisable(true);
            btnStai.setDisable(true);
            scopri();
        });
    }

    public void turnoGiocatore() {
        Image cartaGiocatore = new Image(getClass().getResource(mazzo.get(i).getPercorso()).toString());
        ImageView cardG = new ImageView(cartaGiocatore);
        cardG.setFitHeight(HEIGHT);
        cardG.setFitWidth(WIDTH);
        boxGiocatore.getChildren().add(cardG);
        punteggioGiocatore += mazzo.get(i++).getValore();
        testoGiocatore.setText("Punteggio: " + punteggioGiocatore);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
        pause.setOnFinished(event -> controllo());
        pause.play();
    }

    public void turnoBanco() {
        Image cartaBanco = new Image(getClass().getResource(mazzo.get(i).getPercorso()).toString());
        ImageView cardB = new ImageView(cartaBanco);
        cardB.setFitHeight(HEIGHT);
        cardB.setFitWidth(WIDTH);
        boxBanco.getChildren().add(cardB);
        punteggioBanco += mazzo.get(i++).getValore();
        testoBanco.setText("Punteggio: " + punteggioBanco);

        PauseTransition pausa = new PauseTransition(Duration.seconds(1));
        pausa.setOnFinished(event -> {
            if (punteggioBanco > 21) {
                JOptionPane.showMessageDialog(null, "Banco sballa! Hai vinto!");
                reset();
            } else if (punteggioBanco > punteggioGiocatore) {
                JOptionPane.showMessageDialog(null, "Banco vince!");
                reset();
            } else if (punteggioBanco == punteggioGiocatore) {
                JOptionPane.showMessageDialog(null, "Pareggio!");
                reset();
            } else {
                turnoBanco();
            }
        });
        pausa.play();
    }

    public void scopri() {
        boxBanco.getChildren().clear();
        boxBanco.getChildren().add(testoBanco);
        for (int j = 0; j < 2; j++) {
            Image cartaBanco = new Image(getClass().getResource(mazzo.get(j).getPercorso()).toString());
            ImageView cardB = new ImageView(cartaBanco);
            cardB.setFitHeight(HEIGHT);
            cardB.setFitWidth(WIDTH);
            boxBanco.getChildren().add(cardB);
        }
        testoBanco.setText("Punteggio: " + punteggioBanco);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (punteggioBanco < 17) {
                turnoBanco();
            } else {
                if (punteggioBanco > punteggioGiocatore && punteggioBanco <= 21) {
                    JOptionPane.showMessageDialog(null, "Banco vince!");
                } else if (punteggioBanco == punteggioGiocatore) {
                    JOptionPane.showMessageDialog(null, "Pareggio!");
                } else {
                    JOptionPane.showMessageDialog(null, "Hai vinto!");
                }
                reset();
            }
        });
        pause.play();

    }


    public void controllo() {
        if (punteggioGiocatore > 21) {
            JOptionPane.showMessageDialog(null, "Hai sballato! Hai perso.");
            btnChiama.setDisable(true);
            btnStai.setDisable(true);
            boxBanco.getChildren().clear();
            boxBanco.getChildren().add(testoBanco);
            PauseTransition pausa = new PauseTransition(Duration.seconds(1));
            boxBanco.getChildren().clear();
            boxBanco.getChildren().add(testoBanco);
            for (int j = 0; j < 2; j++) {
                Image cartaBanco = new Image(getClass().getResource(mazzo.get(j).getPercorso()).toString());
                ImageView cardB = new ImageView(cartaBanco);
                cardB.setFitHeight(HEIGHT);
                cardB.setFitWidth(WIDTH);
                boxBanco.getChildren().add(cardB);
            }
            testoBanco.setText("Punteggio: " + punteggioBanco);
            pausa.setOnFinished(event -> {
                JOptionPane.showMessageDialog(null,"Il banco vince con " + punteggioBanco + " punti.");
                reset();
            });
            pausa.play();

        } else if (punteggioGiocatore == 21) {
            JOptionPane.showMessageDialog(null, "21! Hai vinto");
            btnChiama.setDisable(true);
            btnStai.setDisable(true);
            PauseTransition pausa = new PauseTransition(Duration.seconds(1));
            pausa.setOnFinished(actionEvent -> reset());
        }
    }

    public void costruttori() {
        root.getChildren().clear();
        boxBanco.getChildren().clear();
        boxGiocatore.getChildren().clear();

        mazzo.clear();
        for (int i = 1; i <= 13; i++) {
            mazzo.add(new Carta(i, "cuori"));
            mazzo.add(new Carta(i, "quadri"));
            mazzo.add(new Carta(i, "fiori"));
            mazzo.add(new Carta(i, "picche"));
        }
        Collections.shuffle(mazzo);

        Image backgroundImage = new Image(getClass().getResource("/sfondo.jpg").toString());
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        root.setBackground(new Background(bgImage));

        boxBanco.setSpacing(10);
        boxBanco.setLayoutX(200);
        boxBanco.setLayoutY(50);
        boxBanco.getChildren().add(testoBanco);

        boxGiocatore.setSpacing(10);
        boxGiocatore.setLayoutX(200);
        boxGiocatore.setLayoutY(300);
        boxGiocatore.getChildren().add(testoGiocatore);

        btnChiama.setLayoutX(700);
        btnChiama.setLayoutY(400);

        btnStai.setLayoutX(700);
        btnStai.setLayoutY(450);

        root.getChildren().addAll(boxBanco, boxGiocatore, btnChiama, btnStai);
    }

    public void reset() {
        int x = JOptionPane.showConfirmDialog(null, "Vuoi giocare ancora?", "BlackJack", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            btnStai.setDisable(false);
            btnChiama.setDisable(false);
            boxBanco.getChildren().clear();
            boxGiocatore.getChildren().clear();
            testoBanco.setText("");
            testoGiocatore.setText("");
            punteggioBanco = 0;
            punteggioGiocatore = 0;
            i = 0;
            inizio();
        } else {
            System.exit(0);
        }
    }

    public static void main(Stage args) {
        launch();
    }
}