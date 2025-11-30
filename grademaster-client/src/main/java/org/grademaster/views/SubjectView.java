package org.grademaster.views;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.grademaster.controllers.SubjectViewController;
import org.grademaster.models.User;
import org.grademaster.utils.SqlUtil;
import org.grademaster.utils.Utility;
import org.grademaster.utils.ViewNavigator;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class SubjectView {
    private String email;
    private User user;

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

    // Icon color
    private static final Color ICON_COLOR = Color.web("#a78bfa");
    private static final Color ICON_COLOR_LIGHT = Color.web("#e5e5e5");

    public SubjectView(String email) {
        this.email = email;
        this.user = SqlUtil.getUserByEmail(email);
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
        new SubjectViewController(this);
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

        dashboardButton.getStyleClass().add("sidebar-button");
        subjectButton.getStyleClass().addAll("sidebar-button", "sidebar-button-active");
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

        HBox header = createHeader();
        HBox filters = createFilters() ;
        FlowPane subjectsGrid = createSubjectsGrid();

        mainContent.getChildren().addAll(header, filters, subjectsGrid);
        return mainContent;
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        Label pageTitle = new Label("My Subjects");
        pageTitle.getStyleClass().add("page-title");

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Label userLabel = new Label("Welcome, " + email);
        userLabel.getStyleClass().add("user-label");

        header.getChildren().addAll(pageTitle, headerSpacer, userLabel);
        return header;
    }

    private HBox createFilters() {
        HBox filters = new HBox(15);
        filters.setAlignment(Pos.CENTER_LEFT);

        Label filterLabel = new Label("Filter:");
        filterLabel.getStyleClass().add("filter-label");

        ComboBox<String> semesterFilter = new ComboBox<>();
        semesterFilter.getItems().addAll("All Semesters", "1st Semester 2024-2025", "2nd Semester 2023-2024");
        semesterFilter.setValue("All Semesters");
        semesterFilter.getStyleClass().add("filter-combo");

        ComboBox<String> sectionFilter = new ComboBox<>();
        sectionFilter.getItems().addAll("All Sections", "Section A", "Section B", "Section C");
        sectionFilter.setValue("All Sections");
        sectionFilter.getStyleClass().add("filter-combo");

        TextField searchField = new TextField();
        searchField.setPromptText("Search subjects...");
        searchField.getStyleClass().add("search-field");
        searchField.setPrefWidth(250);

        FontIcon searchIcon = new FontIcon(FontAwesomeSolid.SEARCH);
        searchIcon.setIconSize(14);
        searchIcon.setIconColor(Color.web("#a3a3a3"));

        filters.getChildren().addAll(filterLabel, semesterFilter, sectionFilter, searchField);
        return filters;
    }

    private FlowPane createSubjectsGrid() {
        FlowPane grid = new FlowPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.getStyleClass().add("subjects-grid");


        JsonArray subjects = SqlUtil.getSubjectsForUser(user.getId());

        for (JsonElement elem : subjects) {
            JsonObject subjectRelation = elem.getAsJsonObject();
            JsonObject subject = subjectRelation.get("subject").getAsJsonObject();

            String code = subject.get("code").getAsString();
            String title = subject.get("title").getAsString();
            int units = subject.get("units").getAsInt();

            String semesterNumber = subjectRelation.get("semester").getAsString();
            String schoolYear = subjectRelation.get("schoolYear").getAsString();
            String semester = semesterNumber + " Semester " + schoolYear;

            String sectionLetter = subjectRelation.get("section").getAsString();
            String section = "Section " + sectionLetter;


            grid.getChildren().add(createSubjectCard(code, title, section, units, semester));
        }

        return grid;
    }

    private VBox createSubjectCard(String code, String title, String section, int units, String semester/*,int studentCount*/) {
        VBox card = new VBox(12);
        card.getStyleClass().add("subject-card");
        card.setPrefWidth(280);

        // Subject code and title
        Label codeLabel = new Label(code);
        codeLabel.getStyleClass().add("subject-code");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("subject-title");

        // Info row (section and units)
        Label sectionLabel = new Label(section);
        sectionLabel.getStyleClass().add("subject-info");

        Label divider = new Label("│");
        divider.getStyleClass().add("subject-divider");

        Label unitsLabel = new Label(units + " units");
        unitsLabel.getStyleClass().add("subject-info");

        HBox infoRow = new HBox(8);
        infoRow.getChildren().addAll(sectionLabel, divider, unitsLabel);

        // Semester
        Label semesterLabel = new Label(semester);
        semesterLabel.getStyleClass().add("subject-semester");

        // Separator line
        Separator separator = new Separator();
        separator.getStyleClass().add("card-separator");

        /*
        // Student count with icon
        FontIcon studentIcon = new FontIcon(FontAwesomeSolid.USERS);
        studentIcon.setIconSize(14);
        studentIcon.setIconColor(ICON_COLOR);

        Label studentLabel = new Label(studentCount + " students");
        studentLabel.getStyleClass().add("student-count");

        HBox studentRow = new HBox(8);
        studentRow.setAlignment(Pos.CENTER_LEFT);
        studentRow.getChildren().addAll(studentIcon, studentLabel);
        */

        // Action buttons
        Button viewDetailsBtn = new Button("View Details");
        viewDetailsBtn.getStyleClass().add("card-button-primary");

        Button gradesBtn = new Button("Grades");
        gradesBtn.getStyleClass().add("card-button-secondary");

        HBox buttonRow = new HBox(8);
        buttonRow.getChildren().addAll(viewDetailsBtn, gradesBtn);
        HBox.setHgrow(viewDetailsBtn, Priority.ALWAYS);
        HBox.setHgrow(gradesBtn, Priority.ALWAYS);
        viewDetailsBtn.setMaxWidth(Double.MAX_VALUE);
        gradesBtn.setMaxWidth(Double.MAX_VALUE);

        card.getChildren().addAll(codeLabel, titleLabel, infoRow, semesterLabel, separator, /*studentRow,*/ buttonRow);
        return card;
    }

    // Getters
    public Button getDashboardButton() {
        return dashboardButton;
    }

    public Button getSubjectButton() {
        return subjectButton;
    }

    public Button getStudentsButton() {
        return studentsButton;
    }

    public Button getGradesButton() {
        return gradesButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public MenuItem getCreateStudentMenuItem() {
        return createStudentMenuItem;
    }

    public MenuItem getUpdateStudentMenuItem() {
        return updateStudentMenuItem;
    }

    public MenuItem getDeleteStudentMenuItem() {
        return deleteStudentMenuItem;
    }

    public MenuItem getCreateSubjectMenuItem() {
        return createSubjectMenuItem;
    }

    public MenuItem getUpdateSubjectMenuItem() {
        return updateSubjectMenuItem;
    }

    public MenuItem getDeleteSubjectMenuItem() {
        return deleteSubjectMenuItem;
    }

    public String getEmail() {
        return email;
    }
}
