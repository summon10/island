package live.tasks;

import inhabitants.animals.Animal;
import island.Field;

import java.util.List;
import java.util.concurrent.Callable;

public class MoveTask implements Callable<Integer> {
    public Integer call() {

        List<Animal> animals = Field.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        int direction=-1;
        for (Animal animal : animals) {
            direction = animal.move();
        }
        return direction;
    }
}
