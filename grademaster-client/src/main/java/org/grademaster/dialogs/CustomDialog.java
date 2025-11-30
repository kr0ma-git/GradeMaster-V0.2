package org.grademaster.dialogs;

import javafx.scene.control.Dialog;
import org.grademaster.models.User;

public class CustomDialog extends Dialog {
    protected User user;
    // Constructor
    public CustomDialog(User user) {
        this.user = user;

        getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        getDialogPane().getStyleClass().addAll("dialog-box");
    }
}
