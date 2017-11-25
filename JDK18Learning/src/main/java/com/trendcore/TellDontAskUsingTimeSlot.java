package com.trendcore;

import java.util.*;

/**
 * Created by Anurag
 */
public class TellDontAskUsingTimeSlot {

    private static class TimeSlot {

        static Random random = new Random();

        public void schedule() {

        }

        public boolean isAvailable() {
            return random.nextBoolean();
            //return false;
        }

    }

    public static void main(String[] args) {
        List<TimeSlot> list = Arrays.asList(new TimeSlot(), new TimeSlot(), new TimeSlot(), new TimeSlot());

        //Imperative
        /*TimeSlot firstTimeSlot = null;
        for(TimeSlot timeSlot : list){
            if(timeSlot.isAvailable()){
                timeSlot.schedule();
                firstTimeSlot = timeSlot;
                break;
            }
        }*/

        /*TimeSlot firstTimeSlot = list.stream()
                .filter(timeSlot -> timeSlot.isAvailable())
                .findFirst()
                .orElse(new TimeSlot());

        System.out.println("TimeSlot is " + firstTimeSlot);*/

        Optional<TimeSlot> first = list.stream()
                .filter(timeSlot -> timeSlot.isAvailable())
                .findFirst();

        System.out.println();
    }

}
