package sample.nernetwork;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ArtificialNeuralNetworks {
    private Double neuralNetworkWeights[][];
    private ArrayList<Vector<Integer>> inputValues;
    private ArrayList<Integer> outputValues;
    private ArrayList<Vector<Double>> convertedInput;
    private ArrayList<Double> convertedOutput;
    private Vector input;
    private ner ner;
    private ArrayList<ner> allNers;
    private Random randDouble;
    private static double minDouble = 0;
    private static double maxDouble = 1;
    private double devideNumber=0;
    private double normLearning = 0.7;

    public ArtificialNeuralNetworks(){
        inputValues = new ArrayList<Vector<Integer>>();
        outputValues = new ArrayList<Integer>();
        allNers = new ArrayList<ner>();
        convertedInput = new ArrayList<Vector<Double>>();
        convertedOutput = new ArrayList<Double>();

    }
    public double generateRandomDouble(){
        randDouble = new Random();
        return minDouble + (maxDouble - minDouble) * randDouble.nextDouble();
    }
    public void buildNerNetwork(){
        for(int i=0;i<inputValues.get(0).size()+2;i++){
            ner = new ner();
            ner.setNumberOfNer(inputValues.get(0).size()+i);
            allNers.add(ner);
        }

        fillWeightMatrix(2*inputValues.get(0).size()+2);
        fillRandValues();
        System.out.println(inputValues);
        convertNumbers();
        System.out.println(convertedInput);
        System.out.println(convertedOutput);
        //System.out.println(convertNumbers());
        startLearningNetwork(convertedInput,convertedOutput,1000000);
        for(int j = 0; j<2*inputValues.get(0).size()+2;j++){
            System.out.println("");
            for(int k=0;k<2*inputValues.get(0).size()+2;k++){
                System.out.print(" "+neuralNetworkWeights[j][k]);
            }
        }
        //System.out.println(inputValues.get(0).size()+outputValues.get(0).size()+allNers.size());

        //fillWeightMatrix();
        //fillRandValues();


    }
    private double convertNumbers(){
        int maxcurrent=0;
        String curString= new String();
        for(int i=0;i<inputValues.size();i++){
            for(int j=0; j<inputValues.get(0).size();j++){
                if(maxcurrent<inputValues.get(i).get(j)){
                   maxcurrent=inputValues.get(i).get(j);
                }
            }
        }
        for (int i=0; i<outputValues.size();i++){
            if(maxcurrent<outputValues.get(i)){
                maxcurrent = outputValues.get(i);
            }
        }

        curString=Integer.toString(maxcurrent);
        devideNumber=Math.pow(10,curString.length());
        for(int i=0;i<inputValues.size();i++){
            Vector<Double> preConverted = new Vector<Double>();
            for(int j=0;j<inputValues.get(0).size();j++){
                preConverted.add(inputValues.get(i).get(j)/devideNumber);
            }
            convertedInput.add(preConverted);
        }

        for(int i=0;i<outputValues.size();i++){
            convertedOutput.add(outputValues.get(i)/devideNumber);
        }
        return devideNumber;
    }
    private void startLearningNetwork(ArrayList<Vector<Double>> input,ArrayList<Double> convetedOutput,int iteration){
        for(int i=0;i<iteration;i++){
            for(int j=0;j<input.size();j++){
                calcNerNetwork(input.get(j),convetedOutput.get(j));
            }
        }
    }
    private void calcNerNetwork(Vector<Double>input, double output){
        calcOutputAllNers(input);
        calcErr(output);
        calcWeightCorrection(input);

    }

    private double calcOutputAllNers(Vector<Double> input){
        double allInputSignals;
        for(int i=0;i<allNers.size()-1;i++){
            allInputSignals = 0.0;
            for(int j=0;j<input.size();j++){
                //System.out.println(neuralNetworkWeights[j][allNers.get(i).getNumberOfNer()]);
                allInputSignals+=neuralNetworkWeights[j][allNers.get(i).getNumberOfNer()]*input.get(j);
            }
            allNers.get(i).setInputNer(allInputSignals);
        }
        for(int i=0;i<allNers.size()-1;i++){
            allNers.get(i).setOutputner();
        }
        allInputSignals =0.0;
        for(int i=0;i<allNers.size()-1;i++){
            allInputSignals+=allNers.get(i).getOutputner()*neuralNetworkWeights[allNers.get(i).getNumberOfNer()][allNers.get(allNers.size()-1).getNumberOfNer()];
        }

        allNers.get(allNers.size()-1).setInputNer(allInputSignals);
        allNers.get(allNers.size()-1).setOutputner();
        System.out.println(allNers.get(allNers.size()-1).getOutputner());
        return allNers.get(allNers.size()-1).getOutputner();
    }

    private void calcErr(double output){
        allNers.get(allNers.size()-1).setErr((output-allNers.get(allNers.size()-1).getOutputner())*allNers.get(allNers.size()-1).getOutputner()*(1-allNers.get(allNers.size()-1).getOutputner()));
        for(int i=0; i <allNers.size()-1;i++){
            //System.out.println(allNers.get(allNers.size()-1).getErr() * neuralNetworkWeights[allNers.get(i).getNumberOfNer()][allNers.get(allNers.size()-1).getNumberOfNer()] *
                    //allNers.get(i).getOutputner() *(1-allNers.get(i).getOutputner()));
            allNers.get(i).setErr(allNers.get(allNers.size()-1).getErr() * neuralNetworkWeights[allNers.get(i).getNumberOfNer()][allNers.get(allNers.size()-1).getNumberOfNer()] *
                    allNers.get(i).getOutputner() *(1-allNers.get(i).getOutputner()));
        }
    }
    private void calcWeightCorrection(Vector<Double> input){

        for(int j=0; j<input.size();j++){
            for(int i = 0; i<allNers.size()-1;i++){
                //System.out.println( neuralNetworkWeights[j][allNers.get(i).getNumberOfNer()]);
                neuralNetworkWeights[j][allNers.get(i).getNumberOfNer()]+=input.get(j)*normLearning*allNers.get(i).getErr();
                //System.out.println( neuralNetworkWeights[j][allNers.get(i).getNumberOfNer()]);
            }
        }
        for(int i =0;i<allNers.size()-1;i++){
            neuralNetworkWeights[allNers.get(i).getNumberOfNer()][allNers.get(allNers.size()-1).getNumberOfNer()]+=allNers.get(i).getOutputner()*normLearning*allNers.get(allNers.size()-1).getErr();
        }

    }

    private void fillRandValues() {
        for(int i=0;i<inputValues.get(0).size();i++){
            for(int j =0;j<allNers.size()-1;j++){
                neuralNetworkWeights[i][allNers.get(j).getNumberOfNer()] = generateRandomDouble();
            }
        }
        for(int i =0; i<allNers.size()-1;i++){
            neuralNetworkWeights[allNers.get(i).getNumberOfNer()][allNers.get(allNers.size()-1).getNumberOfNer()]=generateRandomDouble();
        }

        for(int j = 0; j<2*inputValues.get(0).size()+2;j++){
            System.out.println("");
            for(int k=0;k<2*inputValues.get(0).size()+2;k++){
                System.out.print(" "+neuralNetworkWeights[j][k]);
            }
        }
    }
    private void fillWeightMatrix(int i){
        neuralNetworkWeights = new Double[i][i];
        for(int j = 0; j<i;j++){
            System.out.println("");
            for(int k=0;k<i;k++){
                neuralNetworkWeights[j][k]=0.0;
                System.out.print(" "+neuralNetworkWeights[j][k]);
            }
        }

    }
    public Vector getInput() {
        return input;
    }

    public void addInput(Vector input) {
        inputValues.add(input);
    }

    public void addOutput (int i){
        outputValues.add(i);
    }


    public ArrayList<Vector<Integer>> getInputValues() {
        return inputValues;
    }

    public void setInputValues(ArrayList<Vector<Integer>> inputValues) {
        this.inputValues = inputValues;
    }

    public ArrayList<Integer> getOutputValues() {
        return outputValues;
    }

    public void setOutputValues(ArrayList<Integer> outputValues) {
        this.outputValues = outputValues;
    }

    public ArrayList<sample.nernetwork.ner> getAllNers() {
        return allNers;
    }

    public void setAllNers(ArrayList<sample.nernetwork.ner> allNers) {
        this.allNers = allNers;
    }

    public Double[][] getNeuralNetworkWeights() {
        return neuralNetworkWeights;
    }

    public void setNeuralNetworkWeights(Double[][] neuralNetworkWeights) {
        this.neuralNetworkWeights = neuralNetworkWeights;
    }


}
