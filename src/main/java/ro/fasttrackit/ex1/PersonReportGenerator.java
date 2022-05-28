package ro.fasttrackit.ex1;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PersonReportGenerator {
    private final PersonProvider personProvider;


    public void generateReport(String outputFile) throws IOException {
        List<Person> personList = personProvider.readPersons();
        generateReport(personList, outputFile);

    }

    private void generateReport(List<Person> personList, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            personList.stream()
                    .collect(Collectors.groupingBy(person2Group, Collectors.mapping(Person::firstName, Collectors.toList())))
                    .entrySet().stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .sorted()
                    .forEach(line -> writeLine(writer, line));
        }
    }

    Function<Person, Range> person2Group = person -> {
        if (person.age() >= 1 && person.age() < 30) {
            return new Range(1, 30);
        } else if (person.age() >= 30 && person.age() < 50) {
            return new Range(30, 50);
        } else {
            return new Range(50, 120);
        }
    };

    private void writeLine(BufferedWriter writer, String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
