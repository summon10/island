package live.tasks;

import live.Round;

import java.util.concurrent.*;

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

        Round round = Round.getInstance();


        FutureTask<Integer> eatenAnimals = new FutureTask<>(eatingTask);
         FutureTask<Integer> diedAnimals = new FutureTask<>(heathDecreaseTask);
         FutureTask<Integer> newAnimals = new FutureTask<>(reproducingTask);
         FutureTask<Integer> moveDirections = new FutureTask<>(moveTask);
         live.execute(eatenAnimals);
         live.execute(diedAnimals);
         live.execute(newAnimals);


        try {
            animalsEaten = eatenAnimals.get();
           animalsDiedByHungry = diedAnimals.get();
           babies = newAnimals.get();
            System.out.println(animalsEaten);
            System.out.println(animalsDiedByHungry);
            System.out.println(babies);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println();

        live.execute(moveDirections);


    }
}
