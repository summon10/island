package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class duck extends Animal {
    public duck() {
        super(4, 0.15, 1, 200, "duck");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "caterpillar" -> 0.9f;
            case "plant" -> 1;
            default -> 0;
        };
    }
    public String getPicture ()
    {
        return "\uD83E\uDD86";
    }
}
