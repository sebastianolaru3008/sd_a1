module com.tabs.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.hibernate.commons.annotations;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jbcrypt;
    requires static lombok;

    exports com.tabs.assignment1.ui;
    exports com.tabs.assignment1.utils;
    exports com.tabs.assignment1.model;
    exports com.tabs.assignment1.model.abstraction_objects;
    exports com.tabs.assignment1.model.transfer_objects;
    exports com.tabs.assignment1.service;
    exports com.tabs.assignment1.repository;
    exports com.tabs.assignment1.controller;
    opens com.tabs.assignment1.ui to javafx.fxml;
    opens com.tabs.assignment1.model.abstraction_objects;
    opens com.tabs.assignment1.model.transfer_objects;
    opens com.tabs.assignment1.controller;
    opens com.tabs.assignment1.repository;
    opens com.tabs.assignment1.service;
    opens com.tabs.assignment1.utils;
}
