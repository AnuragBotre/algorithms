package com.trendcore.problems.leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/regular-expression-matching/description/
 * 10. Regular Expression Matching
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * <p>
 * <p>
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * <p>
 * <p>
 * <p>
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        System.out.println(r.formatResult("aaa", "a"));
        System.out.println(r.formatResult("aaa", "aa."));
        System.out.println(r.formatResult("aaa", "a*"));
        System.out.println(r.formatResult("aaab", "a*b"));
        System.out.println(r.formatResult("aaab", ".*b"));

        System.out.println(r.formatResult("mississippi", "mis*is*p*."));

        System.out.println(r.formatResult("aab", "c*a*b*"));
        System.out.println(r.formatResult("aab", "c*a*bb"));

        System.out.println(r.formatResult("a", "a*"));
        System.out.println(r.formatResult("a", "aa*"));
        System.out.println(r.formatResult("aaa", "a*a*a*"));
        System.out.println(r.formatResult("aaa", "c*d*a*"));
        System.out.println(r.formatResult("aa", "a"));
        System.out.println(r.formatResult("", "bab"));
        System.out.println(r.formatResult("mississippi", "mis*is*ip*."));
        System.out.println(r.formatResult("aaa", "ab*a"));


        System.out.println(r.formatResult("a", "ab*a"));
        System.out.println(r.formatResult("aa", "ab*a"));
        System.out.println(r.formatResult("", "."));
        System.out.println(r.formatResult("a", ".*..a*"));
        System.out.println(r.formatResult("ab", ".*.."));
        System.out.println(r.formatResult("aaa", "ab*a*c*a"));
        System.out.println(r.formatResult("ab", ".*c"));
        System.out.println(r.formatResult("abbbcd", "ab*bbbcd"));
        System.out.println(r.formatResult("abbbcd", "ab*cd"));

        //not working for this input
        System.out.println(r.formatResult("bbba", ".*b"));


        System.out.println(r.formatResult("a", "c*a*a"));
        System.out.println(r.formatResult("a", "c*.*a"));
        System.out.println(r.formatResult("a", "c*.*a*"));

        System.out.println(r.formatResult("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));

        System.out.println(r.formatResult("afafafafas", "af.*af.*af.*af.*s"));
    }

    public String formatResult(String s, String p) {
        return s + " " + p + " " + isMatch(s, p);
    }

    class Struct {
        char c;
        boolean oneOrMoreOccurance;
    }

    public boolean isMatch(String s, String p) {


        return approach4(s, p);
    }

    private boolean approach4(String s, String p) {
        int patternPointer = 0;
        int stringPointer = 0;

        String resultString = "";

        Object[] objs = getStructs(p);
        Struct structs[] = (Struct[]) objs[0];
        int remaininglegthOfPatternWithoutZeroOrMoreOccurance = (int) objs[1];

        boolean flag = true;
        int pointOfStrictMatch = 0;

        for (; flag; ) {

            if (stringPointer >= s.length() && patternPointer >= structs.length) {
                return true;
            } else if (stringPointer >= s.length() && patternPointer < structs.length) {
                //need to take care of this part
                for (int k = patternPointer; k < structs.length; k++) {
                    if (!structs[k].oneOrMoreOccurance) {
                        return false;
                    }
                }

                return true;

            } else if (stringPointer < s.length() && patternPointer >= structs.length) {
                return false;
            }

            if (isCharacterEqual(s, stringPointer, structs, patternPointer) && !structs[patternPointer].oneOrMoreOccurance) {
                resultString = resultString + s.charAt(stringPointer);
                stringPointer++;
                pointOfStrictMatch++;
                patternPointer++;
                remaininglegthOfPatternWithoutZeroOrMoreOccurance--;
                continue;
            }

            if (isCharacterEqual(s, stringPointer, structs, patternPointer)) {
                if (remaininglegthOfPatternWithoutZeroOrMoreOccurance < s.length() - stringPointer) {

                    //in case of . we may have to move the string pointer
                    //because .* have consumed all the characters

                    resultString = resultString + s.charAt(stringPointer);
                    stringPointer++;
                    continue;
                } else {
                    patternPointer++;
                    continue;
                }
            }

            if (!isCharacterEqual(s, stringPointer, structs, patternPointer)) {

                //check previous pattern was .*
                // and can be removed
                if (patternPointer - 1 >= 0 && structs[patternPointer - 1].c == '*' && structs[patternPointer - 1].oneOrMoreOccurance) {
                    stringPointer--;
                } else {
                    patternPointer++;
                }
                /*if(resultString.length() >= pointOfStrictMatch){

                    if(resultString.length() - 1 >= 0 && patternPointer + 1 < structs.length) {
                        if (resultString.charAt(resultString.length() - 1) == structs[patternPointer + 1].c) {
                            resultString = resultString.substring(0, resultString.length() - 1);
                            patternPointer++;
                            stringPointer--;
                        } else {
                            patternPointer++;
                        }
                    }else{
                        patternPointer++;
                    }
                }else{

                }*/

            }
        }

        return true;
    }

    private boolean isCharacterEqual(String s, int stringPointer, Struct structs[], int patternPointer) {
        return s.charAt(stringPointer) == structs[patternPointer].c || structs[patternPointer].c == '.';
    }

    private boolean approach3(String s, String p) {
        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        Struct[] s1 = (Struct[]) getStructs(p)[0];

        boolean process = false;

        Map<Character, Integer> noOfCharacterInserted = new LinkedHashMap<>();

        for (; flag; ) {

            if (stringPointer == s.length() && patternPointer == s1.length) {
                return true;
            } else if (stringPointer < s.length() && patternPointer == s1.length) {
                return false;
            } else if (stringPointer == s.length() && patternPointer < s1.length) {
                //check if remaining charaters are *
                for (int k = patternPointer; k < s1.length; k++) {
                    if (!s1[k].oneOrMoreOccurance) {

                        //what needs to do with these characters
                        //here we can use those counters which are added by *

                        //s1[k].c == '.'
                        //then one logic

                        //s[k].c == other char then need to process in other way

                        if (s1[k].c == '.') {
                            //this is tricky case
                            Iterator<Character> iterator = noOfCharacterInserted.keySet().iterator();
                            if (iterator.hasNext()) {
                                Character next = iterator.next();
                                Integer cnt = noOfCharacterInserted.get(next);
                                if (cnt != null && cnt > 0) {
                                    cnt--;
                                    if (cnt == 0) {
                                        noOfCharacterInserted.remove(next);
                                    } else {
                                        noOfCharacterInserted.put(next, cnt);
                                    }

                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            Integer cnt = noOfCharacterInserted.get(s1[k].c);
                            if (cnt != null && cnt > 0) {
                                cnt--;

                                if (cnt == 0) {
                                    noOfCharacterInserted.remove(s1[k].c);
                                } else {
                                    noOfCharacterInserted.put(s1[k].c, cnt);
                                }
                            } else {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }

            if (s1[patternPointer].c == s.charAt(stringPointer) || s1[patternPointer].c == '.') {
                if (!s1[patternPointer].oneOrMoreOccurance) {
                    patternPointer++;
                } else {
                    Integer cnt = noOfCharacterInserted.get(s.charAt(stringPointer));
                    if (cnt != null) {
                        cnt++;
                        noOfCharacterInserted.put(s.charAt(stringPointer), cnt);
                    } else {
                        noOfCharacterInserted.put(s.charAt(stringPointer), 1);
                    }
                }
                stringPointer++;
            } else {
                if (!s1[patternPointer].oneOrMoreOccurance) {

                    if (s1[patternPointer].c == '.') {
                        //this is tricky case
                        Iterator<Character> iterator = noOfCharacterInserted.keySet().iterator();
                        if (iterator.hasNext()) {
                            Character next = iterator.next();
                            Integer cnt = noOfCharacterInserted.get(next);
                            if (cnt != null && cnt > 0) {
                                cnt--;
                                if (cnt == 0) {
                                    noOfCharacterInserted.remove(next);
                                } else {
                                    noOfCharacterInserted.put(next, cnt);
                                }

                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        Integer cnt = noOfCharacterInserted.get(s1[patternPointer].c);
                        if (cnt != null && cnt > 0) {
                            cnt--;

                            if (cnt == 0) {
                                noOfCharacterInserted.remove(s1[patternPointer].c);
                            } else {
                                noOfCharacterInserted.put(s1[patternPointer].c, cnt);
                            }
                        } else {
                            return false;
                        }
                    }
                    patternPointer++;
                } else {
                    patternPointer++;
                }
            }

        }

        return true;
    }

    private boolean approach2(String s, int stringPointer, int patternPointer, boolean flag, Struct[] s1, boolean process) {
        for (; flag; ) {


            if (remainingLengthOfPattern(s1, patternPointer) > remainingLengthOfString(s, stringPointer) && !process) {
                //we might have to skip certain tokens

                if (s1[patternPointer].oneOrMoreOccurance) {
                    patternPointer++;
                } else {
                    process = true;
                }

            } else {

                if (s1.length == patternPointer && s.length() == stringPointer) {
                    return true;
                } else if (s.length() == stringPointer) {
                    //remaing chars are zero or more seq then return true or return false
                    for (int k = patternPointer; k < s1.length; k++) {
                        if (!s1[k].oneOrMoreOccurance) {
                            return false;
                        }
                    }
                    return true;
                } else if (s1.length == patternPointer) {
                    return false;
                }

                if (s1[patternPointer].c == s.charAt(stringPointer) || s1[patternPointer].c == '.') {
                    stringPointer++;
                    if (!s1[patternPointer].oneOrMoreOccurance) {
                        patternPointer++;
                        process = false;
                    }
                } else {
                    patternPointer++;
                    process = false;
                }
            }


        }

        return true;
    }

    private Object[] getStructs(String p) {
        Struct s2[] = new Struct[p.length()];
        int s1Pointer = 0;

        int lengthOfPattern = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                s2[s1Pointer - 1].oneOrMoreOccurance = true;
                lengthOfPattern--;
            } else {
                s2[s1Pointer] = new Struct();
                s2[s1Pointer].c = p.charAt(i);
                s1Pointer++;
                lengthOfPattern++;
            }
        }

        Struct s1[] = new Struct[s1Pointer];
        System.arraycopy(s2, 0, s1, 0, s1Pointer);
        return new Object[]{s1, lengthOfPattern};
    }

    private int remainingLengthOfString(String s, int stringPointer) {
        return s.length() - stringPointer + 1;
    }

    private int remainingLengthOfPattern(Struct[] p, int patternPointer) {
        return p.length - patternPointer + 1;
    }

    private boolean isCharacterEqual(String s, String p, int stringPointer, int patternPointer) {
        return s.charAt(stringPointer) == p.charAt(patternPointer) || p.charAt(patternPointer) == '.';
    }

    private boolean modifiedApproach1(String s, String p) {
        //This strategy is not working
        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        //In this Approach need to remember position of the the last processed character
        //If it  is * or *-1 char then do it accordingly

        int lastCharProcessedPosition = 0;

        for (; flag; ) {

            if (stringPointer == s.length()) {

                if (patternPointer == p.length()) {
                    return true;
                } else {
                    for (int k = patternPointer; k < p.length(); k++) {
                        if (p.charAt(k) == '*') {
                            continue;
                        }

                        if (k + 1 < p.length() && p.charAt(k + 1) == '*') {
                            continue;
                        }

                        if (s.length() - 1 >= 0 && p.charAt(k) == s.charAt(s.length() - 1) || p.charAt(k) == '.') {
                            //if that char was processed using * then we can remove
                            if (lastCharProcessedPosition + 1 < p.length() && p.charAt(lastCharProcessedPosition + 1) == '*') {
                                lastCharProcessedPosition = lastCharProcessedPosition + 1;
                                continue;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }

                return true;
            } else if (patternPointer == p.length()) {

                return false;
            }

            if (s.charAt(stringPointer) == p.charAt(patternPointer) && (p.charAt(patternPointer) != '.' || p.charAt(patternPointer) != '*')) {
                lastCharProcessedPosition = patternPointer;
                patternPointer++;
            } else if (p.charAt(patternPointer) == '.') {
                lastCharProcessedPosition = patternPointer;
                patternPointer++;
            } else if (p.charAt(patternPointer) == '*') {
                if (patternPointer - 1 >= 0) {
                    char prevChar = p.charAt(patternPointer - 1);
                    if (prevChar != '.' && prevChar != s.charAt(stringPointer)) {
                        stringPointer--;
                        patternPointer++;
                    }
                }
            } else {
                //when nothing matched then next char should be *
                //return false;
                if (patternPointer + 1 < p.length()) {
                    if (p.charAt(patternPointer + 1) == '*') {
                        patternPointer = patternPointer + 2;
                        stringPointer--;
                    } else {

                    }
                } else {
                    return false;
                }

            }
            stringPointer++;
        }
        return true;
    }

    private boolean approach1(String s, String p) {
        int stringPointer = 0;
        int patternPointer = 0;

        boolean flag = true;

        char prevChar = 0;

        for (; flag; ) {

            if ((stringPointer == s.length() && patternPointer == p.length()) || (stringPointer == s.length() && patternPointer == p.length() - 1)) {
                return true;
            } else if (stringPointer < s.length() && patternPointer == p.length()) {
                return false;
            } else if (stringPointer == s.length()) {

                //We are at the end of string and pattern length is not finished yet.
                //hence analyzing pattern length

                for (int c = patternPointer + 1; c < p.length(); c++) {
                    if (p.charAt(c) != '*') {
                        return false;
                    }
                }

                return true;
                /*}else{
                    return false;
                }*/
            }

            if (s.charAt(stringPointer) == p.charAt(patternPointer) && (p.charAt(patternPointer) != '.' || p.charAt(patternPointer) != '*')) {
                patternPointer++;
            } else if (p.charAt(patternPointer) == '.') {
                patternPointer++;
            } else if (p.charAt(patternPointer) == '*') {
                if (patternPointer - 1 >= 0) {
                    prevChar = p.charAt(patternPointer - 1);
                    if (prevChar != '.' && prevChar != s.charAt(stringPointer)) {
                        //stringPointer--;
                        patternPointer++;
                    }
                }
            } else {
                if (patternPointer + 1 < p.length()) {
                    if (p.charAt(patternPointer + 1) == '*') {
                        patternPointer = patternPointer + 2;
                    }
                } else {
                    return false;
                }

            }


            stringPointer++;
        }
        return true;
    }

    private boolean validate(String value, String pattern) {
        if (value == null || pattern == null) {
            return false;
        }
        if ((value.isEmpty() && !pattern.isEmpty()) || (!value.isEmpty() && pattern.isEmpty())) {
            return false;
        }
        if (value.isEmpty() && pattern.isEmpty()) {
            return true;
        }

        int valCharIndex = 0;
        int patternCharIndex = 0;
        char patternCharStar = '*', patternCharDot = '.';
        boolean result = true;
        char valChar, patterChar, prevChar = '*';
        while (valCharIndex < value.length() && patternCharIndex < pattern.length() && result) {
            valChar = value.charAt(valCharIndex);
            patterChar = pattern.charAt(patternCharIndex);
            if (patterChar == patternCharDot) {
                prevChar = patternCharDot;
                patternCharIndex++;
                valCharIndex++;
                continue;
            }
            if (patterChar == valChar) {
                prevChar = valChar;
                patternCharIndex++;
                valCharIndex++;
                continue;
            }
            if (patterChar == patternCharStar && (valChar == prevChar || prevChar == patternCharDot)) {
                prevChar = valChar;
                valCharIndex++;
                continue;
            }
            if (patterChar == patternCharStar && valChar != prevChar) {
                patternCharIndex++;
                continue;
            }
            result = false;
        }
        if (valCharIndex != value.length()) {
            return false;
        }
        return result;
    }
}
