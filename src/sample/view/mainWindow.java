package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.scene.control.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class mainWindow {
    private ArrayList<TextField> textFields= new ArrayList<TextField>();
    private MenuBar topmenu;
    private BorderPane root;
    private Stage prim;
    Button test = new Button("test");
    private VBox vboxForTextFields;


    public mainWindow(Stage primSt) throws IOException {
        TextField dasd = new TextField();
        prim = primSt;
        root = new BorderPane();
        vboxForTextFields = new VBox();
        root.setLeft(vboxForTextFields);
        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*for(int i=0;i<textFields.size();i++){
                    System.out.println(textFields.get(i).getText());
                }*/
                System.out.println(topmenu.getMenus().get(0).getItems());
            }
        });

        vboxForTextFields.getChildren().addAll(test);
        root.setTop(menuBar());
        prim.setTitle("Hello World");
        prim.setScene(new Scene(root, 1200, 500));
        prim.show();
    }

    private MenuBar menuBar() {
        topmenu = new MenuBar();
        Menu textFieldsWorkMenu = new Menu("Входные значения");
        MenuItem add= new MenuItem("Добавть значение");
        MenuItem delete= new MenuItem("Удалить значение");
        textFieldsWorkMenu.getItems().addAll(add,delete);
        topmenu.getMenus().addAll(textFieldsWorkMenu);
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField newTexField = new TextField();
                vboxForTextFields.getChildren().addAll(newTexField);
                textFields.add(newTexField);
                update();
            }
        });
        return topmenu;
    }
    private void update(){
        prim.show();
    }

}
