package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class sheep extends Animal {
    public sheep() {
        super(3, 15, 70, 140, "sheep");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;

    }
    public String getPicture ()
    {
        return "\uD83D\uDC11";
    }
}
