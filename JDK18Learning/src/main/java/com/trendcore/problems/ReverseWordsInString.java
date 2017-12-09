package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class ReverseWordsInString {

    /**
     * Given an input string, reverse the string word by word.
     * A word is defined as a sequence of non-space characters.
     * <p>
     * The input string does not contain leading or trailing spaces and the words are always separated
     * by a single space.
     * <p>
     * Given char s[] = {"the sky is blue"},
     * return "blue is sky the".
     *
     * @param args
     */
    public static void main(String[] args) {
        /*String line = "the sky is blue";

        for (int i = line.length() - 1 , j = i; i >= 0; i--) {
            if (line.charAt(i) == ' ') {
                printWord(line,i+1,j, true);
                j = i-1;
            }else if(i  == 0){
                printWord(line,i,j,false);
            }
        }*/

        //reverse this char sequence
        char s[] = "the sky is blue".toCharArray();

        reverse(s);
        String reverse = new String(s);
        System.out.println(reverse);
    }

    private static void reverse(char[] s) {

        for(int i = 0,j=s.length-1 ; i < s.length/2 ; i++,j--){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

        int startPos = 0;
        for(int i = 0 ; i < s.length ; i++){
            if(s[i] == ' '){
                reverseWord(s,startPos,i-1);
                startPos = i+1;
            }else if(i == s.length-1){
                reverseWord(s,startPos,i);
            }
        }
    }

    private static void reverseWord(char[] s, int start, int end) {
        for(int i = start , j = end , loop = 0 ; loop < ((end-start)/2)+1 ; i++,j--,loop++){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }



    /*private static void printWord(String line, int i, int j, boolean printSpace) {
        for(int index = i ; index <= j ; index++){
            System.out.print(line.charAt(index));
        }

        if(printSpace)
            System.out.print(" ");
    }*/
}
