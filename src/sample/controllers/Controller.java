package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import sample.nernetwork.ArtificialNeuralNetworks;
import sample.view.mainWindow;

import java.util.Vector;


public class Controller {
    mainWindow mainWindow;
    ArtificialNeuralNetworks learnNetwork;

    public Controller(mainWindow wind) {
        mainWindow = wind;
        learnNetwork = new ArtificialNeuralNetworks();
        mainWindow.getTopmenu().getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(mainWindow.getTextFieldsInput().size()>0) {
                    mainWindow.getVboxForInputTextFields().getChildren().remove(mainWindow.getTextFieldsInput().size() - 1);
                    mainWindow.getTextFieldsInput().remove(mainWindow.getTextFieldsInput().size() - 1);
                    mainWindow.update();
                }
            }
        });

        mainWindow.getAddValueWindow().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField newTexField = new TextField();
                Label nameValueLabel = new Label(mainWindow.getAddValueWindow().getNameValue().getText()+":");
                nameValueLabel.setMinWidth(60);
                HBox textFieldInputHBox= new HBox(10);
                textFieldInputHBox.getChildren().addAll(nameValueLabel,newTexField);
                mainWindow.getVboxForInputTextFields().getChildren().add(textFieldInputHBox);
                mainWindow.update();
                mainWindow.getTextFieldsInput().add(newTexField);
            }
        });

        mainWindow.getTopmenu().getMenus().get(0).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.getAddValueWindow().showWindow();
            }
        });


        mainWindow.getAddValuesForNetwork().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mainWindow.getVboxForInputTextFields().getChildren().size() != 0) {
                    Vector input = new Vector();
                    for (int i = 0; i < mainWindow.getTextFieldsInput().size(); i++) {
                        if (mainWindow.getTextFieldsInput().get(i).getText().equals("")) {
                            input.add(0);
                        } else {
                            input.add(Integer.parseInt(mainWindow.getTextFieldsInput().get(i).getText()));

                        }
                    }
                    if (mainWindow.getOutputValue().getText().equals("")) {
                        learnNetwork.getOutputValues().add(0);
                    } else {
                        learnNetwork.getOutputValues().add(Integer.parseInt(mainWindow.getOutputValue().getText()));
                    }
                    learnNetwork.getInputValues().add(input);
                    System.out.println(learnNetwork.getInputValues());
                    System.out.println(learnNetwork.getOutputValues());
                /*System.out.println(input);
                System.out.println(mainWindow.getOutputValue().getText());*/

                }
            }
        });
        mainWindow.getStartLearning().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                learnNetwork.buildNerNetwork();
                //System.out.println(learnNetwork.getAllNers());
                /*for(int i=0;i<learnNetwork.getAllNers().size();i++){
                    System.out.println(learnNetwork.getAllNers().get(i).getNumberOfNer());
                }*/
            }
        });

    }
    private void delete(ActionEvent e){

    }
}
