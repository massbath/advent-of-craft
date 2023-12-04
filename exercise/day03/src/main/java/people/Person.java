package people;

public record Person(String firstName, String lastName, Pets pets) {
    public Person(String firstName, String lastName) {
        this(firstName, lastName, new Pets());
    }

    public int youngestPet() {
        return pets.youngest().orElse(Integer.MAX_VALUE);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }
}

