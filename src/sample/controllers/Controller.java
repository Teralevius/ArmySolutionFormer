package sample.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.nernetwork.NerNetwork;
import sample.view.mainWindow;

import java.nio.DoubleBuffer;
import java.util.Vector;


public class Controller {
    mainWindow mainWindow;
    NerNetwork learnNetwork;
    private ObservableList<StringProperty> data;
    public Controller(mainWindow wind) {
        mainWindow = wind;
        learnNetwork = new NerNetwork();
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

        mainWindow.getTopmenu().getMenus().get(1).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.getVboxForInputTextFields().getChildren().clear();
                mainWindow.getTextFieldsNames().clear();
                mainWindow.getTextFieldsInput().clear();
                mainWindow.getTableEnityValues().getColumns().clear();
                mainWindow.getTableEnityValues().getItems().clear();
                if(data!=null) {
                    data.clear();
                }
                learnNetwork.getInputValues().clear();
                learnNetwork.getOutputValues().clear();
                learnNetwork.getConvertedInput().clear();
                learnNetwork.getConvertedOutput().clear();
            }
        });

        mainWindow.getAddValueWindow().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField newTexField = new TextField();
                Label nameValueLabel = new Label(mainWindow.getAddValueWindow().getNameValue().getText()+":");
                learnNetwork.getNameInputValues().add(nameValueLabel.getText());
                nameValueLabel.setMinWidth(60);
                HBox textFieldInputHBox= new HBox(10);
                textFieldInputHBox.getChildren().addAll(nameValueLabel,newTexField);
                mainWindow.getVboxForInputTextFields().getChildren().add(textFieldInputHBox);
                mainWindow.update();
                mainWindow.getTextFieldsInput().add(newTexField);
                mainWindow.getTextFieldsNames().add(nameValueLabel);
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
                    data = FXCollections.observableArrayList();
                    if(mainWindow.getTableEnityValues().getColumns().size()==0){
                        for(int i=0; i<mainWindow.getTextFieldsNames().size();i++){
                            mainWindow.getTableEnityValues().getColumns().add(mainWindow.createColumn(i,mainWindow.getTextFieldsNames().get(i).getText()));
                        }
                        mainWindow.getTableEnityValues().getColumns().add(mainWindow.createColumn(mainWindow.getTextFieldsInput().size(),"Выходное значение:"));
                        for(int i=0;i<input.size();i++){
                            if(mainWindow.getTextFieldsInput().get(i).getText().equals("")){
                                data.add(new SimpleStringProperty("0"));
                            }else{
                                data.add(new SimpleStringProperty(mainWindow.getTextFieldsInput().get(i).getText()));
                            }
                        }
                        if(mainWindow.getOutputValue().getText().equals("")){
                        data.add(new SimpleStringProperty("0"));
                        }else{
                            data.add(new SimpleStringProperty(mainWindow.getOutputValue().getText()));
                        }
                        mainWindow.getTableEnityValues().getItems().add(data);
                    }else{
                        for(int i=0;i<input.size();i++){
                            if(mainWindow.getTextFieldsInput().get(i).getText().equals("")){
                                data.add(new SimpleStringProperty("0"));
                            }else{
                                data.add(new SimpleStringProperty(mainWindow.getTextFieldsInput().get(i).getText()));
                            }
                        }
                        if(mainWindow.getOutputValue().getText().equals("")){
                            data.add(new SimpleStringProperty("0"));
                        }else{
                            data.add(new SimpleStringProperty(mainWindow.getOutputValue().getText()));
                        }
                        mainWindow.getTableEnityValues().getItems().add(data);
                    }

                }
            }
        });
        mainWindow.getStartLearning().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*if(mainWindow.getTableEnityValues().getColumns().size()==0){
                System.out.println(0);}
                mainWindow.getTableEnityValues().getColumns().add(mainWindow.createColumn(0,"A"));
                mainWindow.getTableEnityValues().getColumns().add(mainWindow.createColumn(1,"B"));
                mainWindow.getTableEnityValues().getColumns().add(mainWindow.createColumn(2,"C"));
                mainWindow.getTableEnityValues().getColumns().add(mainWindow.createColumn(2,"D"));
                ObservableList<StringProperty> data = FXCollections.observableArrayList();
                data.add(new SimpleStringProperty("a"));
                data.add(new SimpleStringProperty("b"));
                data.add(new SimpleStringProperty("c"));
                data.add(new SimpleStringProperty("d"));
                mainWindow.getTableEnityValues().getItems().add(data);
                /*if(learnNetwork.isLernnetwork()==false) {
                    learnNetwork.buildNerNetwork();
                    learnNetwork.setLernnetwork(true);
                }
                for(int i=0;i<1000000;i++){
                    learnNetwork.startLearningNetwork(learnNetwork.getConvertedInput(),learnNetwork.getConvertedOutput());
                    mainWindow.getProgressLearning().setText(i+" / 1000000");
                }
                mainWindow.getProgressLearning().setText("Нейронная сеть обучена");
                //System.out.println(learnNetwork.getAllNers());
                /*for(int i=0;i<learnNetwork.getAllNers().size();i++){
                    System.out.println(learnNetwork.getAllNers().get(i).getNumberOfNer());
                }*/
            }
        });
        mainWindow.getFindSolution().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Vector<Double> result =learnNetwork.findAltern(learnNetwork.getConvertedInput(),learnNetwork.getConvertedOutput(),
                        Integer.parseInt(mainWindow.getCoeff().getText())/learnNetwork.getDevideNumber());
                Vector<Double> convertedResult=new Vector<Double>();
                for(int i=0; i<result.size();i++){
                    convertedResult.add(result.get(i)*learnNetwork.getDevideNumber());
                }
                for(int i=0;i<convertedResult.size();i++){
                    mainWindow.getResultLabel().setText(mainWindow.getResultLabel().getText()+" "+convertedResult.get(i));
                }
            }
        });

    }

    public ObservableList<StringProperty> getData() {
        return data;
    }

    public void setData(ObservableList<StringProperty> data) {
        this.data = data;
    }

    private void delete(ActionEvent e){

    }

}
