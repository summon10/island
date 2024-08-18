package live.tasks;

import island.Field;
import live.Round;

    public class StatTask implements Runnable {
        private boolean isTimeOver;
        private int countAnimalsEnd;
        private int countPlants;
        private static int currentDay = 0;

    //      private final AnimalMultiplyTask animalMultiplyTask;
    //    private final AnimalEatTask animalEatTask;
      //  private final AnimalHpDecreaseTask animalHpDecreaseTask;

      public StatTask() {

      }
        @Override
        public void run() {
            long timeNow = Round.getInstance().getTimeNow();
            isTimeOver = checkTime(timeNow);
            countAnimalsEnd = Field.getInstance().getAllAnimals().size(); // кол-во животных на острове


            countPlants = Field.getInstance().getAllPlants().size();
            printStats();

            if (isTimeOver) {
               Round.getInstance().getExecutorService().shutdown();
                System.exit(0);
            }

        }


        private boolean checkTime(long timeNow) {
            return timeNow / 60 >= 5;
        }

        private void printStats() {  //   вывод статистику моделирования
            if (isTimeOver) {
                System.out.println("ПОБЕДА!!! ВЫ ПРОДЕРЖАЛИСЬ 5 МИНУТ!");
                System.out.println("--".repeat(20));
            } else {
                System.out.printf("--- ДЕНЬ %d ---", currentDay);
                currentDay++;
                System.out.println();
            }
            System.out.println();
            System.out.println("СТАТИСТИКА ПО ОСТРОВУ");
            System.out.println();

            System.out.print("Животных на острове: ");
            System.out.println(countAnimalsEnd);

            System.out.print("Растений на острове: ");
            System.out.println(countPlants);


            System.out.print("Животных умерло от голода: ");
            System.out.println(Round.getInstance().liveTask.getAnimalsDiedByHungry());

            System.out.print("Животных съедено: ");
           System.out.println(Round.getInstance().liveTask.getAnimalsEaten());

            System.out.print("Детенышей родилось: ");
            System.out.println(Round.getInstance().liveTask.getBabies());


            System.out.println();
            System.out.println("-".repeat(30));
            System.out.println();
        }

        public static int getCurrentDay() {
            return currentDay;
        }
    }

