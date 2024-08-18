package live.tasks;

import live.Round;

public class PlantGrowthTask implements Runnable {
    @Override
    public void run() {
        int countPlants = 20;
        if (Round.getInstance().getTimeNow() >= 2) {
            Round.getInstance().placePlants(countPlants / 2);
        } else {
            Round.getInstance().placePlants(countPlants);
        }

    }

}
