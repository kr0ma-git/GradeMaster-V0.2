package org.grademaster.dialogs;

import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.grademaster.models.User;
import org.grademaster.utils.SqlUtil;
import org.grademaster.utils.Utility;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class CreateNewStudentDialog extends CustomDialog {
    private TextField firstNameField = new TextField();
    private TextField middleNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField emailField = new TextField();
    private DatePicker dateOfBirthPicker = new DatePicker();
    private TextField addressField = new TextField();
    private TextField contactNumberField = new TextField();
    private ComboBox<String> genderComboBox = new ComboBox<>();

    public CreateNewStudentDialog(User user) {
        super(user);
        setTitle("Add New Student");
        getDialogPane().setContent(createDialogContentBox());
        setupButtons();
    }

    private VBox createDialogContentBox() {
        VBox dialogContentBox = new VBox(10);

        // Header
        HBox headerBox = createHeader();
        // Form
        GridPane formGrid = createForm();

        dialogContentBox.getChildren().addAll(headerBox, formGrid);
        return dialogContentBox;
    }

    private HBox createHeader() {
        FontIcon icon = new FontIcon(FontAwesomeSolid.USER_PLUS);
        icon.setIconSize(24);
        icon.setIconColor(Color.web("#a78bfa"));

        Label headerLabel = new Label("Add New Student");
        headerLabel.getStyleClass().add("dialog-header");

        HBox headerBox = new HBox(10);
        headerBox.getChildren().addAll(icon, headerLabel);

        return headerBox;
    }

    private GridPane createForm() {
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20, 0, 10, 0));

        grid.add(createFieldLabel("First Name *"), 0, 0);
        firstNameField.setPromptText("Enter first name");
        firstNameField.getStyleClass().add("dialog-input");
        grid.add(firstNameField, 0, 1);

        grid.add(createFieldLabel("Middle Name *"), 1, 0);
        middleNameField.setPromptText("Enter middle name");
        middleNameField.getStyleClass().add("dialog-input");
        grid.add(middleNameField, 1, 1);

        grid.add(createFieldLabel("Last Name *"), 0, 2);
        lastNameField.setPromptText("Enter last name");
        lastNameField.getStyleClass().add("dialog-input");
        grid.add(lastNameField, 0, 3);

        grid.add(createFieldLabel("Email *"), 1, 2);
        emailField.setPromptText("student@email.com");
        emailField.getStyleClass().add("dialog-input");
        grid.add(emailField, 1, 3);

        grid.add(createFieldLabel("Date of Birth *"), 0, 4);
        dateOfBirthPicker.setPromptText("Select date");
        dateOfBirthPicker.getStyleClass().add("dialog-input");
        dateOfBirthPicker.setMaxWidth(Double.MAX_VALUE);
        grid.add(dateOfBirthPicker, 0, 5);

        grid.add(createFieldLabel("Gender *"), 1, 4);
        genderComboBox.getItems().addAll("Male", "Female", "Other");
        genderComboBox.setPromptText("Select gender");
        genderComboBox.getStyleClass().add("dialog-input");
        genderComboBox.setMaxWidth(Double.MAX_VALUE);
        grid.add(genderComboBox, 1, 5);

        grid.add(createFieldLabel("Address *"), 0, 6);
        addressField.setPromptText("Enter full address");
        addressField.getStyleClass().add("dialog-input");
        grid.add(addressField, 0, 7, 2, 1);

        grid.add(createFieldLabel("Contact Number *"), 0, 8);
        contactNumberField.setPromptText("09XX XXX XXXX");
        contactNumberField.getStyleClass().add("dialog-input");
        grid.add(contactNumberField, 0, 9);

        return grid;
    }

    private void  setupButtons() {
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

        Button saveButton = (Button) getDialogPane().lookupButton(saveButtonType);
        saveButton.getStyleClass().add("dialog-button-save");
        saveButton.setOnAction(event -> {
                if (!validateInput()) {
                    Utility.showAlertDialog(Alert.AlertType.ERROR, "Error Creating New Student");
                } else {
                    String firstName = firstNameField.getText();
                    String middleName = middleNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = emailField.getText();
                    String dateOfBirth = dateOfBirthPicker.getValue().toString();
                    String address = addressField.getText();
                    String contactNumber = contactNumberField.getText();
                    String gender = genderComboBox.getValue();

                    // Convert inputs into a JSON object to pass into the helper function to create the user
                    JsonObject studentData = new JsonObject();
                    studentData.addProperty("firstName", firstName);
                    studentData.addProperty("middleName", middleName);
                    studentData.addProperty("lastName", lastName);
                    studentData.addProperty("studentEmail", email);
                    studentData.addProperty("dateOfBirth", dateOfBirth);
                    studentData.addProperty("address", address);
                    studentData.addProperty("contactNumber", contactNumber);
                    studentData.addProperty("gender", gender);

                    boolean createStudentStatus = SqlUtil.postCreateStudent(studentData);

                    if (createStudentStatus) {
                        Utility.showAlertDialog(Alert.AlertType.INFORMATION, "Student Created");
                    } else {
                        Utility.showAlertDialog(Alert.AlertType.ERROR, "Student Creation Failed");
                        event.consume();
                    }
                }
            }
        );

        Button cancelButton = (Button) getDialogPane().lookupButton(cancelButtonType);
        cancelButton.getStyleClass().add("dialog-button-cancel");
    }

    private boolean validateInput() {
        if (firstNameField.getText().isEmpty() || middleNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty() || dateOfBirthPicker.getValue() == null || genderComboBox.getValue() == null || contactNumberField.getText().isEmpty() || getAddressField().getText().isEmpty()) { return false; }
        // Check if student already exists via email
        if (SqlUtil.getStudentByEmail(emailField.getText()) != null) return false;

        return true;
    }

    private Label createFieldLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("dialog-field-label");

        return label;
    }

    // Getters

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getMiddleNameField() {
        return middleNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public DatePicker getDateOfBirthPicker() {
        return dateOfBirthPicker;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public TextField getContactNumberField() {
        return contactNumberField;
    }

    public ComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }
}
