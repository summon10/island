package inhabitants.animals.predators;

import inhabitants.animals.Animal;

public class snake extends Animal {
    public snake() {
        super(1, 3, 15, 30, "snake");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "Duck" -> 0.1f;
            case "Fox" -> 0.15f;
            case "Rabbit" -> 0.2f;
            case "Mouse" -> 0.4f;
            default -> 0;
        };
    }
    public String getPicture ()
    {
        return "\uD83D\uDC0D";
    }
}
