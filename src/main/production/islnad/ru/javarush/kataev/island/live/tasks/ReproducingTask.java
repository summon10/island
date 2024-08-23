package live.tasks;

import inhabitants.animals.Animal;
import island.Field;
import island.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ReproducingTask implements Callable<Integer> {
    private int babies;

    @Override
    public Integer call() {
        babies = 0;
        List<Animal> animals = Field.getInstance().getAllAnimals();
        List<Animal> animalsMultiplied = new ArrayList<>();
        if (animals.size() > 0) {
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = Field.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Animal> locationAnimals = location.getAnimals();

                    if (locationAnimals.size() > 1) {
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.reproduce(partner);

                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;
                            }
                        }
                    }
                }
            }
        }
        return babies;
    }
}



