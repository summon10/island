package inhabitants.plants;

import inhabitants.Inhabitant;

public class Plant extends Inhabitant {
    public String name = "Plant";
    private final int maxPopulation = 200;
    private final float weight = 1;

    public Plant(String name) {
        this.name = name;
    }
}
