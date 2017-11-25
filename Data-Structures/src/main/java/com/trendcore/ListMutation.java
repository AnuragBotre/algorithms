package com.trendcore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Anurag
 */
public class ListMutation {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList(Arrays.asList(-5,0,-1,12,-2,5,-5));

        int i = 0;
        while(i < list.size()){
            if((Integer)list.get(i) < 0){
                list.remove(i);
            }else{
                i++;
            }
        }

        System.out.println(list);


    }

}
