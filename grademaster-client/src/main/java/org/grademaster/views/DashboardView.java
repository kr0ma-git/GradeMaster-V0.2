package org.grademaster.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.grademaster.controllers.DashboardController;
import org.grademaster.utils.SqlUtil;
import org.grademaster.utils.Utility;
import org.grademaster.utils.ViewNavigator;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class DashboardView {
    private String email;
    private int subjectCountForCurrentUser;
    private int studentCountForCurrentUser;

    // Sidebar elements
    private Label brandLabel;

    private Button dashboardButton = new Button("Dashboard");
    private Button subjectButton = new Button("My Subjects");
    private Button studentsButton = new Button("My Students");
    private Button gradesButton = new Button("Grades");
    private Button logoutButton = new Button("Logout");

    // Menu Bar elements
    private MenuItem createStudentMenuItem;
    private MenuItem updateStudentMenuItem;
    private MenuItem deleteStudentMenuItem;
    private MenuItem createSubjectMenuItem;
    private MenuItem updateSubjectMenuItem;
    private MenuItem deleteSubjectMenuItem;

    // Content header
    private Label pageTitle = new Label("Dashboard");
    private Label userLabel;

    // Icon color
    private static final Color ICON_COLOR = Color.web("#a78bfa");
    private static final Color ICON_COLOR_LIGHT = Color.web("#e5e5e5");

    public DashboardView(String email, int subjectCountForCurrentUser, int studentCountForCurrentUser) {
        this.email = email;
        this.subjectCountForCurrentUser = subjectCountForCurrentUser;
        this.studentCountForCurrentUser = studentCountForCurrentUser;
        this.userLabel = new Label("Welcome, " + email);
        setupBrandLabel();
        setupSidebarIcons();
    }

    private void setupBrandLabel() {
        FontIcon bookIcon = new FontIcon(FontAwesomeSolid.GRADUATION_CAP);
        bookIcon.setIconSize(22);
        bookIcon.setIconColor(ICON_COLOR);

        brandLabel = new Label("GradeMaster");
        brandLabel.setGraphic(bookIcon);
        brandLabel.setGraphicTextGap(10);
    }

    private void setupSidebarIcons() {
        // Dashboard icon
        FontIcon dashIcon = new FontIcon(FontAwesomeSolid.HOME);
        dashIcon.setIconSize(16);
        dashIcon.setIconColor(ICON_COLOR_LIGHT);
        dashboardButton.setGraphic(dashIcon);
        dashboardButton.setGraphicTextGap(10);

        // Subjects icon
        FontIcon subjectIcon = new FontIcon(FontAwesomeSolid.BOOK);
        subjectIcon.setIconSize(16);
        subjectIcon.setIconColor(ICON_COLOR_LIGHT);
        subjectButton.setGraphic(subjectIcon);
        subjectButton.setGraphicTextGap(10);

        // Students icon
        FontIcon studentsIcon = new FontIcon(FontAwesomeSolid.USERS);
        studentsIcon.setIconSize(16);
        studentsIcon.setIconColor(ICON_COLOR_LIGHT);
        studentsButton.setGraphic(studentsIcon);
        studentsButton.setGraphicTextGap(10);

        // Grades icon
        FontIcon gradesIcon = new FontIcon(FontAwesomeSolid.CLIPBOARD_LIST);
        gradesIcon.setIconSize(16);
        gradesIcon.setIconColor(ICON_COLOR_LIGHT);
        gradesButton.setGraphic(gradesIcon);
        gradesButton.setGraphicTextGap(10);

        // Logout icon
        FontIcon logoutIcon = new FontIcon(FontAwesomeSolid.SIGN_OUT_ALT);
        logoutIcon.setIconSize(16);
        logoutIcon.setIconColor(Color.web("#f87171"));
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setGraphicTextGap(10);
    }

    public void show() {
        Scene scene = createScene();
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        new DashboardController(this);
        ViewNavigator.switchViews(scene);
    }

    private Scene createScene() {
        MenuBar unifiedMenuBar = createMenuBar();

        BorderPane root = new BorderPane();
        root.getStyleClass().add("dashboard-root");

        // TODO: Make it so that only admins can access the menu bar
        if (SqlUtil.getUserRole(email).equals("ADMIN")) {
            root.setTop(unifiedMenuBar); // Inserting the menu bar before everything else in the main container;
        }

        VBox sidebar = createSidebar();
        root.setLeft(sidebar);

        VBox mainContent = createMainContent();
        root.setCenter(mainContent);

        return new Scene(root, Utility.APP_WIDTH, Utility.APP_HEIGHT);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu studentMenu = new Menu("Students");
        Menu subjectMenu = new Menu("Subjects");

        createStudentMenuItem = new MenuItem("Add Student");
        updateStudentMenuItem = new MenuItem("Update Student");
        deleteStudentMenuItem = new MenuItem("Delete Student");

        createSubjectMenuItem = new MenuItem("Add Subject");
        updateSubjectMenuItem = new MenuItem("Update Subject");
        deleteSubjectMenuItem = new MenuItem("Delete Student");

        studentMenu.getItems().addAll(createStudentMenuItem, updateStudentMenuItem, deleteStudentMenuItem);
        subjectMenu.getItems().addAll(createSubjectMenuItem, updateSubjectMenuItem, deleteSubjectMenuItem);
        menuBar.getMenus().addAll(studentMenu, subjectMenu);

        return menuBar;
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(220);

        brandLabel.getStyleClass().add("brand-label");
        VBox brandBox = new VBox(brandLabel);
        brandBox.getStyleClass().add("brand-box");
        brandBox.setPadding(new Insets(20, 15, 30, 15));

        dashboardButton.getStyleClass().addAll("sidebar-button", "sidebar-button-active");
        subjectButton.getStyleClass().add("sidebar-button");
        studentsButton.getStyleClass().add("sidebar-button");
        gradesButton.getStyleClass().add("sidebar-button");

        VBox navBox = new VBox(5);
        navBox.setPadding(new Insets(0, 10, 0, 10));
        navBox.getChildren().addAll(dashboardButton, subjectButton, studentsButton, gradesButton);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        logoutButton.getStyleClass().add("logout-button");
        VBox logoutBox = new VBox(logoutButton);
        logoutBox.setPadding(new Insets(10));

        sidebar.getChildren().addAll(brandBox, navBox, spacer, logoutBox);
        return sidebar;
    }

    private VBox createMainContent() {
        VBox mainContent = new VBox(20);
        mainContent.getStyleClass().add("main-content");
        mainContent.setPadding(new Insets(25));

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        pageTitle.getStyleClass().add("page-title");
        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);
        userLabel.getStyleClass().add("user-label");
        header.getChildren().addAll(pageTitle, headerSpacer, userLabel);

        // Stats row
        HBox statsRow = new HBox(15);
        statsRow.getChildren().addAll(
                createStatCard("Your Total Subjects", String.valueOf(subjectCountForCurrentUser), FontAwesomeSolid.NOTES_MEDICAL, "#6366f1"),
                createStatCard("Your Total Students", String.valueOf(studentCountForCurrentUser), FontAwesomeSolid.USER_GRADUATE, "#10b981"),
                createStatCard("Current Semester", "2025-2026", FontAwesomeSolid.CALENDAR, "#f59e0b"),
                createStatCard("Average Grade", "null", FontAwesomeSolid.CHART_LINE, "#ef4444")
        );

        // Quick actions
        Label actionsLabel = new Label("Quick Actions");
        actionsLabel.getStyleClass().add("section-title");

        HBox actionsRow = new HBox(15);
        // If admin, all are shown
        if (SqlUtil.getUserRole(email).equals("ADMIN")) {
            actionsRow.getChildren().addAll(
                    createActionButton("Add Student", FontAwesomeSolid.USER_PLUS),
                    createActionButton("Add Subject", FontAwesomeSolid.BOOK_MEDICAL),
                    createActionButton("New Enrollment", FontAwesomeSolid.FILE_SIGNATURE),
                    createActionButton("View Reports", FontAwesomeSolid.CHART_BAR)
            );
        } else {
            actionsRow.getChildren().addAll(
                    createActionButton("New Enrollment", FontAwesomeSolid.FILE_SIGNATURE),
                    createActionButton("View Reports", FontAwesomeSolid.CHART_BAR)
            );
        }

        // Recent activity
        Label recentLabel = new Label("Recent Activity");
        recentLabel.getStyleClass().add("section-title");

        VBox recentActivity = new VBox(10);
        recentActivity.getStyleClass().add("recent-card");
        recentActivity.getChildren().addAll(
                createActivityItem("John Doe enrolled in Mathematics", FontAwesomeSolid.USER_PLUS),
                createActivityItem("Grade updated for Jane Smith in Science", FontAwesomeSolid.EDIT),
                createActivityItem("New subject 'History 101' added", FontAwesomeSolid.BOOK),
                createActivityItem("5 students enrolled in English Literature", FontAwesomeSolid.USERS)
        );

        mainContent.getChildren().addAll(header, statsRow, actionsLabel, actionsRow, recentLabel, recentActivity);
        return mainContent;
    }

    private VBox createStatCard(String title, String value, FontAwesomeSolid iconType, String color) {
        FontIcon icon = new FontIcon(iconType);
        icon.setIconSize(20);
        icon.setIconColor(Color.web(color));

        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("stat-value");
        valueLabel.setStyle("-fx-text-fill: " + color + ";");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-title");

        HBox topRow = new HBox(10);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.getChildren().addAll(icon, valueLabel);

        VBox card = new VBox(5);
        card.getStyleClass().add("stat-card");
        card.setAlignment(Pos.CENTER_LEFT);
        card.getChildren().addAll(topRow, titleLabel);
        HBox.setHgrow(card, Priority.ALWAYS);

        return card;
    }

    private VBox createActionButton(String text, FontAwesomeSolid iconType) {
        FontIcon icon = new FontIcon(iconType);
        icon.setIconSize(28);
        icon.setIconColor(ICON_COLOR);

        Label textLabel = new Label(text);
        textLabel.getStyleClass().add("action-text");

        VBox btn = new VBox(8);
        btn.getStyleClass().add("action-card");
        btn.setAlignment(Pos.CENTER);
        btn.getChildren().addAll(icon, textLabel);
        HBox.setHgrow(btn, Priority.ALWAYS);

        return btn;
    }

    private HBox createActivityItem(String text, FontAwesomeSolid iconType) {
        FontIcon icon = new FontIcon(iconType);
        icon.setIconSize(14);
        icon.setIconColor(ICON_COLOR);

        Label textLabel = new Label(text);
        textLabel.getStyleClass().add("activity-text");

        HBox item = new HBox(10);
        item.setAlignment(Pos.CENTER_LEFT);
        item.getChildren().addAll(icon, textLabel);

        return item;
    }

    // Getters
    public Button getDashboardButton() { return dashboardButton; }
    public Button getSubjectButton() { return subjectButton; }
    public Button getStudentsButton() { return studentsButton; }
    public Button getEnrollmentButton() { return gradesButton; }
    public Button getLogoutButton() { return logoutButton; }
    public MenuItem getCreateStudentMenuItem() { return createStudentMenuItem; }
    public MenuItem getUpdateStudentMenuItem() { return updateStudentMenuItem; }
    public MenuItem getDeleteStudentMenuItem() { return deleteStudentMenuItem; }
    public MenuItem getCreateSubjectMenuItem() { return createSubjectMenuItem; }
    public MenuItem getUpdateSubjectMenuItem() { return updateSubjectMenuItem; }
    public MenuItem getDeleteSubjectMenuItem() { return deleteSubjectMenuItem; }
    public String getEmail() {
        return email;
    }
}