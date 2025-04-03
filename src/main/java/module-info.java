module com.example.blackjack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.blackjack to javafx.fxml;
    exports com.example.blackjack;
    exports com.example.blackjack.controller;
    opens com.example.blackjack.controller to javafx.fxml;
    exports com.example.blackjack.model;
    opens com.example.blackjack.model to javafx.fxml;
}