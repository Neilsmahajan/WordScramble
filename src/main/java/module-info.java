module com.example.wordscramble {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wordscramble to javafx.fxml;
    exports com.example.wordscramble;
}