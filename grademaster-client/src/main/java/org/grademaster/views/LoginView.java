package org.grademaster.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.grademaster.controllers.LoginController;
import org.grademaster.utils.Utility;
import org.grademaster.utils.ViewNavigator;

public class LoginView {
    private Label gradeMasterLabel = new Label("GradeMaster");
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button loginButton = new Button("Login");
    private Label signupLabel = new Label("Don't have an account? Click Here");

    public void show() {
        Scene scene = createScene();
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        new LoginController(this); // Instantiated as LoginController so that we can call the components there using the getters and the setters
        ViewNavigator.switchViews(scene);
    }

    private Scene createScene() {
        VBox mainContainerVBox = new VBox(43);

        // Main VBox Styling
        mainContainerVBox.getStyleClass().addAll("main-background");
        mainContainerVBox.setAlignment(Pos.CENTER);
        gradeMasterLabel.getStyleClass().addAll("header", "text-white");
        gradeMasterLabel.setAlignment(Pos.TOP_CENTER);

        // Creating Component VBox
        VBox loginFormBox = createLoginFormBox();

        // Loading Component VBox
        mainContainerVBox.getChildren().addAll(gradeMasterLabel, loginFormBox);

        return new Scene(mainContainerVBox, Utility.APP_WIDTH, Utility.APP_HEIGHT);
    }

    // Component Methods
    private VBox createLoginFormBox() {
        VBox loginFormVBox = new VBox(51); // Pass in with space value for each component in the VBox,meaning each component will receive 51 pixels of spacing amongst each other

        // Styling (Tailwind way)
        loginFormVBox.setAlignment(Pos.CENTER);

        usernameField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        usernameField.setPromptText("Enter Email");
        usernameField.setMaxWidth(473);

        passwordField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(473);

        loginButton.getStyleClass().addAll("text-size-md", "bg-light-blue", "text-white", "text-weight-700", "rounded-border");
        loginButton.setMaxWidth(173);

        signupLabel.getStyleClass().addAll("text-size-sm", "text-light-gray", "text-underline", "link-text");

        loginFormVBox.getChildren().addAll(usernameField, passwordField, loginButton, signupLabel);

        return loginFormVBox;
    }

    public Label getGradeMasterLabel() {
        return gradeMasterLabel;
    }

    public void setGradeMasterLabel(Label gradeMasterLabel) {
        this.gradeMasterLabel = gradeMasterLabel;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Label getSignupLabel() {
        return signupLabel;
    }

    public void setSignupLabel(Label signupLabel) {
        this.signupLabel = signupLabel;
    }
}
