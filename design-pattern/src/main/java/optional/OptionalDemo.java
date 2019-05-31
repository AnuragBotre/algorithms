package optional;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        OptionalDemo optionalDemo = new OptionalDemo();
        Integer value1 = null;
        Integer value2 = 3;

        Optional<Integer> optional1 = Optional.ofNullable(0);
        Optional<Integer> optional2 = Optional.of(value2);

        System.out.println(optionalDemo.add(optional1,optional2));

    }

    private Integer add(Optional<Integer> optional1, Optional<Integer> optional2) {
        optional1.isPresent();

        Integer integer1 = optional1.get();
        Integer integer2 = optional2.get();

        return integer1 + integer2;
    }

}
