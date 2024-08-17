package live.tasks;

import inhabitants.Inhabitant;
import inhabitants.animals.Animal;
import inhabitants.plants.Plant;
import island.Field;
import island.Location;
import live.Round;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class EatingTask implements Callable<Integer> {
   // private final CountDownLatch latch;
    private int animalsEaten;


 //   public EatingTask(CountDownLatch latch) {
  //      this.latch = latch;
 //   }

    @Override
    public Integer call() {
        animalsEaten = 0;
        List<Animal> animals = Field.getInstance().getAllAnimals();
        List<Inhabitant> lifeFormsEaten = new ArrayList<>();
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
                            if (currentAnimal.getChanceToEat(inhabitant.getName()) > 0 && !(lifeFormsEaten.contains(inhabitant))) {
                                boolean isEaten = currentAnimal.eat(inhabitant);

                                if (isEaten) {
                                    if (inhabitant instanceof Animal animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            Field.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                        }
                                        lifeFormsEaten.add(animalEaten);
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
            System.out.printf("На %d день существования острова все живое погибло! Вы проиграли.", StatisticsTask.getCurrentDay());
            Round.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("На %d день в живых остались только гусеницы!", StatisticsTask.getCurrentDay()); // так как они бесссмертные и не требуют пищи
           Round.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
     //   latch.countDown();
        return animalsEaten;
    }

}

