package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem of my own creation.
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        LongestCommonSubstring l = new LongestCommonSubstring();
        System.out.println(l.longestCommonSubstring(new String[]{"zaab", "baa", "ddaa"}));
        System.out.println(l.longestCommonSubstring(new String[]{"zaaab", "baadaaa", "ddaayaaa"}));
        //This input is breaking the prog.
        System.out.println(l.longestCommonSubstring(new String[]{"zaaab", "baadaba", "ddaayaca"}));
    }

    public String longestCommonSubstring(String[] strs) {
        //process input arrays
        int minLength = 0;

        List list = new ArrayList();


        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }

            Map map = new HashMap();
            for (int j = 0; j < strs[i].length(); j++) {
                if (map.get(strs[i].charAt(j)) != null) {
                    List list1 = (List) map.get(strs[i].charAt(j));
                    list1.add(j);
                } else {
                    List list1 = new ArrayList();
                    list1.add(j);
                    map.put(strs[i].charAt(j), list1);
                }
            }

            list.add(map);
        }

        String result = "";

        String maxSeq = "";

        for (int i = 0; i < strs[minLength].length(); i++) {
            char c = strs[minLength].charAt(i);

            //starting with this characters position
            //i.e i and c
            if (otherStringContainsCharacter(list, minLength, c)) {

                result = "" + c;
                //now we got character which is common in all string
                //find next seq from this position
                int offset = 1;
                for (int j = i + 1; j < strs[minLength].length(); j++) {

                    char nextChar = strs[minLength].charAt(j);

                    boolean charContainsInAllList = true;

                    for (int k = 0; k < list.size(); k++) {
                        if (k != minLength) {
                            Map map = (Map) list.get(k);
                            List<Integer> positionList = (List) map.get(c);

                            //from all positions check whether at least one of the position forms a sequence.
                            boolean matches = false;
                            for (Integer pos : positionList) {
                                /*if(((int)pos)+offset < strs[k].length() && strs[k].charAt(((int)pos)+offset) == nextChar){
                                    matches = true;
                                }else{

                                }*/
                                for (int f = 0; f < result.length(); f++) {
                                    if (pos + offset < strs[k].length() && strs[k].charAt(pos + offset) == result.charAt(f)) {
                                        //then store this position somewhere
                                    } else {


                                    }
                                }

                            }

                            if (!matches) {
                                charContainsInAllList = false;
                                break;
                            }
                        }
                    }
                    offset++;

                    if (!charContainsInAllList) {
                        break;
                    }
                    result = result + nextChar;
                }

            } else {
                result = "";
            }

            if (result.length() > maxSeq.length()) {
                maxSeq = result;
            }
        }

        return maxSeq;
    }

    private boolean otherStringContainsCharacter(List list, int toBeSkipped, char c) {
        for (int i = 0; i < list.size(); i++) {
            if (i != toBeSkipped) {
                Map map = (Map) list.get(i);
                if (!map.containsKey(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}