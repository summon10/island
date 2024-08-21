package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class horse extends Animal {
    public horse() {
        super(4, 60, 400, 1000, "horse");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;
    }
    public String getPicture ()
    {
       return "\uD83D\uDC0E";
    }
}
