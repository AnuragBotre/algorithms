package currying;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Letter {

    private String returningAddress;
    private String insideAddress;
    private Date localDate;
    private String salutation;
    private String body;
    private String closing;

    public Letter(String salutation, String body){
        this.salutation = salutation;
        this.body = body;
    }

    /*constructor with more parameters*/
    public Letter(String returningAddress, String insideAddress, Date localDate, String salutation, String body,String closing) {
        this.returningAddress = returningAddress;
        this.insideAddress = insideAddress;
        this.localDate = localDate;
        this.salutation = salutation;
        this.body = body;
        this.closing = closing;
    }

    static Letter createLetter(String salutation, String body) {
        return new Letter(salutation, body);
    }


    static BiFunction<String, String, Letter> SIMPLE_LETTER_CREATOR = (salutation, body) -> new Letter(salutation, body);

    /*This function chaining is called currying*/
    static Function<String, Function<String, Letter>> SIMPLE_CURRIED_LETTER_CREATOR = salutation -> body -> new Letter(salutation,body);

    Letter createLetter(String returnAddress, String insideAddress, Date dateOfLetter,
                        String salutation, String body, String closing) {
        return new Letter(returnAddress, insideAddress, dateOfLetter, salutation, body, closing);
    }

    /*Arbitary Arity*/
    static Function<String,Function<String,Function<Date,Function<String,Function<String,Function<String,Letter>>>>>> LETTER_CREATOR =
            returnAddress ->
            insideAddress ->
            date ->
            salutation ->
            body ->
            closing -> new Letter(returnAddress,insideAddress,date,salutation,body,closing);

    /*Builder Pattern :- Instead of a sequence of functions, we use a sequence of functional interfaces*/
    public static AddReturnAddress builder(){
        AddReturnAddress addReturnAddress = returnAddress ->
                insideAddress ->
                date ->
                salutation ->
                body ->
                closing -> new Letter(returnAddress, insideAddress, date, salutation, body, closing);


        return addReturnAddress;
    }


    interface AddReturnAddress{
        Letter.AddClosing withReturnAddress(String returnAddress);
    }


    public interface AddClosing {
        Letter.AddInsideAddress withInsideAddress(String insideAddress);
    }

    public interface AddInsideAddress {
        Letter.AddDate witDate(Date date);
    }

    public interface AddDate {
        Letter.AddSalutation withSalutation(String salutation);
    }


    public interface AddSalutation {
        Letter.AddBody witBody(String body);
    }

    public interface AddBody {
        Letter witClosing(String closing);
    }
}
