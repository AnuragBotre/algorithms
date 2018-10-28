package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/wildcard-matching/
 * Given an input string (s) and a pattern (p),
 * implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
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
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 * <p>
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 * <p>
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence,
 * while the second '*' matches the substring "dce".
 * Example 5:
 * <p>
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class WildCardMatching {

    public static void main(String[] args) {
        WildCardMatching w = new WildCardMatching();

        w.testCase("bba", "*a**", true);
        w.testCase("baaabab", "*****ba*****ab", true);
        /*w.testCase("abc", "**" , true);
        w.testCase("abc", "*" , true);
        w.testCase("abc", "***" , true);
        w.testCase("abc", "abc",true);
        w.testCase("abc", "???",true);
        w.testCase("abc", "a" , false);
        w.testCase("abc", "a*c" , true);
        w.testCase("abc", "a?c" , true);
        w.testCase("abc", "a**c" , true);
        w.testCase("abc", "**c" , true);
        w.testCase("abc", "*b*c" , true);
        w.testCase("", "*" , true);
        w.testCase("acdcb", "a*c?b" , false);*/
        w.testCase("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*", true);
        //w.testCase("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*", true);
        /*w.testCase("ababba","***b*ab***ba",true);*/
        w.testCase("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb", "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb", true);
    }

    private void testCase(String abc, String s, boolean b) {
        System.out.println("Expected :- " + b + " Actual :- " + isMatch(abc, s));
    }

    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }

        p = getModifiedPatternString(p);
        //System.out.println(p);
        return traverse(s, 0, p, 0);
        //return foo(s,p);
        //return matchHere(s, p, 0,0);
        //return helper(s, p, 0,0);
        //return isMatchRecursiveUtil(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private String getModifiedPatternString(String p) {
        String newPattern = "";
        char prevC = 0;
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i == 0) {
                prevC = p.charAt(i);
                newPattern = newPattern + p.charAt(i);
            } else {
                if (p.charAt(i) == '*' && prevC == '*') {
                    count++;
                    if (count >= 1) {
                        continue;
                    }
                } else {
                    count = 0;
                }

                newPattern = newPattern + p.charAt(i);
                prevC = p.charAt(i);
            }
        }
        return newPattern;
    }

    /**
     * This Algo is working.
     * But takes more time.
     * Need to optmize it using dynamic programming.
     * @param s
     * @param stringPointer
     * @param p
     * @param patternPointer
     * @return
     */
    private boolean traverse(String s, int stringPointer, String p, int patternPointer) {

        if (stringPointer < 0) {
            return false;
        }

        if (stringPointer == s.length() - 1 && patternPointer == p.length() - 1) {
            char c = s.charAt(stringPointer);
            char pc = p.charAt(patternPointer);

            if (c == pc) {
                return true;
            } else if (pc == '*' || pc == '?') {
                return true;
            }

            return false;
        }

        if (patternPointer >= p.length()) {
            return false;
        }

        if (stringPointer >= s.length()) {
            //there are no * character left
            for (int i = patternPointer; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        char c = p.charAt(patternPointer);

        if (c == '*') {
            //TODO Need to fix this.
            //do we need to keep
            boolean traverse = traverse(s, stringPointer + 1, p, patternPointer);

            //how to backtrack
            if (!traverse) {
                traverse = traverse(s, stringPointer, p, patternPointer + 1);
            }
            return traverse;
        } else if (c == '?') {
            return traverse(s, stringPointer + 1, p, patternPointer + 1);
        } else if (c == s.charAt(stringPointer)) {
            return traverse(s, stringPointer + 1, p, patternPointer + 1);
        }

        return false;
    }

    public boolean foo(String s, String p) {

        if(p.isEmpty()) return s.isEmpty();

        if(s.isEmpty()) {
            int i=-1;
            while(++i<p.length()) {
                if(p.charAt(i) != '*') return false;
            }
            return true;
        }

        if(p.length() == 1 && p.charAt(0) == '*') return true;

        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '?' || p.charAt(0) == '*');

        if(p.charAt(0) == '*' && p.length() >= 2) {

            return foo(s,p.substring(1)) || foo(s.substring(1),p) ;
        }

        else return firstMatch && foo(s.substring(1),p.substring(1));
    }

    /*public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }*/

    private boolean combinationsOfZeroOrManyOccurances(String s, int stringPointer, String p, int patternPointer, int originalStringPointer, int originalPatternPointer) {

        if (stringPointer >= s.length()) {
            return false;
        }

        if (patternPointer >= p.length()) {
            return false;
        }

        combinationsOfZeroOrManyOccurances(s, stringPointer + 1, p, patternPointer, originalStringPointer, originalPatternPointer);
        combinationsOfZeroOrManyOccurances(s, stringPointer - 1, p, patternPointer + 1, originalStringPointer, originalPatternPointer);

        return true;

    }

    private boolean traverseStar(String s, int stringPointer, String p, int patternPointer) {
        return traverse(s, stringPointer + 1, p, patternPointer);
    }


    private boolean matchHere(String s, String p, int indexS, int indexP){
        if(indexS>=s.length()){
            return isPatternTailingMatch(p,indexP);
        }
        if(indexP>=p.length()){
            return isPatternEndWithWildCard(p);
        }
        if(s.charAt(indexS)==p.charAt(indexP)||p.charAt(indexP)=='?'){
            return matchHere(s,p,indexS+1,indexP+1);
        }
        else if(p.charAt(indexP)=='*'){
            return matchWildCard(s,p,indexS,indexP+1);
        }
        return false;
    }

    private boolean isPatternTailingMatch(String p, int indexP){
        for(int index=indexP; index<p.length();index++){
            if(p.charAt(index)!='*'){
                return false;
            }
        }
        return true;
    }
    private boolean isPatternEndWithWildCard(String p){
        return p.endsWith("*");
    }
    private boolean matchWildCard(String s, String p, int indexS, int indexP){
        for(int index=indexS;index<s.length();index++){
            if(matchHere(s,p,index,indexP)){
                return true;
            }
        }
        return false;
    }


    boolean helper(String s, String p, int l, int r) {
        if(r == p.length()) return l == s.length();

        if(p.charAt(r) == '*') {
            while(r < p.length() && p.charAt(r) == '*') r++;   // Move the index at p to a non-start char.
            while(l < s.length()) {
                if(helper(s, p, l, r)) return true; // Find one match, return true.
                l++; // Try the next one.
            }
            return helper(s, p, l, r);
        }else if(l < s.length() && (p.charAt(r) == '?' || s.charAt(l) == p.charAt(r))){
            return helper(s, p, l + 1, r + 1);
        }else{
            return false;
        }
    }

    private boolean isMatchRecursiveUtil(char[] text, char[] pattern, int pos1, int pos2) {
        if (pos2 == pattern.length) {
            return text.length == pos1;
        }

        if (pattern[pos2] != '*') {
            if (pos1 < text.length && (text[pos1] == pattern[pos2]) || pattern[pos2] == '?') {
                return isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1);
            } else {
                return false;
            }
        } else {
            //if we have a***b then skip to the last *
            while (pos2 < pattern.length - 1 && pattern[pos2 + 1] == '*') {
                pos2++;
            }
            pos1--;
            while (pos1 < text.length) {
                if (isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1)) {
                    return true;
                }
                pos1++;
            }
            return false;
        }
    }


}
