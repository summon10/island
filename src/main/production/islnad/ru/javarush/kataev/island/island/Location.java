package island;


import java.util.*;
import inhabitants.animals.Animal;
import inhabitants.inhabitant;
import inhabitants.plants.Plant;


public class Location {
        private final int row;
        private final int column;
        private final ArrayList<Animal> animals;
        private final ArrayList<Plant> plants;


        public Location(int row, int column) { // установить значение строки и столбца для местоположения
            this.row = row;
            this.column = column;

            animals = new ArrayList<Animal>();
            plants = new ArrayList<Plant>();
        }


        public void addAnimal(Animal animal) { // добавить животного в данное местоположение
            animal.setRow(row);
            animal.setColumn(column);

            animals.add(animal);
        }


        public void removeAnimal(Animal animal) { // удалить животное
            animals.remove(animal);
        }


        public void addPlant(Plant plant) { // добавить растение
            plant.setRow(row);
            plant.setColumn(column);
            plants.add(plant);
        }


        public void removePlant(Plant plant) { // удалить растение
            plants.remove(plant);
        }


        public ArrayList<Plant> getPlants() { // получить список растений
            return plants;
        }


        public ArrayList<Animal> getAnimals() { // получить список животных
            return animals;
        }


        public ArrayList<inhabitant> getInhabitants() {              // получить все формы жизни
            ArrayList<inhabitant> inhabitants = new ArrayList<>();
            inhabitants.addAll(animals);
            inhabitants.addAll(plants);
            return inhabitants;
        }
        public int getRow() {
            return row;
        }
        public int getColumn() {
            return column;
        }
    }

