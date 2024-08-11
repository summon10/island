package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class duck extends Animal {
    public duck() {
        super(4, 0.15, 1, 200, "caterpillar");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "caterpillar" -> 0.9f;
            case "plant" -> 1;
            default -> 0;
        };
    }
}
