package inhabitants.plants;

import inhabitants.Inhabitant;

public class Plant extends Inhabitant {
    private final String name = "Plant";
    private final int maxPopulation = 200;
    private final float weight = 1;

    public Plant() {
        super(1, "Plant", 200);
    }
}
