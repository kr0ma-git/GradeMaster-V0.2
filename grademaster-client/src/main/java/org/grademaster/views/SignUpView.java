package org.grademaster.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.grademaster.controllers.SignUpController;
import org.grademaster.utils.Utility;
import org.grademaster.utils.ViewNavigator;


public class SignUpView {
    private Label grademasterLabel =  new Label("GradeMaster");
    private TextField nameField = new TextField();
    private TextField usernameField = new TextField();
    private TextField emailField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private PasswordField rePasswordField = new PasswordField();
    private Button registerButton = new Button("Register");
    private Label loginLabel = new Label("Already have an account? Login here");

    public void show() {
        Scene scene = createScene();
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        new SignUpController(this);
        ViewNavigator.switchViews(scene);
    }

    public Scene createScene() {
        VBox mainContainerVBox = new VBox(44);

        mainContainerVBox.getStyleClass().addAll("main-background");
        mainContainerVBox.setAlignment(Pos.CENTER);
        grademasterLabel.getStyleClass().addAll("header", "text-white");
        grademasterLabel.setAlignment(Pos.TOP_CENTER);

        VBox signUpFormContainer = createSignUpForm();

        mainContainerVBox.getChildren().addAll(grademasterLabel, signUpFormContainer);
        return new Scene(mainContainerVBox, Utility.APP_WIDTH, Utility.APP_HEIGHT);
    }

    private VBox createSignUpForm() {
        VBox signUpForm = new VBox(41);

        signUpForm.setAlignment(Pos.CENTER);

        nameField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        nameField.setPromptText("Enter Name");
        nameField.setMaxWidth(473);

        usernameField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        usernameField.setPromptText("Enter Username");
        usernameField.setMaxWidth(473);

        emailField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        emailField.setPromptText("Enter Email");
        emailField.setMaxWidth(473);

        passwordField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(473);

        rePasswordField.getStyleClass().addAll("field-background", "text-light-gray", "text-size-md", "rounded-border");
        rePasswordField.setPromptText("Re-Enter Password");
        rePasswordField.setMaxWidth(473);

        registerButton.getStyleClass().addAll("text-size-md", "bg-light-blue", "text-white", "text-weight-700", "rounded-border");
        registerButton.setMaxWidth(173);

        loginLabel.getStyleClass().addAll("text-size-sm", "text-light-gray", "text-underline", "link-text");

        signUpForm.getChildren().addAll(nameField, usernameField, emailField, passwordField, rePasswordField, registerButton, loginLabel);

        return signUpForm;
    }

    public Label getGrademasterLabel() {
        return grademasterLabel;
    }

    public void setGrademasterLabel(Label grademasterLabel) {
        this.grademasterLabel = grademasterLabel;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public PasswordField getRePasswordField() {
        return rePasswordField;
    }

    public void setRePasswordField(PasswordField rePasswordField) {
        this.rePasswordField = rePasswordField;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }

    public Label getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(Label loginLabel) {
        this.loginLabel = loginLabel;
    }
}
