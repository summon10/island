package inhabitants.animals.predators;

import inhabitants.animals.Animal;

public class wolf extends Animal {
    public wolf() {
        super(3, 8, 50, 30, "wolf");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "Horse", "Buffalo" -> 0.1f;
            case "Deer", "WildBoar" -> 0.15f;
            case "Duck" -> 0.4f;
            case "Goat", "Rabbit" -> 0.6f;
            case "Sheep" -> 0.7f;
            case "Mouse" -> 0.8f;
            default -> 0;
        };
    }
}
