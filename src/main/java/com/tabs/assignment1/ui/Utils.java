package com.tabs.assignment1.ui;

import com.tabs.assignment1.model.abstraction_objects.AgencyDAO;
import com.tabs.assignment1.model.abstraction_objects.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class Utils {
    final static String loginTitle = "Travel App";
    final static String registerTitle = "Register";
    final static String userTitle = "User Screen";
    final static String travelAgencyTitle = "Travel Agency";
    final static String userBookingsTitle = "User Bookings";
    static UserDAO currentUser = new UserDAO();
    static AgencyDAO currentAgency = new AgencyDAO();

    static void switchScene(ActionEvent event, String screenPath, String title) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader screenLoader = new FXMLLoader(HelloApplication.class.getResource(screenPath));
        Scene scene = new Scene(screenLoader.load());
        window.setTitle(title);
        window.setScene(scene);
        window.centerOnScreen();
        window.show();
    }
}
