package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class buffalo extends Animal {
    public buffalo() {
        super(3, 100, 700, 10, "buffalo");
    }


    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;
    }
}
