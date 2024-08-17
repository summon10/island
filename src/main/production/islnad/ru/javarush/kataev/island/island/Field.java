package island;

import inhabitants.animals.Animal;
import inhabitants.plants.Plant;
import java.util.*;

public class Field {

        private Location[][] locations; // Двумерный массив состоящий из локаций(ячеек)
        private final int numRows = 100; //default

        private final int numColumns = 20; //default
        private static volatile Field instance;

        private Field() { // для создания одного обьекта
        }

        public static Field getInstance() { // получение экземпляра класса
            if (instance == null) {
                synchronized (Field.class) {
                    if (instance == null) {
                        instance = new Field();
                    }
                }
            }
            return instance;
        }


        public void initializeLocations(int numRows, int numColumns) { // инициализация локаций (ячеек) на острове.
            locations = new Location[numRows][numColumns];
            for (int i = 0; i < locations.length; i++) {
                for (int j = 0; j < locations[i].length; j++) {
                    locations[i][j] = new Location(i, j);
                }
            }
        }

        public void initializeLocations() {
            locations = new Location[numRows][numColumns];
            for (int i = 0; i < locations.length; i++) {
                for (int j = 0; j < locations[i].length; j++) {
                    locations[i][j] = new Location(i, j);
                }
            }
        }

        public synchronized Location getLocation(int row, int column) {// получение локации (ячейки) по заданным координатам
            return locations[row][column];
        }


        public void addAnimal(Animal animal, int row, int column) { // добавить животного в указанную локацию
            Location location = getLocation(row, column);
            location.addAnimal(animal);
        }

        public void removeAnimal(Animal animal, int row, int column) {// удалить животного в указанную локацию
            Location location = getLocation(row, column);
            location.removeAnimal(animal);
        }

        public void addPlant(Plant plant, int row, int column) { // добавить растения в указанную локацию
            Location location = getLocation(row, column);
            location.addPlant(plant);
        }

        public void removePlant(Plant plant, int row, int column) { // удалить растения в указанную локацию
            Location location = getLocation(row, column);
            location.removePlant(plant);
        }

        public synchronized List<Animal> getAllAnimals() {  // получить список всех животных на острове
            ArrayList<Animal> allAnimals = new ArrayList<Animal>();
            for (Location[] row : locations) {
                for (Location location : row) {
                    allAnimals.addAll(location.getAnimals());
                }
            }
            return allAnimals;
        }

        public ArrayList<Plant> getAllPlants() {                // получить список всех растений на острове
            ArrayList<Plant> allPlants = new ArrayList<Plant>();
            for (Location[] row : locations) {
                for (Location location : row) {
                    allPlants.addAll(location.getPlants());
                }
            }
            return allPlants;
        }
        public int getNumRows() {
            return numRows;
        }
        public int getNumColumns() {
            return numColumns;
        }
    }
