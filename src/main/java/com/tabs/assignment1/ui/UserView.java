package com.tabs.assignment1.ui;

import com.tabs.assignment1.controller.UserController;
import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.transfer_objects.FilterDTO;
import com.tabs.assignment1.utils.ExceptionUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.tabs.assignment1.ui.Utils.currentUser;

public class UserView implements Initializable {

    private final UserController userController = new UserController();
    private final FilterDTO filter = new FilterDTO();

    @FXML
    private Button applyDestinationBtn;
    @FXML
    private ListView<DestinationDAO> destinationListView;
    @FXML
    private Button applyPriceBtn;
    @FXML
    private TextField priceFieldMin;
    @FXML
    private TextField priceFieldMax;
    @FXML
    private Button applyPeriodBtn;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private TableView<PackageDAO> packageTableView;//User table doesn't contain status, only the
    // travel agency can see that.
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
    private TableColumn<PackageDAO, Integer> noOfSpotsCol;
    @FXML
    private TableColumn<PackageDAO, String> statusCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurePackageTableView();
        loadPackages();
        loadDestinations();
    }

    private void loadPackages() {//load only bookings that are not status fully booked or booked by the user
        List<PackageDAO> packageList = userController.getAllPackages();
        packageTableView.getItems().removeAll();
        packageTableView.setItems(FXCollections.observableArrayList(packageList));
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
        noOfSpotsCol.setCellValueFactory(new PropertyValueFactory<>("noOfSpots"));
        statusCol.setCellValueFactory(param -> { // put the destination's name, not the object reference
            if (param.getValue() != null) {
                return new SimpleStringProperty(param.getValue().getStatus().toString());
            } else {
                return new SimpleStringProperty("<No status>");
            }
        });

    }

    @FXML
    void bookVacation(ActionEvent event) {
        userController.addBooking(currentUser.getId(),
                packageTableView.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "A new booking has been added!");
        alert.showAndWait();
        loadPackages();
    }

    private void loadDestinations() {
        List<DestinationDAO> destinationList = null;
        try {
            destinationList = userController.getAllDestinations();
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
            destinationList = new ArrayList<>();
        }

        destinationListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(DestinationDAO destination, boolean empty) {
                super.updateItem(destination, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = destination.getName();
                    setText(text);
                }
            }
        });
        ObservableList<DestinationDAO> observableList = FXCollections.observableList(destinationList);
        destinationListView.setItems(observableList);
    }

    @FXML
    void applyDestinationFilter(ActionEvent event) {
        DestinationDAO destination = destinationListView.getSelectionModel().getSelectedItem();
        filter.setDestination(destination);
        List<PackageDAO> resultList = null;
        try {
            resultList = userController.getAllFilteredPackages(filter);
            ObservableList<PackageDAO> observableList = FXCollections.observableList(resultList);
            packageTableView.setItems(observableList);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
    }

    @FXML
    void applyPriceFilter(ActionEvent event) {
        if (!priceFieldMin.getText().isEmpty())
            filter.setMinPrice(Integer.parseInt(priceFieldMin.getText()));
        if (!priceFieldMax.getText().isEmpty())
            filter.setMaxPrice(Integer.parseInt(priceFieldMax.getText()));
        List<PackageDAO> resultList = null;
        try {
            resultList = userController.getAllFilteredPackages(filter);
            ObservableList<PackageDAO> observableList = FXCollections.observableList(resultList);
            packageTableView.setItems(observableList);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }

    }

    @FXML
    void applyPeriodFilter(ActionEvent event) {
        Date dateFrom = Date.valueOf(fromDate.getValue());
        Date dateTo = Date.valueOf(toDate.getValue());
        filter.setStartPeriod(dateFrom);
        filter.setEndPeriod(dateTo);
        List<PackageDAO> resultList = null;
        try {
            resultList = userController.getAllFilteredPackages(filter);
            ObservableList<PackageDAO> observableList = FXCollections.observableList(resultList);
            packageTableView.setItems(observableList);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }

    }

    @FXML
    void reset() {
        loadPackages();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Utils.switchScene(event, "login.fxml", Utils.loginTitle);
    }

    @FXML
    void showUserBookings(ActionEvent event) throws IOException {
        Utils.switchScene(event, "user-bookings.fxml", Utils.userBookingsTitle);
    }
}
