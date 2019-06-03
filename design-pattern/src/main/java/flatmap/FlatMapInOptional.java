package flatmap;

import java.util.Optional;
import java.util.stream.Stream;

public class FlatMapInOptional {

    public static void main(String[] args) {
        Optional<String> nullOptional = Optional.ofNullable(null);

        String not_found = nullOptional.flatMap(s -> Optional.ofNullable(s)).map(o -> ((String) o).toUpperCase()).orElse("Not Found");
        System.out.println(not_found);
    }

}
