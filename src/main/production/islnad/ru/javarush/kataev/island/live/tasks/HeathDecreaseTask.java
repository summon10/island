package live.tasks;

import inhabitants.animals.Animal;
import island.Field;
import island.Location;
import live.Round;

import java.util.List;
import java.util.concurrent.Callable;

public class HeathDecreaseTask implements Callable<Integer> {
    private double percentOfHpToDecrease = 15;

    private int animalsDiedByHungry;

    @Override
    public Integer call() {
        animalsDiedByHungry = 0;

        List<Animal> animals = Field.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHp() > 0).toList();

        if (Round.getInstance().getTimeNow() / 60 >= 3) {
            percentOfHpToDecrease *= 2;
        }
        for (Animal animal : animals) {
            if (animal != null) {
                double hpToDecrease = animal.getMaxHp() * percentOfHpToDecrease / 100.0;
                if (animal.getHp() - hpToDecrease > 0) {
                    animal.setHp(animal.getHp() - hpToDecrease);
                } else {
                    Location location = Field.getInstance().getLocation(animal.getRow(), animal.getColumn());
                    Field.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                    animalsDiedByHungry++;
                }
            }
        }

        return animalsDiedByHungry;
    }

}
