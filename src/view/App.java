package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import controller.MainController;
import controller.PaneNavigator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Class;
import model.Course;
import model.Instructor;
import model.Room;
import model.StudentsGroup;

public class App extends Application {

    private Stage primaryStage;
    private ObservableList<Room> RoomData = FXCollections.observableArrayList();
    private ObservableList<StudentsGroup> GroupData = FXCollections.observableArrayList();
    private ObservableList<Course> CourseData = FXCollections.observableArrayList();
    private ObservableList<Instructor> InstructorData = FXCollections.observableArrayList();
    private ObservableList<Class> ClassData = FXCollections.observableArrayList();
    private HashMap<String,Boolean> workingDays = new HashMap<>();
    private ObservableList<Class> generatedTableData = FXCollections.observableArrayList();
    private int periodsCount = 5;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        initialize();
        primaryStage.setTitle("Time Table Generator");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:resources/images/logo.png"));
        primaryStage.setScene(createScene(loadMainPane()));
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initialize(){
        System.out.println("init");
        Room room101 = new Room("101");
        Room room102 = new Room("102");
        RoomData.addAll(room101,room102);

        StudentsGroup firstYear = new StudentsGroup("First Year");
        StudentsGroup secondYear = new StudentsGroup("Second Year");
        StudentsGroup thirdYear = new StudentsGroup("Third Year");
        StudentsGroup fourthYear = new StudentsGroup("Fourth Year");
        GroupData.addAll(firstYear,secondYear,thirdYear,fourthYear);

        Course first351B = new Course("CC","351B");
        Course first352B = new Course("OOADJ","352B");
        Course first341B = new Course("CD","341B");
        Course first342AB4 = new Course("ROBOTICS","342AB4");
        Course first342AB7 = new Course("BC","342AB7");
        Course first242AB1 = new Course("AC","242AB1");
        Course first242AB2= new Course("CA","242AB2");
        Course first242AB3 = new Course("KM","242AB3");
        Course first242AB4 = new Course("LK","242AB4");
        Course first242AB5 = new Course("HN","242AB5");

        CourseData.addAll(first351B,first352B,first341B,first342AB4,first342AB7,first242AB1,first242AB2,first242AB3,first242AB4,first242AB5);

        Instructor PROF_SHRUTHI = new Instructor("PROF_SHRUTHI");
        Instructor PROF_VINAY_JOSHI = new Instructor("PROF_VINAY_JOSHI");
        Instructor PROF_POONAM_KUMARI = new Instructor("PROF_POONAM_KUMARI");
        Instructor PROF_ASHOK = new Instructor("PROF_ASHOK");
        Instructor PROF_SHRUTI= new Instructor("PROF_SHRUTI");
        Instructor PROF_AMS= new Instructor("PROF_AMS");
        Instructor PROF_KSS= new Instructor("PROF_KSS");
        Instructor PROF_ABS= new Instructor("PROF_ABS");
        Instructor PROF_CBJ= new Instructor("PROF_CBJ");
        Instructor PROF_MK= new Instructor("PROF_MK");

        InstructorData.addAll(PROF_SHRUTHI,PROF_VINAY_JOSHI,PROF_POONAM_KUMARI,PROF_ASHOK,PROF_SHRUTI,PROF_MK,PROF_KSS,PROF_CBJ,PROF_AMS,PROF_ABS);

        Class CC = new Class(first351B,"Lec",PROF_SHRUTHI,firstYear,room101);
        Class OOADJ = new Class(first352B,"Lec",PROF_VINAY_JOSHI,firstYear,room101);
        Class CD = new Class(first341B,"Lec",PROF_POONAM_KUMARI,firstYear,room101);
        Class ROBOTICS = new Class(first342AB4,"Lec",PROF_ASHOK,firstYear,room101);
        Class BC = new Class(first342AB7,"Lec",PROF_SHRUTI,firstYear,room101);

        Class AC = new Class(first242AB1,"Lec",PROF_AMS,secondYear,room102);
        Class CA = new Class(first242AB2,"Lec",PROF_KSS,secondYear,room102);
        Class KM = new Class(first242AB3,"Lec",PROF_ABS,secondYear,room102);
        Class LK = new Class(first242AB4,"Lec",PROF_CBJ,firstYear,room102);
        Class HN = new Class(first242AB5,"Lec",PROF_MK,firstYear,room102);

        ClassData.addAll(CC,OOADJ,CD,ROBOTICS,BC);

        workingDays.put("saturday",true);
        workingDays.put("sunday",true);
        workingDays.put("monday",true);
        workingDays.put("tuesday",true);
        workingDays.put("wednesday",true);
        workingDays.put("thursday",false);
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(new URL("file:resources/fxml/" + PaneNavigator.MAIN_PANE));
        //loader.setLocation(new URL("file:resources/fxml/" + PaneNavigator.MAIN_PANE));
        //Pane mainPane = loader.load();
        Pane mainPane = loader.load();
        MainController mainController = loader.getController();
        PaneNavigator.setMainApp(this);
        PaneNavigator.setMainController(mainController);
        PaneNavigator.loadPane(PaneNavigator.START_PANE);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        //new URL("file:resources/style/tab.css)
        File f = new File("resources/style/tab.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        //scene.getStylesheets().add(this.getClass().getResource("/resources/style/tab.css").toExternalForm());
        f = new File("resources/style/style.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        return scene;
    }


    public static void main(String[] args) {
        launch();
    }

    public ObservableList<Room> getRoomData() {
        return RoomData;
    }

    public ObservableList<StudentsGroup> getGroupData() {
        return GroupData;
    }

    public ObservableList<Course> getCourseData() {
        return CourseData;
    }

    public ObservableList<Instructor> getInstrutorData() {
        return InstructorData;
    }

    public ObservableList<Class> getClassData() {
        return ClassData;
    }

    public HashMap<String,Boolean> getWorkingDays() {
        return workingDays;
    }

    public int getPeriodsCount() {
        return periodsCount;
    }

    public void setPeriodsCount(int periodsCount) {
        this.periodsCount = periodsCount;
    }

    public ObservableList<Class> getGeneratedTableData() {
        return generatedTableData;
    }

    public void setGeneratedTableData(ArrayList<Class> generatedtable){
        generatedTableData.clear();
        generatedTableData.addAll(generatedtable);
    }
}