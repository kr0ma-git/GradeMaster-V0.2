package org.grademaster.controllers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.grademaster.models.User;
import org.grademaster.utils.ApiUtil;
import org.grademaster.utils.SqlUtil;
import org.grademaster.utils.Utility;
import org.grademaster.views.DashboardView;
import org.grademaster.views.LoginView;
import javafx.scene.input.MouseEvent;
import org.grademaster.views.SignUpView;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }

    private void initialize() {
        loginView.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateUser()) { return; }

                String email = loginView.getUsernameField().getText();
                String password = loginView.getPasswordField().getText();

                if (SqlUtil.postLoginUser(email, password)) {
                    User user = SqlUtil.getUserByEmail(email);
                    int subjectCountForCurrentUser = SqlUtil.getSubjectCountForCurrentUser(user.getId());
                    int studentCountUnderUser = SqlUtil.getStudentCountUnderUser(user.getId());
                    if (subjectCountForCurrentUser != -1 && studentCountUnderUser != -1) {
                        Utility.showAlertDialog(Alert.AlertType.INFORMATION, "Login Successful!");
                        new DashboardView(email, subjectCountForCurrentUser, studentCountUnderUser).show();
                    }
                } else {
                    Utility.showAlertDialog(Alert.AlertType.ERROR, "Failed to Authenticate");
                }
            }
        });

        loginView.getSignupLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new SignUpView().show(); // Instantiate a SignUpView scene and set it as the scene to be displayed by the main container via the ViewNavigator
            }
        });
    }

    private boolean validateUser() {
        if (loginView.getUsernameField().getText().isEmpty() || loginView.getPasswordField().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
