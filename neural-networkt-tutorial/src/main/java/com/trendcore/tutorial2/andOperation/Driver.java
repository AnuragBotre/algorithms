package com.trendcore.tutorial2.andOperation;

import com.trendcore.TableGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Driver {

    TableGenerator tableGenerator = new TableGenerator();

    public static void main(String[] args) {

        int data[][][] = Perceptron.andData;

        double[] weights = Perceptron.INITIAL_WEIGHTS;

        Driver driver = new Driver();

        Perceptron perceptron = new Perceptron();

        int epochNumber = 0;

        boolean errorFlag = true;

        double error = 0;

        while (errorFlag) {
            driver.printHeading(epochNumber);
            errorFlag = false;
            error = 0;

            List<List<String>> rowVectorForPrinting = new ArrayList();

            for (int i = 0; i < data.length; i++) {
                double weightedSum = perceptron.calculateWeightedSum(data[i][0], weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                error = data[i][1][0] - result;

                if (error != 0)
                    errorFlag = true;

                double[] adjustedWeights = perceptron.adjustWeights(data[i][0], weights, error);
                rowVectorForPrinting.add(driver.getVectorRow(data[i], weights, result, error, weightedSum, adjustedWeights));
                weights = adjustedWeights;
            }

            driver.printVector(rowVectorForPrinting);

            epochNumber++;
        }
    }

    private void printVector(List<List<String>> data) {
        String s = tableGenerator.generateTable(Arrays.asList(
                "w1", "w2", "x1", "x2", "Target Result", "Result", "Error", "Weighted Sum", "Adjusted w1", "Adjusted w2"
                ), data
        );

        System.out.println(s);
    }

    private void printHeading(int epochNumber) {
        System.out.println("Epoch # " + epochNumber);
    }

    private List<String> getVectorRow(int[][] data, double[] weights, int result, double error, double weightedSum, double[] adjustWeights) {

        return Arrays.asList(
                weights[0], weights[1], data[0][0], data[0][1], data[1][0], result, error, weightedSum, adjustWeights[0], adjustWeights[1]
        ).stream().map(
                number -> number.toString()
        ).collect(Collectors.toList());


    }

}
