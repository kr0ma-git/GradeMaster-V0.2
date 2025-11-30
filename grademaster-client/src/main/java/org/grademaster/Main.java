package org.grademaster;

import javafx.application.Application;
import javafx.stage.Stage;
import org.grademaster.utils.ViewNavigator;
import org.grademaster.views.DashboardView;
import org.grademaster.views.LoginView;
import org.grademaster.views.SignUpView;
import org.grademaster.views.SubjectView;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("GradeMaster");
        ViewNavigator.setMainStage(stage);
        //new DashboardView("test@gmail.com", 2).show();
        //new SignUpView().show();
        new LoginView().show();
    }
}