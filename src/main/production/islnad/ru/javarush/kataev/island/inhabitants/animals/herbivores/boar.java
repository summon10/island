package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class boar extends Animal   {


    public boar() {
        super(2, 50, 400, 50, "boar");
    }


    @Override
    public float getChanceToEat(String wantToBeEaten) {
         {
            return switch (wantToBeEaten) {
                case "mouse" -> 0.5f;
                case "caterpillar" -> 0.9f;
                case "plant" -> 1;
                default -> 0;
            };
        }
    }
}
