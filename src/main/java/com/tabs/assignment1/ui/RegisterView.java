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


public class RegisterView implements Initializable {

    private final UserController userController = new UserController();

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    private PasswordField confPassTextField;

    @FXML
    private Button signupBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip tooltip = new Tooltip();
        tooltip.autoHideProperty().set(true);
        signupBtn.setTooltip(tooltip);
    }

    @FXML
    void signup(ActionEvent event) throws IOException {
        Window window = ((Node) event.getSource()).getScene().getWindow();

        if (passTextField.getText().equals(confPassTextField.getText())) {
            UserDTO registerUserData = new UserDTO(usernameTextField.getText(),
                    passTextField.getText());
            try {
                Utils.currentUser = userController.registerUser(registerUserData);
                Utils.switchScene(event, "login.fxml", Utils.loginTitle);
            } catch (Exception e) {
                ExceptionUtil.printExceptionStacktrace(e);
                Tooltip signupToolTip = signupBtn.getTooltip();
                signupToolTip.setText(e.getMessage());
                signupToolTip.show(window);
            }
        } else {
            Tooltip signupToolTip = signupBtn.getTooltip();
            signupToolTip.setText("Cannot confirm password");
            signupToolTip.show(window);
        }
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Utils.switchScene(event, "login.fxml", Utils.loginTitle);
    }
}
