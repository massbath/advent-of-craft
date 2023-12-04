package people;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private final List<Person> people = new ArrayList<>();

    public void add(Person person) {
        people.add(person);
    }

    public Person findPersonWithYoungestPet() {
        return people.stream()
                .reduce(people.getFirst(),
                        (person, anotherPerson) -> person.youngestPet() <= anotherPerson.youngestPet() ? person : anotherPerson);
    }
}
