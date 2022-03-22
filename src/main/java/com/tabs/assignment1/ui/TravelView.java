package com.tabs.assignment1.ui;


import com.tabs.assignment1.controller.AgencyController;
import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.transfer_objects.PackageDTO;
import com.tabs.assignment1.utils.ExceptionUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.tabs.assignment1.ui.Utils.currentAgency;

public class TravelView implements Initializable {
    private final AgencyController agencyController = new AgencyController();
    @FXML
    private ListView<DestinationDAO> destinationListView;
    @FXML
    private TableView<PackageDAO> packageTableView;
    @FXML
    private TableColumn<PackageDAO, String> destinationCol;
    @FXML
    private TableColumn<PackageDAO, String> nameCol;
    @FXML
    private TableColumn<PackageDAO, String> priceCol;
    @FXML
    private TableColumn<PackageDAO, String> startCol;
    @FXML
    private TableColumn<PackageDAO, String> endCol;
    @FXML
    private TableColumn<PackageDAO, String> detailCol;
    @FXML
    private TableColumn<PackageDAO, Integer> seatCol;
    @FXML
    private TableColumn<PackageDAO, String> statusCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurePackageTableView();
        loadDestinationList();
        loadPackages();
    }

    @FXML
    void addDestination() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Add New Destination");
        inputDialog.setContentText("Destination: ");
        inputDialog.showAndWait();
        String destinationName = inputDialog.getEditor().getText();
        agencyController.addDestination(destinationName);
        loadDestinationList();
    }

    @FXML
    void addPackage() {
        DestinationDAO selectedDestination =
                destinationListView.getSelectionModel().getSelectedItem();
        if (selectedDestination != null) {
            Dialog<PackageDAO> dialog = createAddPackageDialog(selectedDestination);
            Optional<PackageDAO> result = dialog.showAndWait();
            result.ifPresent(vacationPackage -> {
                loadPackages();
            });
        } else {
            Alert destinationError = new Alert(Alert.AlertType.ERROR, "Select a DestinationDAO first", ButtonType.OK);
            destinationError.showAndWait();
        }

    }

    @FXML
    void deleteDestination() {
        DestinationDAO selectedDestinationDAO = destinationListView.getSelectionModel().getSelectedItem();
        if (selectedDestinationDAO != null) {
            agencyController.deleteDestination(selectedDestinationDAO.getId());
            loadDestinationList();
            loadPackages();
        }
    }

    @FXML
    void deletePackage() {
        PackageDAO selectedPackage = packageTableView.getSelectionModel().getSelectedItem();
        if (selectedPackage != null) {
            agencyController.deletePackage(selectedPackage.getId());
            loadPackages();
        }
    }

    @FXML
    void editPackage() {
        PackageDAO selectedVacationPackage = packageTableView.getSelectionModel().getSelectedItem();
        if (selectedVacationPackage != null) {
            Dialog<PackageDAO> dialog = createEditPackageDialog(selectedVacationPackage);
            Optional<PackageDAO> result = dialog.showAndWait();
            result.ifPresent(vP -> {
                loadPackages();
            });
        } else {
            Alert vacationError = new Alert(Alert.AlertType.ERROR, "Select a vacation first", ButtonType.OK);
            vacationError.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Utils.switchScene(event, "login.fxml", Utils.loginTitle);
    }

    private Dialog<PackageDAO> createEditPackageDialog(PackageDAO selectedVacationPackage) {
        Dialog<PackageDAO> dialog = new Dialog<>();
        dialog.setHeaderText("Edit Package");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Text destination =
                new Text(selectedVacationPackage.getDestinationByDestinationId().getName());

        TextField name = new TextField();
        name.setPromptText("name");
        name.setText(selectedVacationPackage.getName());

        TextField price = new TextField();
        price.setPromptText("price");
        price.setText(String.valueOf(selectedVacationPackage.getPrice()));

        Date dateFromString = selectedVacationPackage.getStartDate();
        Date dateToString = selectedVacationPackage.getEndDate();

        DatePicker dateFrom = new DatePicker(dateFromString.toLocalDate());
        DatePicker dateTo = new DatePicker(dateToString.toLocalDate());

        TextField details = new TextField();
        details.setPromptText("details");
        details.setText(selectedVacationPackage.getDetails());


        TextField seats = new TextField();
        seats.setPromptText("seats");
        seats.setText(String.valueOf(selectedVacationPackage.getNoOfSpots()));

        dialogPane.setContent(new VBox(8, destination, name, price, dateFrom, dateTo, details, seats));
        dialog.setResultConverter((ButtonType b) -> {
            if (b == ButtonType.OK) {
                PackageDTO packageData = new PackageDTO(
                        name.getText(),
                        Integer.parseInt(price.getText()),
                        Date.valueOf(dateFrom.getValue()),
                        Date.valueOf(dateTo.getValue()),
                        details.getText(),
                        Integer.parseInt(seats.getText()),
                        selectedVacationPackage.getDestinationByDestinationId().getId(),
                        currentAgency.getId()
                );
                return agencyController.editPackage(packageData, selectedVacationPackage.getId());
            } else {
                return new PackageDAO();
            }
        });
        return dialog;
    }

    private Dialog<PackageDAO> createAddPackageDialog(DestinationDAO selectedDestination) {
        Dialog<PackageDAO> dialog = new Dialog<>();
        dialog.setHeaderText("Add New Package");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Text destination = new Text(selectedDestination.getName());

        TextField name = new TextField();
        name.setPromptText("name");

        TextField price = new TextField();
        price.setPromptText("price");

        DatePicker dateFrom = new DatePicker(LocalDate.now());
        DatePicker dateTo = new DatePicker(LocalDate.now());

        TextField details = new TextField();
        details.setPromptText("details");

        TextField seats = new TextField();
        seats.setPromptText("seats");

        dialogPane.setContent(new VBox(8, destination, name, price, dateFrom, dateTo, details, seats));


        dialog.setResultConverter((ButtonType b) -> {
            if (b == ButtonType.OK) {
                PackageDTO packageData = new PackageDTO(
                        name.getText(),
                        Integer.parseInt(price.getText()),
                        Date.valueOf(dateFrom.getValue()),
                        Date.valueOf(dateTo.getValue()),
                        details.getText(),
                        Integer.parseInt(seats.getText()),
                        selectedDestination.getId(),
                        currentAgency.getId()
                );
                return agencyController.addPackage(packageData);
            } else {
                return null;
            }
        });
        return dialog;
    }

    private void loadDestinationList() {
        List<DestinationDAO> destinationList = null;
        try {
            destinationList = agencyController.getAllDestinations();
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
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
        if (destinationList != null) {
            ObservableList<DestinationDAO> observableList =
                    FXCollections.observableList(destinationList);
            destinationListView.setItems(observableList);
        }
    }

    private void loadPackages() {//load packages for selected destination? or all packages
        List<PackageDAO> packageList = agencyController.getAllAgencyPackages(currentAgency);
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
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        detailCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        seatCol.setCellValueFactory(new PropertyValueFactory<>("noOfSpots"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}
