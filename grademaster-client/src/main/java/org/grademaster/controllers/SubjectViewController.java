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

public class SubjectViewController {
    private SubjectView subjectView;
    private User user;

    public SubjectViewController(SubjectView subjectView) {
        this.subjectView = subjectView;
        fetchUserData();
        initialize();
    }

    public void fetchUserData() {
        user = SqlUtil.getUserByEmail(subjectView.getEmail());
    }

    private void initialize() {
        addMenuActions();
        subjectView.getDashboardButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                int subjectCount = SqlUtil.getSubjectCountForCurrentUser(user.getId());
                int studentCount = SqlUtil.getStudentCountUnderUser(user.getId());
                new DashboardView(subjectView.getEmail(), subjectCount, studentCount).show();
            }
        });
    }

    private void addMenuActions() { // Assigns the different views to their corresponding menu button
        subjectView.getCreateStudentMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new CreateNewStudentDialog(user).showAndWait();
            }
        });
        subjectView.getUpdateStudentMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new UpdateStudentDialog(user).showAndWait();
            }
        });
    }
}
