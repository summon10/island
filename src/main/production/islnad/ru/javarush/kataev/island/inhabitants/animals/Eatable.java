package inhabitants.animals;

import inhabitants.Inhabitant;

public interface Eatable {
    float getChanceToEat (String nameWantToEat, String wantToBeEaten);
    float eat(Inhabitant food);

    float rabbit_by_wolf = 1;

}
