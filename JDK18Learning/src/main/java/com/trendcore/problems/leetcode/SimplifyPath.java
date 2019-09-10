package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/simplify-path/
 * <p>
 * 71. Simplify Path
 * <p>
 * Given an absolute path for a file (Unix-style), simplify it.
 * Or in other words, convert it to the canonical path.
 * <p>
 * In a UNIX-style file system, a period . refers to the current directory.
 * Furthermore, a double period .. moves the directory up a level.
 * For more information, see: Absolute path vs relative path in Linux/Unix
 * <p>
 * Note that the returned canonical path must always begin with a slash /,
 * and there must be only a single slash / between two directory names.
 * The last directory name (if it exists) must not end with a trailing /.
 * Also, the canonical path must be the shortest string representing the absolute path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 * <p>
 * Input: "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op,
 * as the root level is the highest level you can go.
 * Example 3:
 * <p>
 * Input: "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are
 * replaced by a single one.
 * Example 4:
 * <p>
 * Input: "/a/./b/../../c/"
 * Output: "/c"
 * Example 5:
 * <p>
 * Input: "/a/../../b/../c//.//"
 * Output: "/c"
 * Example 6:
 * <p>
 * Input: "/a//b////c/d//././/.."
 * Output: "/a/b/c"
 */
public class SimplifyPath {

    public String simplifyPath(String path) {

        List list = new ArrayList<>();

        String word = "";

        for (int i = 0; i < path.length(); i++) {

            if (path.charAt(i) == '/') {

                if (!word.isEmpty())
                    list.add(word);

                word = "";
            } else if (i + 1 < path.length() &&
                    path.charAt(i) == '.' && path.charAt(i + 1) == '.') {

                //double dot means remove whatever added last from list
                if (!list.isEmpty()) {
                    int size = list.size();
                    list.remove(size - 1);
                }

            } else if (path.charAt(i) == '.') {

            } else {
                word = word + path.charAt(i);
            }
        }

        if (!word.isEmpty()) {
            list.add(word);
        }

        return simplifyPath(list);
    }

    public String simplifyPath(List words) {
        String simplifiedPath = "/";
        for (int i = 0; i < words.size(); i++) {
            simplifiedPath = simplifiedPath + words.get(i);
            if (i != words.size() - 1) {
                simplifiedPath = simplifiedPath + "/";
            }
        }
        return simplifiedPath;
    }
}
