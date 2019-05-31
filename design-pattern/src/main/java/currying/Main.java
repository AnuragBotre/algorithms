package currying;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Letter.createLetter("123","test");

        Letter.SIMPLE_LETTER_CREATOR.apply("s","t");

        Letter letter = Letter.SIMPLE_CURRIED_LETTER_CREATOR.apply("Salutation").apply("body");

        Letter letterCreator = Letter.LETTER_CREATOR.apply("ClosingAddress")
                                                    .apply("Inside Address")
                                                    .apply(new Date())
                                                    .apply("salutation")
                                                    .apply("body")
                                                    .apply("closing");


        Letter letterWithBuilderPattern = Letter.builder().withReturnAddress("Return Address")
                .withInsideAddress("Inside Address")
                .witDate(new Date())
                .withSalutation("test")
                .witBody("body")
                .witClosing("closing");


    }

}
