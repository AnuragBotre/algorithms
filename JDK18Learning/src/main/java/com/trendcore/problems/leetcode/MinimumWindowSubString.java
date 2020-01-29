package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * 76. Minimum Window Substring
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubString {

    public String minWindow(String s, String t) {

        String window = "";


        /*
        one pointer will start from left
        other pointer will start from right

        ADOBECODEBANC
        |  |        |

        ADOBECODEBANC


        ADOBECODEBANC
                 |  |

        BANC


        1 pointer pointing to start
        and other to end
         ADOBECODEBANC
         |           |
         abc
            |
         */

        /*
        pointer1 = 0;
        pointer2 = s.length() - 1;
        visited = list of visited characters with their position

        original char list which will indicate boolean if node is visited or not

        if (t is not empty){
            Char c = t.contains(s.charAt(i));
            if (c != null && c.visited) {
                if (pointer1 not initialized)
                pointer1 = i;
                t = t.remove(s.charAt(i));
                visited.add(t);
            } else {
                //move pointer1 to next pos in visited
                if (!visited.empty) {
                    pointer1 = visited.get(0);
                }
            }
        }else{
            //means all char visited
            tempMin = visited.get(last) - visited.get(0);
            if (min is not initialized){
                min = tempMin;
            } else {
                if(min > tempMin){
                    min = tempMin;
                }
            }
        }
        */

        int pointer1 = 0;
        boolean pointer1Initialized = false;
        int pointer2 = s.length() - 1;

        List<OriginalChar> originalCharList = new ArrayList<>();
        List<VisitedChar> visitedCharList = new ArrayList<>();

        for (int i = 0; i < t.length(); i++) {
            addCharacter(originalCharList, t.charAt(i));
        }

        String minWindowSubString = "";
        int i = 0;

        for (i = 0; i < s.length(); i++) {
            if (originalCharListIsEmpty(originalCharList)) {
                Optional<OriginalChar> originalChar = originalCharListContains(originalCharList, s.charAt(i));
                if (originalChar.isPresent()) {
                    if ((originalChar.get().count > 0)) {
                        if (!pointer1Initialized) {
                            pointer1 = i;
                            pointer1Initialized = true;
                        }
                        OriginalChar originalChar1 = originalChar.get();
                        originalChar1.count--;
                        addInVisitedCharList(visitedCharList, i, originalChar1.c);
                    } else {

                        if (!visitedCharList.isEmpty()) {
                            VisitedChar remove = visitedCharList.remove(0);
                            //
                            //markCharNotVisitedInOriginalCharList(originalCharList, remove.c);
                            remove.pos = i;
                            visitedCharList.add(remove);
                        }

                    }
                }
            } else {
                VisitedChar p2 = visitedCharList.get(visitedCharList.size() - 1);
                VisitedChar p1 = visitedCharList.get(0);
                String substring;
                if (p2.pos == s.length()) {
                    substring = s.substring(p1.pos, p2.pos);
                } else {
                    substring = s.substring(p1.pos, p2.pos+1);
                }

                if (minWindowSubString.length() == 0) {
                    minWindowSubString = substring;
                } else {
                    if (substring.length() < minWindowSubString.length()) {
                        minWindowSubString = substring;
                    }
                }

                //need to do more things
                if (!visitedCharList.isEmpty()) {
                    VisitedChar remove = visitedCharList.remove(0);
                    //
                    markCharNotVisitedInOriginalCharList(originalCharList, remove.c);
                }
            }
        }

        //check if min windows still needs to calculated
        //if (visitedCharList.size() == originalCharList.size()) {

        if (visitedCharList.size() != t.length()) {
            Optional<OriginalChar> originalChar = originalCharListContains(originalCharList, s.charAt(i - 1));
            if (originalChar.isPresent()) {
                if ((originalChar.get().count > 0)) {
                    if (!pointer1Initialized) {
                        pointer1 = i;
                        pointer1Initialized = true;
                    }
                    OriginalChar originalChar1 = originalChar.get();
                    originalChar1.count--;
                    addInVisitedCharList(visitedCharList, i, originalChar1.c);
                }
            }
        }

        if (visitedCharList.size() == t.length()) {
            VisitedChar p2 = visitedCharList.get(visitedCharList.size() - 1);
            VisitedChar p1 = visitedCharList.get(0);
            String substring;
            if (p2.pos == s.length()) {
                substring = s.substring(p1.pos, p2.pos);
            } else {
                substring = s.substring(p1.pos, p2.pos+1);
            }

            if (minWindowSubString.length() == 0) {
                minWindowSubString = substring;
            } else {
                if (substring.length() < minWindowSubString.length()) {
                    minWindowSubString = substring;
                }
            }
        }

        return minWindowSubString;
    }

    private void addCharacter(List<OriginalChar> originalCharList, char originalChar) {
        Optional<OriginalChar> first = originalCharList.stream().filter(originalChar1 -> originalChar1.c == originalChar).findFirst();
        if (first.isPresent()) {
            first.get().count++;
        } else {
            originalCharList.add(new OriginalChar(originalChar));
        }
    }

    private void markCharNotVisitedInOriginalCharList(List<OriginalChar> originalCharList, char c) {
        Optional<OriginalChar> first = originalCharList.stream().filter(originalChar -> originalChar.c == c).findFirst();
        if (first.isPresent()) {
            OriginalChar originalChar = first.get();
            originalChar.count++;
        }
    }

    private void addInVisitedCharList(List<VisitedChar> visitedCharList, int i, char originalChar) {
        VisitedChar visitedChar = new VisitedChar(originalChar, i);
        visitedCharList.add(visitedChar);
    }

    private Optional<OriginalChar> originalCharListContains(List<OriginalChar> originalCharList, char charAt) {
        return originalCharList.stream()
                .filter(originalChar -> originalChar.c == charAt)
                .findFirst();
    }

    private boolean originalCharListIsEmpty(List<OriginalChar> originalCharList) {
        return originalCharList.stream().filter(originalChar -> (originalChar.count > 0)).findAny().isPresent();
    }

    class OriginalChar {
        char c;
        int count;

        public OriginalChar(char c) {
            this.c = c;
            count = 1;
        }
    }

    class VisitedChar {
        char c;
        int pos;

        public VisitedChar(char originalChar, int i) {
            this.c = originalChar;
            this.pos = i;
        }
    }

}
