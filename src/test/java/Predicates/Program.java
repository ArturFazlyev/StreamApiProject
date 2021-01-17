package Predicates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Cергей", 20));
        persons.add(new Person("Лада", 20));


        // Печать коллекции
        persons.stream().forEach(System.out::println);
        // Перебор коллекции
        persons.stream().filter(person -> person.getAge() >= 18).forEach(System.out::println);

        // Отсортировать коллекцию
        persons.stream().filter(person -> person.getAge() >= 18).sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);

        // Отфильтровать отсортированную коллекцию
        persons.stream().filter(person -> person.getAge() >= 18).map(Person::getName)
                .forEach(System.out::println);


        // Вывести среднее арифметическое
        System.out.println(persons.stream().filter(person -> person.getAge() >= 18).mapToInt(p -> p.getAge()).average().getAsDouble());


    }
}
