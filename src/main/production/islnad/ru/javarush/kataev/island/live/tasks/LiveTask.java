package live.tasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class LiveTask implements Runnable {

    private final EatingTask eatingTask;
    private final HeathDecreaseTask heathDecreaseTask;
    private final ReproducingTask reproducingTask;
    private final MoveTask moveTask;

    public LiveTask() {
       eatingTask = new EatingTask();
       heathDecreaseTask = new HeathDecreaseTask();
       reproducingTask = new ReproducingTask();
       moveTask = new MoveTask();
    }
    public int getAnimalsEaten() {
        return animalsEaten;
    }

    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }

    public int getBabies() {
        return babies;
    }

    private int animalsEaten;
    private int animalsDiedByHungry;
    private int babies;

    @Override
    public void run() {
         ExecutorService live = Executors.newFixedThreadPool(4);
         FutureTask<Integer> eatenAnimals = new FutureTask<>(eatingTask);
         FutureTask<Integer> diedAnimals = new FutureTask<>(heathDecreaseTask);
         FutureTask<Integer> newAnimals = new FutureTask<>(reproducingTask);
         FutureTask<Integer> moveDirections = new FutureTask<>(moveTask);
         live.execute(eatenAnimals);
         while (true) {
             if (eatenAnimals.isDone()) {
                 live.execute(newAnimals);
                // System.out.println("new animals task");
                 if (newAnimals.isDone()) {
                     //System.out.println("die task");
                     live.execute(diedAnimals);
                    // System.out.println("die task");
                     break;
                 }
             }
         }
        while (true) {
            try {
                if (eatenAnimals.isDone()  && newAnimals.isDone() && diedAnimals.isDone()) {
                    animalsEaten = eatenAnimals.get();

                    animalsDiedByHungry = diedAnimals.get();

                    babies = newAnimals.get();
                   break;
                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }

        live.execute(moveDirections);

        while (true) {
            try {
                if (moveDirections.isDone()) {
                    moveDirections.get();

                    break;
                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        }


    }

