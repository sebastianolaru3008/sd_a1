package com.tabs.assignment1.ui;

import com.tabs.assignment1.controller.UserController;
import com.tabs.assignment1.model.transfer_objects.UserDTO;
import com.tabs.assignment1.utils.ExceptionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.tabs.assignment1.ui.Utils.*;

public class LoginView implements Initializable {
    private final UserController userController = new UserController();

    @FXML
    public Button signupBtn;
    @FXML
    public Button travelAgencyBtn;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip tooltip = new Tooltip();
        tooltip.autoHideProperty().set(true);
        loginBtn.setTooltip(tooltip);
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        Window window = ((Node) event.getSource()).getScene().getWindow();

        UserDTO loginDetails = new UserDTO(usernameTextField.getText(), passwordTextField.getText());

        try {
            currentUser = userController.loginUser(loginDetails);
            switchScene(event, "user.fxml", Utils.userTitle);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
            Tooltip tooltip = loginBtn.getTooltip();
            tooltip.setText(e.getMessage());
            tooltip.show(window);
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        switchScene(event, "register.fxml", Utils.registerTitle);
    }

    @FXML
    void openTravelAgentScreen(ActionEvent event) throws IOException {
        Window window = ((Node) event.getSource()).getScene().getWindow();

        UserDTO loginDetails = new UserDTO(usernameTextField.getText(), passwordTextField.getText());

        try {
            currentUser = userController.loginUser(loginDetails);
            currentAgency = currentUser.getAgencyByAgencyId();
            switchScene(event, "travel-agency.fxml", Utils.userTitle);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
            Tooltip tooltip = loginBtn.getTooltip();
            tooltip.setText(e.getMessage());
            tooltip.show(window);
        }
    }
}
