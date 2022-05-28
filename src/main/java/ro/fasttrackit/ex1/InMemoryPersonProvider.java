package ro.fasttrackit.ex1;

import java.util.List;

public class InMemoryPersonProvider implements PersonProvider {
    @Override
    public List<Person> readPersons() {
        return List.of(
                new Person("Maria", "Patru", 22)
                , new Person("Dragos", "Baciu", 33)
                , new Person("Ciprian", "Axinte", 55)
                , new Person("Marius", "Dumitru", 14)
                , new Person("Sofia", "Patrusu", 4)
        );
    }
}
