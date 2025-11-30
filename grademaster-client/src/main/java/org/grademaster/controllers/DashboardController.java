package org.grademaster.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.grademaster.dialogs.CreateNewStudentDialog;
import org.grademaster.dialogs.UpdateStudentDialog;
import org.grademaster.models.User;
import org.grademaster.utils.SqlUtil;
import org.grademaster.views.DashboardView;
import org.grademaster.views.SubjectView;


public class DashboardController {
    private DashboardView dashboardView;
    private User user;

    public DashboardController(DashboardView dashboardView) {
       this.dashboardView = dashboardView;
       fetchUserData();
       initialize();
    }

    public void fetchUserData() {
        user = SqlUtil.getUserByEmail(dashboardView.getEmail());
    }

    private void initialize() {
        addMenuActions();
        dashboardView.getSubjectButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new SubjectView("test@gmail.com").show();
            }
        });
    }

    private void addMenuActions() { // Assigns the different views to their corresponding menu button
        dashboardView.getCreateStudentMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new CreateNewStudentDialog(user).showAndWait();
            }
        });
        dashboardView.getUpdateStudentMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new UpdateStudentDialog(user).showAndWait();
            }
        });
    }
}
