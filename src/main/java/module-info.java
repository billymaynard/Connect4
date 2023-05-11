module com.connect.connect4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.connect.connect4 to javafx.fxml;
    exports com.connect.connect4;
}