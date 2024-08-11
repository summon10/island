package inhabitants.animals.predators;

import inhabitants.animals.Animal;

public class eagle extends Animal {
    public eagle() {
        super(3, 1, 6, 20, "eagle");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "Fox" -> 0.1f;
            case "Duck" -> 0.8f;
            case "Rabbit", "Mouse" -> 0.9f;
            default -> 0;
        };
    }
}
