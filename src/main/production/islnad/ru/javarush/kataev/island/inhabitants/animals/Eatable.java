package inhabitants.animals;

import inhabitants.Inhabitant;

public interface Eatable {
    float getChanceToEat (String wantToBeEaten);
    float eat(Inhabitant food);



}
