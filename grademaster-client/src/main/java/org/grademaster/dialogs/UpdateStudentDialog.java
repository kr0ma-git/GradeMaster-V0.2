package org.grademaster.dialogs;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.grademaster.models.User;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.time.LocalDate;

public class UpdateStudentDialog extends CustomDialog{
    private TextField firstNameField = new TextField();
    private TextField middleNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField emailField = new TextField();
    private DatePicker dateOfBirthPicker = new DatePicker();
    private TextField addressField = new TextField();
    private TextField contactNumberField = new TextField();
    private ComboBox<String> genderComboBox = new ComboBox<>();

    public UpdateStudentDialog(User user) {
        super(user);
        setTitle("Update Student");
        getDialogPane().setContent(createDialogContentBox());
        setupButtons();
    }

    public void setStudentData(String firstName, String middleName, String lastName,
                               String email, LocalDate dateOfBirth, String address,
                               String contactNumber, String gender) {
        firstNameField.setText(firstName);
        middleNameField.setText(middleName);
        lastNameField.setText(lastName);
        emailField.setText(email);
        dateOfBirthPicker.setValue(dateOfBirth);
        addressField.setText(address);
        contactNumberField.setText(contactNumber);
        genderComboBox.setValue(gender);
    }

    private VBox createDialogContentBox() {
        VBox dialogContentBox = new VBox(10);

        HBox headerBox = createHeader();
        GridPane formGrid = createForm();

        dialogContentBox.getChildren().addAll(headerBox, formGrid);
        return dialogContentBox;
    }

    private HBox createHeader() {
        FontIcon icon = new FontIcon(FontAwesomeSolid.USER_EDIT);
        icon.setIconSize(24);
        icon.setIconColor(Color.web("#f59e0b"));

        Label headerLabel = new Label("Update Student");
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

        // Row 0-1: First Name, Middle Name
        grid.add(createFieldLabel("First Name *"), 0, 0);
        firstNameField.setPromptText("Enter first name");
        firstNameField.getStyleClass().add("dialog-input");
        grid.add(firstNameField, 0, 1);

        grid.add(createFieldLabel("Middle Name"), 1, 0);
        middleNameField.setPromptText("Enter middle name");
        middleNameField.getStyleClass().add("dialog-input");
        grid.add(middleNameField, 1, 1);

        // Row 2-3: Last Name, Email
        grid.add(createFieldLabel("Last Name *"), 0, 2);
        lastNameField.setPromptText("Enter last name");
        lastNameField.getStyleClass().add("dialog-input");
        grid.add(lastNameField, 0, 3);

        grid.add(createFieldLabel("Email *"), 1, 2);
        emailField.setPromptText("student@email.com");
        emailField.getStyleClass().add("dialog-input");
        grid.add(emailField, 1, 3);

        // Row 4-5: Date of Birth, Gender
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

        // Row 6-7: Address (full width)
        grid.add(createFieldLabel("Address"), 0, 6);
        addressField.setPromptText("Enter full address");
        addressField.getStyleClass().add("dialog-input");
        grid.add(addressField, 0, 7, 2, 1);

        // Row 8-9: Contact Number
        grid.add(createFieldLabel("Contact Number"), 0, 8);
        contactNumberField.setPromptText("09XX XXX XXXX");
        contactNumberField.getStyleClass().add("dialog-input");
        grid.add(contactNumberField, 0, 9);

        return grid;
    }

    private void setupButtons() {
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(updateButtonType, cancelButtonType);

        Button updateButton = (Button) getDialogPane().lookupButton(updateButtonType);
        updateButton.getStyleClass().add("dialog-button-save");

        Button cancelButton = (Button) getDialogPane().lookupButton(cancelButtonType);
        cancelButton.getStyleClass().add("dialog-button-cancel");
    }

    private Label createFieldLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("dialog-field-label");
        return label;
    }

    // Getters
    public String getFirstName() { return firstNameField.getText().trim(); }
    public String getMiddleName() { return middleNameField.getText().trim(); }
    public String getLastName() { return lastNameField.getText().trim(); }
    public String getEmail() { return emailField.getText().trim(); }
    public LocalDate getDateOfBirth() { return dateOfBirthPicker.getValue(); }
    public String getAddress() { return addressField.getText().trim(); }
    public String getContactNumber() { return contactNumberField.getText().trim(); }
    public String getGender() { return genderComboBox.getValue(); }
}
