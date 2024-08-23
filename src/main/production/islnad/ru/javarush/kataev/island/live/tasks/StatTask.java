package live.tasks;

import inhabitants.animals.Animal;
import island.Field;
import live.Round;

import java.util.List;

public class StatTask implements Runnable {
        private boolean isTimeOver;
        private int countAnimalsEnd;
        private int countPlants;
        private static int currentDay = 0;
        private List<Animal> allAnimals;

        public StatTask() {

      }
        @Override
        public void run() {
            long timeNow = Round.getInstance().getTimeNow();
            isTimeOver = checkTime(timeNow);
            countAnimalsEnd = Field.getInstance().getAllAnimals().size(); // кол-во животных на острове
            allAnimals = Field.getInstance().getAllAnimals();

            countPlants = Field.getInstance().getAllPlants().size();
            printStats();

            if (isTimeOver) {
               Round.getInstance().getExecutorService().shutdown();
                System.exit(0);
            }

        }
        private void printAnimals ()
        {
            System.out.println("Кабан \uD83D\uDC17 " + allAnimals.stream().filter(c -> c.getName().equals("boar")).toList().size());
            System.out.println("Буйвол \uD83D\uDC03 " + allAnimals.stream().filter(c -> c.getName().equals("buffalo")).toList().size());
            System.out.println("Гусеницы \uD83D\uDC1B " + allAnimals.stream().filter(c -> c.getName().equals("caterpillar")).toList().size());
            System.out.println("Олени \uD83E\uDD8C " + allAnimals.stream().filter(c -> c.getName().equals("deer")).toList().size());
            System.out.println("Утки \uD83E\uDD86 " + allAnimals.stream().filter(c -> c.getName().equals("duck")).toList().size());
            System.out.println("Козлы \uD83D\uDC10 " + allAnimals.stream().filter(c -> c.getName().equals("goat")).toList().size());
            System.out.println("Лошади \uD83D\uDC0E " + allAnimals.stream().filter(c -> c.getName().equals("horse")).toList().size());
            System.out.println("Мыши \uD83D\uDC01 " + allAnimals.stream().filter(c -> c.getName().equals("mouse")).toList().size());
            System.out.println("Кролики \uD83D\uDC07 " + allAnimals.stream().filter(c -> c.getName().equals("rabbit")).toList().size());
            System.out.println("Овцы \uD83D\uDC11 " + allAnimals.stream().filter(c -> c.getName().equals("sheep")).toList().size());
            System.out.println("Медведи \uD83D\uDC3B " + allAnimals.stream().filter(c -> c.getName().equals("bear")).toList().size());
            System.out.println("Орлы \uD83E\uDD85 " + allAnimals.stream().filter(c -> c.getName().equals("eagle")).toList().size());
            System.out.println("Лисы \uD83E\uDD85 " + allAnimals.stream().filter(c -> c.getName().equals("fox")).toList().size());
            System.out.println("Змеи \uD83D\uDC0D " + allAnimals.stream().filter(c -> c.getName().equals("snake")).toList().size());
            System.out.println("Волки \uD83D\uDC3A " + allAnimals.stream().filter(c -> c.getName().equals("wolf")).toList().size());


        }


        private boolean checkTime(long timeNow) {
            return timeNow / 60 >= 5;
        }

        private void printStats() {  //   вывод статистику моделирования
            System.out.print("\033[H\033[J");
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
            printAnimals();


            System.out.println();
            System.out.println("-".repeat(30));
            System.out.println();
        }

        public static int getCurrentDay() {
            return currentDay;
        }
    }

