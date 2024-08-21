package inhabitants.animals.predators;

import inhabitants.animals.Animal;

public class bear extends Animal {
    public bear() {
        super(2, 80, 500, 5, "bear");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        return switch (wantToBeEaten) {
            case "Duck" -> 0.1f;
            case "Buffalo" -> 0.2f;
            case "Horse" -> 0.4f;
            case "WildBoar" -> 0.5f;
            case "Goat", "Sheep" -> 0.7f;
            case "Deer", "Rabbit", "Snake" -> 0.8f;
            case "Mouse" -> 0.9f;
            default -> 0;
        };
    }
   public String getPicture ()
    {
        return "\uD83D\uDC3B";
    }
}
