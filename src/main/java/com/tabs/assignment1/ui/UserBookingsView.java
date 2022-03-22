package com.tabs.assignment1.ui;

import com.tabs.assignment1.controller.UserController;
import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class UserBookingsView implements Initializable {

    private final UserController userController = new UserController();

    @FXML
    private TableView<PackageDAO> bookingsTableView;
    @FXML
    private TableColumn<PackageDAO, String> destinationCol;
    @FXML
    private TableColumn<PackageDAO, String> nameCol;
    @FXML
    private TableColumn<PackageDAO, String> priceCol;
    @FXML
    private TableColumn<PackageDAO, String> periodCol;
    @FXML
    private TableColumn<PackageDAO, String> detailCol;
    @FXML
    private TableColumn<PackageDAO, Integer> seatCol;
    @FXML
    private Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurePackageTableView();
        loadBookings();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Utils.switchScene(event, "user.fxml", Utils.userTitle);
    }

    private void configurePackageTableView() {
        destinationCol.setCellValueFactory(param -> { // put the destination's name, not the object reference
            if (param.getValue() != null) {
                return new SimpleStringProperty(param.getValue().getDestinationByDestinationId().getName());
            } else {
                return new SimpleStringProperty("<No destination>");
            }
        });
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        periodCol.setCellValueFactory(new PropertyValueFactory<>("period"));
        detailCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        seatCol.setCellValueFactory(new PropertyValueFactory<>("noOfSpots"));
    }

    private void loadBookings() {
        List<PackageDAO> bookings = userController.getAllUserBookings(Utils.currentUser.getId());
        bookingsTableView.getItems().removeAll();
        bookingsTableView.setItems(FXCollections.observableArrayList(bookings));
    }
}
