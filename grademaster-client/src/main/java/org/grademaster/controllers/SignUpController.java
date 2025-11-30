package org.grademaster.controllers;

import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.grademaster.utils.SqlUtil;
import org.grademaster.utils.Utility;
import org.grademaster.views.LoginView;
import org.grademaster.views.SignUpView;

public class SignUpController {
    private SignUpView signUpView;

    public SignUpController(SignUpView signUpView) {
        this.signUpView = signUpView;
        initialize();
    }

    private void initialize() {
        signUpView.getRegisterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateInput()) {
                    Utility.showAlertDialog(Alert.AlertType.ERROR, "Invalid Input");
                    return;
                }

                // Extracting data in the field if form is validated
                String name = signUpView.getNameField().getText();
                String username = signUpView.getUsernameField().getText();
                String email = signUpView.getEmailField().getText();
                String password = signUpView.getPasswordField().getText();
                String role = "TEACHER";


                // GSON data encoding
                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("name", name);
                jsonData.addProperty("username", username);
                jsonData.addProperty("email", email);
                jsonData.addProperty("password", password);
                jsonData.addProperty("role", role);

                // Sending POST request
                boolean postCreateAccountStatus = SqlUtil.postCreateUser(jsonData);

                // Depending on the result, display a message
                if (postCreateAccountStatus) {
                    Utility.showAlertDialog(Alert.AlertType.INFORMATION, "Successfully created a new account");
                    new LoginView().show(); // Take the user back to the login view
                } else {
                    Utility.showAlertDialog(Alert.AlertType.ERROR, "Failed to create new account...");
                }
            }
        });

        signUpView.getLoginLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new LoginView().show();
            }
        });
    }

    private boolean validateInput() {
        if (signUpView.getNameField().getText().isEmpty() || signUpView.getUsernameField().getText().isEmpty() || signUpView.getPasswordField().getText().isEmpty() || signUpView.getRePasswordField().getText().isEmpty()) return false;
        if (!signUpView.getPasswordField().getText().equals(signUpView.getRePasswordField().getText())) return false;

        // Check if user already exists
        if (SqlUtil.getUserByEmail(signUpView.getEmailField().getText()) != null) return false;

        return true;
    }
}
