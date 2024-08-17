package live.tasks;

import live.Round;

import java.util.concurrent.Callable;

public class PlantGrowthTask implements Callable<Integer> {
    @Override
    public Integer call() {
        int countPlants = 20;
        if (Round.getInstance().getTimeNow() >= 2) {
            Round.getInstance().placePlants(countPlants / 2);
        } else {
            Round.getInstance().placePlants(countPlants);
        }
    return 1;
    }

}
