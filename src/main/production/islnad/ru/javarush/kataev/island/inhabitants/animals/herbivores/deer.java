package inhabitants.animals.herbivores;

import inhabitants.animals.Animal;

public class deer extends Animal {
    public deer() {
        super(4, 50, 300, 20, "deer");
    }

    @Override
    public float getChanceToEat(String wantToBeEaten) {
        if (wantToBeEaten.equals("plant")) return 100;
        else return 0;
    }

    public String getPicture ()
    {
        return "\uD83E\uDD8C";
    }
}
