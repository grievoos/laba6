module com.example.voting2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.voting2 to javafx.fxml;
    exports com.example.voting2;
}