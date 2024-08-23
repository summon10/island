package live;

import inhabitants.animals.Animal;
import inhabitants.animals.herbivores.*;
import inhabitants.animals.predators.*;
import inhabitants.plants.Plant;
import island.Field;
import island.Location;
import live.tasks.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Round {
    private final long startTime;

    private static volatile Round instance;
    Random random = new Random();
   public LiveTask liveTask = new LiveTask();
    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    private volatile ScheduledExecutorService executorService;



    private Round() {
        startTime = System.currentTimeMillis();
    }


    public static Round getInstance() {
        if (instance == null) {
            synchronized (Round.class) {
                if (instance == null) {
                    instance = new Round();
                }
            }
        }
        return instance;
    }

    public void createAndRunIsland(int countPlants, int countAnimals) { // создать остров с заданными параметрами
        locateAnimals(countAnimals);
        placePlants(countPlants);
        runIslandModel();
    }

    private void runIslandModel() {  // запустить остров
        executorService = Executors.newScheduledThreadPool(3);
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatTask statTask = new StatTask();

        executorService.scheduleAtFixedRate(liveTask, 1, 4, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantGrowthTask, 10, 10, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statTask, 0, 4, TimeUnit.SECONDS);
    }

    public void locateAnimals(int countPredators) {  // разместить животных на острове
        List<Animal> animals = createAnimals(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Animal animal : animals) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(Field.getInstance().getNumRows());
                int column = random.nextInt(Field.getInstance().getNumColumns());
                Location location = Field.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(animal.getName())).toList().size() <= animal.getMaxPopulation()) {
                    Field.getInstance().addAnimal(animal, row, column);
                    placed = true;
                }
            }
        }
    }

    private List<Animal> createAnimals(int countAnimals) { // создать список животных с заданным количеством
        List<Animal> animals = new ArrayList<>();
                // создаем по одному животному каждого вида
        animals.add(new bear());
        animals.add(new eagle());
        animals.add(new fox());
        animals.add(new snake());
        animals.add(new wolf());
        animals.add(new boar());
        animals.add(new buffalo());
        animals.add(new caterpillar());
        animals.add(new deer());
        animals.add(new duck());
        animals.add(new goat());
        animals.add(new horse());
        animals.add(new mouse());
        animals.add(new rabbit());
        animals.add(new sheep());

        // генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countAnimals - animals.size();
        for (int i = 0; i < remainingCount; i++) {
            // генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(animals.size());
            Animal randomAnimal = animals.get(randomIndex);
            try {
                // создаем экземпляр животного через рефлексию
                Animal newAnimal = randomAnimal.getClass().getConstructor().newInstance();
                animals.add(newAnimal);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }

        }

        return animals;
    }

    public long getTimeNow() { // получить текущее время симуляции
        return (System.currentTimeMillis() - startTime) / 1000;
    }
    private List<Plant> createPlants(int countPlants) { // создать список растений с заданным количеством
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    public void placePlants(int countPlants) {  // разместить растения на острове
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(Field.getInstance().getNumRows());
                int column = random.nextInt(Field.getInstance().getNumColumns());
                Location location = Field.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    Field.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }
}




