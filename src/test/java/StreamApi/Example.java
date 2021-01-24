package StreamApi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example {

    private List<String> list;

    @Test
    public void testFirst() {
        /*
        Получение объекта Stream
         */

        IntStream.of(120, 410, 85, 32, 314, 12)
                .filter(x -> x < 300)
                .map(x -> x + 11)
                .limit(3)
                .forEach(System.out::print);
    }

    @Test
    public void testSecond() {
        this.list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");

        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
        stream.filter(s -> s.contains("Third"));
        stream.forEach(System.out::println);
    }


}
