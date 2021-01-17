package Predicates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Person> nums = new ArrayList<>();
        nums.add(new Person("Cергей", 12));
        nums.add(new Person("Лада", 13));


        // Печать коллекции
        nums.stream().forEach(System.out::println);
        // Перебор коллекции
        nums.stream().filter(person -> person.getAge() >=18).forEach(System.out::println);

        nums.stream().filter(person -> person.getAge() >=18).sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);

        nums.stream().filter(person -> person.getAge() >=18).map(Person::getName)
                .forEach(System.out::println);


    }
}
