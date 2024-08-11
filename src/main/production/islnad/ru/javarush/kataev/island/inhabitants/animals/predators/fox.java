package inhabitants.animals.predators;

import inhabitants.animals.Animal;

public class fox extends Animal {
    public fox() {
        super(2, 2, 8, 30, "fox");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "Caterpillar" -> 0.4f;
            case "Duck" -> 0.6f;
            case "Rabbit" -> 0.7f;
            case "Mouse" -> 0.9f;
            default -> 0;
        };
    }
}
