module org.wordlers.wordle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    
    opens org.wordlers.wordle to javafx.fxml;
    exports org.wordlers.wordle;
}