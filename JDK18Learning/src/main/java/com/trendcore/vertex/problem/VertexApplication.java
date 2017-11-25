package com.trendcore.vertex.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anurag
 */
public class VertexApplication {

    private List<List<Integer>> inputArray;

    public VertexApplication(){

    }

    public void initializeArray(){
        inputArray = new ArrayList<>();
        inputArray.add(Arrays.asList(0,0,0,0));
        inputArray.add(Arrays.asList(0,1,1,0));
        inputArray.add(Arrays.asList(0,1,1,0));
        inputArray.add(Arrays.asList(0,0,0,0));
    }

    public void initializeArray(Integer... numbers){
        if(inputArray == null){
            inputArray = new ArrayList<>();
        }
        inputArray.add(Arrays.asList(numbers));
    }

    public void applyRules(VertexCellRules... rules){
        for(int row = 0 ; row < inputArray.size() ; row++)
            for (int column = 0; column < inputArray.get(row).size(); column++) {
                Integer cell = inputArray.get(row).get(column);
                List neighbourList = getNeighbourList(row, column, inputArray.get(row));

                List<Boolean> results = applyRules(rules, cell, neighbourList);

                boolean finalResult = getFinalResult(results);

                setNewState(row, column, finalResult);
            }
    }

    private void setNewState(int row, int column, boolean finalResult) {
        if(finalResult){
            inputArray.get(row).set(column,1);
        }else{
            inputArray.get(row).set(column,0);
        }
    }

    public boolean getFinalResult(List<Boolean> results) {
        boolean prevResult=false;
        int i = 0;
        for(Boolean result : results){
            if(i == 0){
                prevResult = result;
            }
            prevResult = prevResult & result;
            i++;
        }

        return prevResult;
    }

    private List<Boolean> applyRules(VertexCellRules[] rules, Integer cell, List neighbourList) {
        List<Boolean> results = new ArrayList();
        for(VertexCellRules rule : rules){
            results.add(rule.apply(cell,neighbourList));
        }
        return results;
    }

    private List getNeighbourList(int row, int column, List<Integer> currentRow) {
        List list = new ArrayList();
        if(row - 1 >= 0){
            List<Integer> previousRow = inputArray.get(row-1);
            list.addAll(getNeighboursFromRow(previousRow,column));
        }

        list.addAll(getNeighboursFromRow(currentRow,column,true));

        if(row+1 < inputArray.size()){
            List<Integer> nextRow = inputArray.get(row+1);
            list.addAll(getNeighboursFromRow(nextRow,column));
        }

        return list;
    }

    private List<Integer> getNeighboursFromRow(List<Integer> rowList, int column, boolean skipCurrentColumn) {

        List list = new ArrayList();
        if(column - 1 >= 0){
            list.add(rowList.get(column));
        }

        if(!skipCurrentColumn)
            list.add(rowList.get(column));

        if(column + 1 < rowList.size()){
            list.add(rowList.get(column+1));
        }

        return list;
    }

    private List<Integer> getNeighboursFromRow(List<Integer> rowList, int column) {
        return getNeighboursFromRow(rowList,column,false);
    }


    public void printResult() {
        for(List<Integer> arr : inputArray){
            for(Integer integer : arr){
                System.out.print(integer + " " );
            }
            System.out.println();
        }
    }
}
