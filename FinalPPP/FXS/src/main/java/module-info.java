module com.idk.fxs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires mysql.connector.java;


    opens com.idk.fxs to javafx.fxml;
    exports com.idk.fxs;

}