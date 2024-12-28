module com.example.tools {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens com.example.tools to javafx.fxml;
    exports com.example.tools;
    exports com.example.tools.controller;
    exports com.example.tools.service;
    opens com.example.tools.controller to javafx.fxml;
    opens com.example.tools.model to javafx.base;
}