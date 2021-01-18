package StreamApi;

import letscode.javalearn.collections.Department;
import letscode.javalearn.collections.Employee;
import letscode.javalearn.collections.Event;
import letscode.javalearn.collections.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    private List<Employee> emps = List.of(
            new Employee("Michael", "Smith", 243, 43, Position.CHEF),
            new Employee("Jane", "Smith", 523, 40, Position.MANAGER),
            new Employee("Jury", "Gagarin", 6423, 26, Position.MANAGER),
            new Employee("Jack", "London", 5543, 53, Position.WORKER),
            new Employee("Eric", "Jackson", 2534, 22, Position.WORKER),
            new Employee("Andrew", "Bosh", 3456, 44, Position.WORKER),
            new Employee("Joe", "Smith", 723, 30, Position.MANAGER),
            new Employee("Jack", "Gagarin", 7423, 35, Position.MANAGER),
            new Employee("Jane", "London", 7543, 42, Position.WORKER),
            new Employee("Mike", "Jackson", 7534, 31, Position.WORKER),
            new Employee("Jack", "Bosh", 7456, 54, Position.WORKER),
            new Employee("Mark", "Smith", 123, 41, Position.MANAGER),
            new Employee("Jane", "Gagarin", 1423, 28, Position.MANAGER),
            new Employee("Sam", "London", 1543, 52, Position.WORKER),
            new Employee("Jack", "Jackson", 1534, 27, Position.WORKER),
            new Employee("Eric", "Bosh", 1456, 32, Position.WORKER)
    );

    private List<Department> deps = List.of(
            new Department(1, 0, "Head"),
            new Department(2, 1, "West"),
            new Department(3, 1, "East"),
            new Department(4, 2, "Germany"),
            new Department(5, 2, "France"),
            new Department(6, 3, "China"),
            new Department(7, 3, "Japan")
    );

    @Test
    public void testCreation() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("D:\\1.txt"));
        Stream<Path> list = Files.list(Paths.get("./"));
        Stream<Path> walk = Files.walk(Paths.get("./"), 3);

        Stream<String> build = Stream.<String>builder()
                .add("Mike")
                .add("Joe")
                .build();

        // Создание stream
        Stream<Employee> stream = emps.stream();
        Stream<Employee> employeeStream = emps.parallelStream();

        Stream.generate(() -> new Event(UUID.randomUUID(),
                LocalDateTime.now(),
                "Description"));

        // Создание stream c шагом
        Stream<Integer> iterate = Stream.iterate(1950, val -> val + 3);

        Stream<Object> concat = Stream.concat(build, stream);


    }

    @Test
    public void testTerminate(){
        emps.stream().count();
        emps.stream().forEach(employee -> System.out.println(employee.getAge()));
        emps.stream().forEachOrdered(employee -> System.out.println(employee.getAge()));

        Map<Integer, String> collect = emps.stream().collect(Collectors.toMap(Employee::getId,
                emps -> String.format("%s, %s",
                        emps.getFirstName(), emps.getLastName())));

        System.out.println(deps.stream().reduce(this::reducer));

        emps.stream().anyMatch(employee -> employee.getPosition() == Position.CHEF);

    }

    @Test
    public void testTransform(){
        IntStream.of(100, 200, 300).mapToLong(Long::valueOf);
        emps.stream().filter(employee -> employee.getAge() >= 18);
        emps.stream().takeWhile(employee -> employee.getAge() > 30).forEach(System.out::println);
    }

    @Test
    public void testReal(){
        Stream<Employee> sorted = emps.stream().filter(employee -> employee.getAge() >= 30 && employee.getPosition() != Position.WORKER)
                .sorted(Comparator.comparing(Employee::getLastName));
        print(sorted);

        Stream<Employee> employeeStream = emps.stream().filter(employee -> employee.getAge() >= 40)
                .limit(4)
                .sorted(Comparator.comparing(Employee::getAge));

        print(employeeStream);

        System.out.println(emps.stream().mapToInt(Employee::getAge).summaryStatistics());


    }

    private void print(Stream<Employee> stream) {
        stream
                .map(emp -> String.format(
                        "%4d | %-15s %-10s age %s %s",
                        emp.getId(),
                        emp.getLastName(),
                        emp.getFirstName(),
                        emp.getAge(),
                        emp.getPosition()
                ))
                .forEach(System.out::println);

        System.out.println();
    }

    public Department reducer (Department parent, Department child){
        if (child.getParent() == parent.getId()){
            parent.getChild().add(child);
        } else {
            parent.getChild().forEach(subParent -> reducer(subParent, child));
        }

        return parent;
    }


}
