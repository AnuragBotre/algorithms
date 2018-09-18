package com.trendcore.tutorial;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    /**
     * Neuron's identifier
     */
    private String id;
    /**
     * Collection of neuron's input connections (connections to this neuron)
     */
    protected List<Connection> inputConnections;
    /**
     * Collection of neuron's output connections (connections from this to other
     * neurons)
     */
    protected List<Connection> outputConnections;
    /**
     * Input summing function for this neuron
     */
    protected InputSummingFunction inputSummingFunction;
    /**
     * Activation function for this neuron
     */
    protected ActivationFunction activationFunction;

    /**
     * Default constructor
     */
    public Neuron() {
        this.inputConnections = new ArrayList<>();
        this.outputConnections = new ArrayList<>();
    }

    /**
     * Calculates the neuron's output
     */
    public double calculateOutput() {
        double totalInput = inputSummingFunction.getOutput(inputConnections);
        return activationFunction.getOutput(totalInput);
    }

}