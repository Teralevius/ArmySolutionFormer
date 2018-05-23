package sample.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class mainWindow {
    private ArrayList<TextField> textFieldsInput = new ArrayList<TextField>();
    private ArrayList<Label> textFieldsNames = new ArrayList<Label>();
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
    private TextField coeff;
    private VBox leftPartOfInterface;
    private Label inputValueLabel;
    private HBox buttonControllForNer;
    private Button findSolution;
    private static Label coeffLabel;
    private Label progressLearning;
    private Label resultLabel;
    private HBox centerPartOfInterface;
    private TableView tableEnityValues;
    private ScrollPane scrollPaneForInputValuesVBox;
    private ScrollPane scrollPaneForTable;
    private VBox  bottomPartOfInterface;
    private VBox forTable;

    public mainWindow(Stage primSt) throws IOException {
        prim = primSt;
        root = new BorderPane();
        addValueWindow = new AddValueWindow();
        leftPartOfInterface = new VBox(10);
        centerPartOfInterface = new HBox(10);
        forTable = new VBox();
        tableEnityValues = new TableView();
        forTable.setPadding(new Insets(10,0,0,0));
        forTable.getChildren().add(tableEnityValues);
        //scrollPaneForTable = new ScrollPane();
        //scrollPaneForTable.setContent(tableEnityValues);
        //scrollPaneForTable.setMaxHeight(300);
        //scrollPaneForTable.setMaxWidth(500);
        inputValueLabel = new Label("Входные значения:");
        leftPartOfInterface.getChildren().add(inputValueLabel);
        leftPartOfInterface.setPadding(new Insets(10,5,5,15));
        vboxForInputTextFields = new VBox(10);
        vboxForInputTextFields.setPadding(new Insets(10,5,5,5));
        vboxForInputTextFields.setMaxWidth(200);
        vboxForInputTextFields.setMinWidth(200);
        scrollPaneForInputValuesVBox = new ScrollPane();
        scrollPaneForInputValuesVBox.setMaxHeight(150);
        scrollPaneForInputValuesVBox.setContent(vboxForInputTextFields);
        leftPartOfInterface.getChildren().add(scrollPaneForInputValuesVBox);
        vboxForOutputTextFields = new VBox(10);

        vboxForOutputTextFields.setMaxWidth(200);
        vboxForOutputTextFields.setPadding(new Insets(10,5,5,5));
        outputValueLabel = new Label("Выходное значение:");
        outputValue = new TextField();
        coeff = new TextField();
        coeffLabel = new Label("Коэффициент:");
        findSolution = new Button("Найти Альтернативу");

        vboxForOutputTextFields.getChildren().addAll(outputValueLabel,outputValue,coeffLabel,coeff,findSolution);
        centerPartOfInterface.getChildren().addAll(vboxForOutputTextFields,forTable);
        root.setLeft(leftPartOfInterface);
        root.setCenter(centerPartOfInterface);

        buttonControllForNer = new HBox(10);
        addValuesForNetwork = new Button("Добавить выборку");
        startLearning = new Button("Начать обучение");
        buttonControllForNer.getChildren().addAll(addValuesForNetwork,startLearning);
        leftPartOfInterface.getChildren().add(buttonControllForNer);
        root.setTop(menuBar());
        bottomPartOfInterface = new VBox(20);
        bottomPartOfInterface.setPadding(new Insets(10,0,30,300));
        progressLearning = new Label("ПРОВЕРКА");
        resultLabel = new Label("Результат:");
        bottomPartOfInterface.getChildren().addAll(progressLearning,resultLabel);
        root.setBottom(bottomPartOfInterface);
        prim.setTitle("ArmySolutionFormer");
        prim.setScene(new Scene(root, 700, 320));
        prim.show();
    }

    public TableColumn<ObservableList<StringProperty>,String> createColumn(final int columnIndex, String nameColumn){
        TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
        column.setText(nameColumn);
        column
                .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
                        ObservableList<StringProperty> values = cellDataFeatures.getValue();
                        if (columnIndex >= values.size()) {
                            return new SimpleStringProperty("");
                        } else {
                            return cellDataFeatures.getValue().get(columnIndex);
                        }
                    }
                });
        return column;
    }


    public TableView getTableEnityValues() {
        return tableEnityValues;
    }

    public void setTableEnityValues(TableView tableEnityValues) {
        this.tableEnityValues = tableEnityValues;
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
        Menu instruments = new Menu ("Инструменты");
        MenuItem addInput= new MenuItem("Добавть значение");
        MenuItem deleteInput= new MenuItem("Удалить значение");
        MenuItem clearAll = new MenuItem ("Очистить всё");
        textFieldsInputWorkMenu.getItems().addAll(addInput,deleteInput);
        instruments.getItems().addAll(clearAll);

        topmenu.getMenus().addAll(textFieldsInputWorkMenu,instruments);
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

    public ArrayList<Label> getTextFieldsNames() {
        return textFieldsNames;
    }

    public void setTextFieldsNames(ArrayList<Label> textFieldsNames) {
        this.textFieldsNames = textFieldsNames;
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

    public TextField getCoeff() {
        return coeff;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(Label resultLabel) {
        this.resultLabel = resultLabel;
    }

    public void setCoeff(TextField coeff) {
        this.coeff = coeff;
    }

    public Label getProgressLearning() {
        return progressLearning;
    }

    public void setProgressLearning(Label progressLearning) {
        this.progressLearning = progressLearning;
    }

    public Button getFindSolution() {
        return findSolution;
    }

    public void setFindSolution(Button findSolution) {
        this.findSolution = findSolution;
    }
}
