package island;


import inhabitants.Inhabitant;
import inhabitants.animals.Animal;
import inhabitants.plants.Plant;

import java.util.ArrayList;


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


        public synchronized void addAnimal(Animal animal) { // добавить животного в данное местоположение
            animal.setRow(row);
            animal.setColumn(column);

            animals.add(animal);
        }


        public synchronized void removeAnimal(Animal animal) { // удалить животное
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


        public ArrayList<Inhabitant> getInhabitants() {              // получить все формы жизни
            ArrayList<Inhabitant> Inhabitants = new ArrayList<>();
            Inhabitants.addAll(animals);
            Inhabitants.addAll(plants);
            return Inhabitants;
        }
        public int getRow() {
            return row;
        }
        public int getColumn() {
            return column;
        }
    }

