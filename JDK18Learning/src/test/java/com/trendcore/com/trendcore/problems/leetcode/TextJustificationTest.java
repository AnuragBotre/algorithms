package com.trendcore.com.trendcore.problems.leetcode;

import com.trendcore.problems.leetcode.TextJustification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TextJustificationTest {

    TextJustification textJustification = new TextJustification();

    @Test
    public void withExampleInput() {
        execute(new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
        }, 16, new String[]{
                "This    is    an",
                "example  of text",
                "justification.  "
        });
    }

    @Test
    public void withOtherExampleInputs() {
        execute(new String[]{
                "What","must","be","acknowledgment","shall","be"
        }, 16, new String[]{
                "What   must   be",
                "acknowledgment  ",
                "shall be        "
        });

        execute(new String[]{
                "Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"
        }, 20, new String[]{
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
        });
    }



    @Test
    public void wordsWithGreaterThanMaxWidth() {
        execute(new String[]{
                "This"
        }, 3, new String[]{
                "This"
        });
    }

    public void execute(String[] words, int maxWidth, String[] result) {
        List<String> strings = textJustification.fullJustify(words, maxWidth);

        Object[] objects = strings.toArray();

        Assert.assertArrayEquals(objects, result);
    }
}
