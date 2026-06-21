module com.example.pruebas_de_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.compiler;
    requires annotations;
    requires javafx.media;

    opens com.example.pruebas_de_fx to javafx.fxml;
    exports com.example.pruebas_de_fx;
}