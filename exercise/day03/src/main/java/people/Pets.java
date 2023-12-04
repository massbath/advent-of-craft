package people;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class Pets {
    private final List<Pet> pets = new ArrayList<>();

    public OptionalInt youngest() {
        return pets.stream().mapToInt(Pet::age).min();
    }

    public void add(Pet pet) {
        pets.add(pet);
    }
}
