package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class mainWindow {
    private ArrayList<TextField> textFieldsInput = new ArrayList<TextField>();
    private MenuBar topmenu;
    private BorderPane root;
    private Stage prim;
    private AddValueWindow addValueWindow;
    private VBox vboxForInputTextFields;
    private VBox vboxForOutputTextFields;
    private Button addValuesForNetwork;
    private Button startLearning;
    private Label outputValueLabel;
    private TextField outputValue;
    private VBox leftPartOfInterface;
    private Label inputValueLabel;
    private HBox buttonControllForNer;


    public mainWindow(Stage primSt) throws IOException {
        prim = primSt;
        root = new BorderPane();
        addValueWindow = new AddValueWindow();
        leftPartOfInterface = new VBox(10);
        inputValueLabel = new Label("Входные значения:");
        leftPartOfInterface.getChildren().add(inputValueLabel);
        leftPartOfInterface.setPadding(new Insets(10,5,5,5));
        vboxForInputTextFields = new VBox(10);
        vboxForInputTextFields.setPadding(new Insets(10,5,5,5));
        vboxForInputTextFields.setMaxWidth(200);
        leftPartOfInterface.getChildren().add(vboxForInputTextFields);
        vboxForOutputTextFields = new VBox(20);
        vboxForOutputTextFields.setMaxWidth(200);
        vboxForOutputTextFields.setPadding(new Insets(10,5,5,5));
        outputValueLabel = new Label("Выходное значение:");
        outputValue = new TextField();
        vboxForOutputTextFields.getChildren().addAll(outputValueLabel,outputValue);

        root.setAlignment(vboxForOutputTextFields, Pos.CENTER_LEFT);
        root.setLeft(leftPartOfInterface);
        root.setCenter(vboxForOutputTextFields);

        buttonControllForNer = new HBox(10);
        addValuesForNetwork = new Button("Добавить выборку");
        startLearning = new Button("Начать обучение");
        buttonControllForNer.getChildren().addAll(addValuesForNetwork,startLearning);
        leftPartOfInterface.getChildren().add(buttonControllForNer);
        root.setTop(menuBar());
        prim.setTitle("ArmySolutionFormer");
        prim.setScene(new Scene(root, 1200, 500));
        prim.show();
    }

    public Button getAddValuesForNetwork() {
        return addValuesForNetwork;
    }

    public void setAddValuesForNetwork(Button addValuesForNetwork) {
        this.addValuesForNetwork = addValuesForNetwork;
    }

    public VBox getVboxForOutputTextFields() {
        return vboxForOutputTextFields;
    }

    public void setVboxForOutputTextFields(VBox vboxForOutputTextFields) {
        this.vboxForOutputTextFields = vboxForOutputTextFields;
    }

    private MenuBar menuBar() {
        topmenu = new MenuBar();
        Menu textFieldsInputWorkMenu = new Menu("Входные значения");

        MenuItem addInput= new MenuItem("Добавть значение");
        MenuItem deleteInput= new MenuItem("Удалить значение");
        textFieldsInputWorkMenu.getItems().addAll(addInput,deleteInput);

        topmenu.getMenus().addAll(textFieldsInputWorkMenu);
        return topmenu;
    }
    public void update(){
        prim.show();
    }

    public ArrayList<TextField> getTextFieldsInput() {
        return textFieldsInput;
    }

    public void setTextFieldsInput(ArrayList<TextField> textFieldsInput) {
        this.textFieldsInput = textFieldsInput;
    }

    public MenuBar getTopmenu() {
        return topmenu;
    }

    public void setTopmenu(MenuBar topmenu) {
        this.topmenu = topmenu;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public Stage getPrim() {
        return prim;
    }

    public void setPrim(Stage prim) {
        this.prim = prim;
    }

    public VBox getVboxForInputTextFields() {
        return vboxForInputTextFields;
    }

    public void setVboxForInputTextFields(VBox vboxForInputTextFields) {
        this.vboxForInputTextFields = vboxForInputTextFields;
    }

    public Button getStartLearning() {
        return startLearning;
    }

    public void setStartLearning(Button startLearning) {
        this.startLearning = startLearning;
    }

    public AddValueWindow getAddValueWindow() {
        return addValueWindow;
    }

    public void setAddValueWindow(AddValueWindow addValueWindow) {
        this.addValueWindow = addValueWindow;
    }

    public TextField getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(TextField outputValue) {
        this.outputValue = outputValue;
    }

    public VBox getLeftPartOfInterface() {
        return leftPartOfInterface;
    }

    public void setLeftPartOfInterface(VBox leftPartOfInterface) {
        this.leftPartOfInterface = leftPartOfInterface;
    }
}
