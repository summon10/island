package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class goat extends Animal {
    public goat() {
        super(60, 10, 60, 140, "goat");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;
    }

    public String getPicture ()
    {
        return "\uD83D\uDC10";
    }
}
