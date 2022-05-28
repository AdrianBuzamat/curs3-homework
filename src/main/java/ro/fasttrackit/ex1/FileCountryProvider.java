package ro.fasttrackit.ex1;

import lombok.RequiredArgsConstructor;

import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
public class FileCountryProvider implements PersonProvider {
    private final String sourceFile;

    @Override
    public List<Person> readPersons() {
        try {
            return Files.lines(Path.of(sourceFile))
                    .map(this::toPersons)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Person toPersons(String line) {
        String[] tokens = line.split(",");
        return new Person(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
    }
}
