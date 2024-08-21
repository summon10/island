package live.tasks;

import inhabitants.Inhabitant;
import inhabitants.animals.*;
import inhabitants.plants.Plant;
import island.Field;
import island.Location;
import live.Round;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class EatingTask implements Callable<Integer> {

    private int animalsEaten;

    @Override
    public Integer call() {
        animalsEaten = 0;
        List<Animal> animals = Field.getInstance().getAllAnimals();
        List<Inhabitant> inhabitantsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getName().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getMaxHp() > 0) {
                    Location location = Field.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Inhabitant> locationInhabitants = location.getInhabitants();

                    if (!locationInhabitants.isEmpty()) {
                        for (Inhabitant inhabitant : locationInhabitants) {
                            if (currentAnimal.getChanceToEat(inhabitant.getName()) > 0 && !(inhabitantsEaten.contains(inhabitant))) {
                                //System.out.println(currentAnimal.getPicture() + " ate " + inhabitant.getPicture());
                               boolean isEaten = currentAnimal.eat(inhabitant);

                                if (isEaten) {
                                    if (inhabitant instanceof Animal animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            Field.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                        }
                                        inhabitantsEaten.add(animalEaten);
                                        animalsEaten++;

                                    } else {
                                        Plant plant = (Plant) inhabitant;
                                        if (!location.getPlants().isEmpty()) {
                                            Field.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {
            System.out.printf("На %d день существования острова все живое погибло! Вы проиграли.", StatTask.getCurrentDay());
            Round.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("На %d день в живых остались только гусеницы!", StatTask.getCurrentDay());
           Round.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }

        return animalsEaten;
    }

}

