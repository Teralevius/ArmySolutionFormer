package sample.nernetwork;

public class ner {
    private double inputNer = 0;
    private double outputner;
    private int numberOfNer;
    private double err;

    private double activateFunction(double enterNerValue){

        return 1/(1+Math.pow(Math.E,-enterNerValue));

    }

    public void setNumberOfNer(int numberOfNer) {
        this.numberOfNer = numberOfNer;
    }
    public int getNumberOfNer() {
        return numberOfNer;
    }

    public double getInputNer() {
        return inputNer;
    }

    public void setInputNer(double inputNer) {
        this.inputNer = inputNer;
    }

    public double getOutputner() {
        return outputner;
    }

    public void setOutputner() {
        this.outputner = activateFunction(inputNer);
    }

    public double getErr() {
        return err;
    }

    public void setErr(double err) {
        this.err = err;
    }
}
