package org.grademaster.utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Switches between the different views or scenes
 */

public class ViewNavigator {
    private static Stage mainStage;

    // Main stage setter
    public static void setMainStage(Stage stage) {
       mainStage = stage;
    }

    public static void switchViews(Scene scene) {
        if (mainStage != null) {
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
}
