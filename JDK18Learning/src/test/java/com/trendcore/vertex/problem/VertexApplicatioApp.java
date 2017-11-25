package com.trendcore.vertex.problem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag
 */
public class VertexApplicatioApp {


    @Test
    public void testRule() throws Exception {
        VertexApplication v = new VertexApplication();
        v.initializeArray(0,0,0);
        v.initializeArray(0,1,0);
        /*v.initializeArray(0,0,0);*/

        VertexCellRules underFlowRule = new VertexCellRules() {
            @Override
            public boolean apply(Integer cell, List<Integer> neighbourList) {
                int count = 0;
                if(cell == 1){
                    for (Integer neighbours:neighbourList) {
                        if(neighbours == 1){
                            count++;
                        }
                    }
                }
                return count == 2;
            }
        };

        VertexCellRules overpopulationRule = new VertexCellRules() {
            @Override
            public boolean apply(Integer cell, List<Integer> neighbourList) {
                int count = 0;
                if(cell == 1){
                    for (Integer neighbours:neighbourList) {
                        if(neighbours == 1){
                            count++;
                        }
                    }
                }
                return count == 3;
            }
        };

        v.applyRules(underFlowRule,overpopulationRule);

        v.printResult();
    }


    @Test
    public void evaluateBoolean() throws Exception {

        VertexApplication v = new VertexApplication();

        List<Boolean> list = new ArrayList<>();

        list.add(true);
        list.add(true);
        list.add(true);

        System.out.println(v.getFinalResult(list));

    }
}
