import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import people.Person;
import people.Pet;
import people.PetType;
import people.Population;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static people.PetType.*;

class PopulationTests {
    private static Population population;

    @BeforeAll
    static void setup() {
        population = aPopulation(
                with("Peter", "Griffin", having(CAT, "Tabby", 2)),
                with("Stewie", "Griffin", having(CAT, "Dolly", 3), having(DOG, "Brian", 9)),
                with("Joe", "Swanson", having(DOG, "Spike", 4)),
                with("Lois", "Griffin", having(SNAKE, "Serpy", 1)),
                with("Meg", "Griffin", having(BIRD, "Tweety", 1)),
                with("Chris", "Griffin", having(TURTLE, "Speedy", 4)),
                with("Cleveland", "Brown", having(HAMSTER, "Fuzzy", 1), having(HAMSTER, "Wuzzy", 2)),
                with("Glenn", "Quagmire"));
    }

    @Test
    void whoOwnsTheYoungestPet() {
        var personWithYoungestPet = population.findPersonWithYoungestPet();

        assertThat(personWithYoungestPet).isNotNull();
        assertThat(personWithYoungestPet)
                .extracting("firstName", "lastName").containsExactly("Lois", "Griffin");
    }

    private static Population aPopulation(Person... people) {
        var population = new Population();
        stream(people).forEach(population::add);
        return population;
    }

    private static Person with(String firstName, String lastName, Pet... pets) {
        var person = new Person(firstName, lastName);
        stream(pets).forEach(person::addPet);
        return person;
    }

    private static Pet having(PetType type, String name, int age) {
        return new Pet(type, name, age);
    }
}
