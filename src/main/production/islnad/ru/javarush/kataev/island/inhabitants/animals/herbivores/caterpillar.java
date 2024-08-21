package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class caterpillar extends Animal  {

    public caterpillar() {
        super(0, 0, 0.01, 1000, "caterpillar");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;
    }
    public String getPicture ()
    {
        return "\uD83D\uDC1B";
    }
}

