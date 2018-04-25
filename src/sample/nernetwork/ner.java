package sample.nernetwork;

public class ner {
    private double inputNer;
    private double outputner;
    private int numberOfLayer;

    public int getNumberOfLayer() {
        return numberOfLayer;
    }

    private double activateFunction(double enterNerValue){

        return 1/(1+Math.pow(Math.E,-enterNerValue));

    }

    public void setNumberOfLayer(int numberOfLayer) {
        this.numberOfLayer = numberOfLayer;
    }

    public double getInputNer() {
        return inputNer;
    }

    private void setInputNer(double inputNer) {
        this.inputNer = inputNer;
    }

    private double getOutputner() {
        return outputner;
    }

    private void setOutputner(double outputner) {
        this.outputner = outputner;
    }
}
