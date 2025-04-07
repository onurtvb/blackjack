module com.blackjack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.blackjack to javafx.fxml;
    exports com.blackjack;
    exports com.blackjack.controller;
    opens com.blackjack.controller to javafx.fxml;
    exports com.blackjack.model;
    opens com.blackjack.model to javafx.fxml;
}