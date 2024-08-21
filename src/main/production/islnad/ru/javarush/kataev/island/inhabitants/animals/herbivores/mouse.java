package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class mouse extends Animal {
    public mouse() {
        super(1, 0.01, 0.05, 500, "mouse");
    }
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "Caterpillar" -> 0.9f;
            case "Plant" -> 1;
            default -> 0;
        };
    }
    public String getPicture ()
    {
        return "\uD83D\uDC01";
    }
}
