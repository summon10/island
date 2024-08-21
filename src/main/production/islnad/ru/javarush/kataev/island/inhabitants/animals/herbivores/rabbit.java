package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class rabbit extends Animal {
    public rabbit() {
        super(2, 0.45, 2, 150, "rabbit");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;

    }
    public String getPicture ()
    {
        return "\uD83D\uDC07";
    }
}
