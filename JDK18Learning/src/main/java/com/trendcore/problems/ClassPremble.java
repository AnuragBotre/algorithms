package com.trendcore.problems;

/**
 * Created by Anurag
 */
public @interface ClassPremble {
    String author();
    int rev() default 1;
    String l() default "N/A";

    String[]rev1();
}
