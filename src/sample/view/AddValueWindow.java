package sample.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import javafx.scene.control.TextField;


public class AddValueWindow {
    private Stage addValueStage;
    private GridPane gridPaneAddValueWindow;
    private TextField nameValue;
    private Label labelFroNameTextField;
    private Button addButton;


    AddValueWindow(){
        addValueStage = new Stage();
        nameValue = new TextField();
        addButton = new Button("Добавить");
        labelFroNameTextField = new Label("Название:");
        gridPaneAddValueWindow = new GridPane();
        gridPaneAddValueWindow.add(labelFroNameTextField,0,0);
        gridPaneAddValueWindow.add(nameValue,0,1);
        gridPaneAddValueWindow.add(addButton,0,2);
        gridPaneAddValueWindow.setHgap(10);
        gridPaneAddValueWindow.setVgap(10);
        gridPaneAddValueWindow.setPadding(new Insets(90,20,70,50));
        addValueStage.setScene(new Scene(gridPaneAddValueWindow,250,300));
        addValueStage.setTitle("Добавьте значение");

    }
    public void showWindow(){
        addValueStage.show();
    }

    public Stage getAddValueStage() {
        return addValueStage;
    }

    public void setAddValueStage(Stage addValueStage) {
        this.addValueStage = addValueStage;
    }

    public GridPane getGridPaneAddValueWindow() {
        return gridPaneAddValueWindow;
    }

    public void setGridPaneAddValueWindow(GridPane gridPaneAddValueWindow) {
        this.gridPaneAddValueWindow = gridPaneAddValueWindow;
    }

    public TextField getNameValue() {
        return nameValue;
    }

    public void setNameValue(TextField nameValue) {
        this.nameValue = nameValue;
    }

    public Label getLabelFroNameTextField() {
        return labelFroNameTextField;
    }

    public void setLabelFroNameTextField(Label labelFroNameTextField) {
        this.labelFroNameTextField = labelFroNameTextField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }


}
