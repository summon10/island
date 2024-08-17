package inhabitants.animals;

import inhabitants.Inhabitant;
import inhabitants.plants.Plant;
import island.Field;
import island.Location;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Inhabitant implements Eatable, Movable, Reproducible {
    private final int step; // Скорость перемещения, не более чем, клеток за ход

    public double getMaxHp() {
        return maxHp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    private final double maxHp; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    private double hp; // Количество здоровья животного

    public Animal(int step, double maxHp, double weight, int maxPopulation, String name) {
        super(weight, name, maxPopulation);
        this.step = step;
        this.maxHp = maxHp;
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.name = name;
        this.hp = maxHp; //на старте максимальное кол-во здоровья

    }

    @Override
    public boolean eat(Inhabitant food) {
        double chanceToEat;

        boolean animalEatFood;


        String foodName = food.getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + food.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения
            Location location = Field.getInstance().getLocation(food.getRow(), food.getColumn()); // Животное/растение удаляется из списка обиталей локации после съедения
            if (food instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    Field.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) food;
                if (location.getPlants().size() > 0) {
                    Field.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }



    @Override
    public int move() {
        Random random = new Random();
        int randomCells = random.nextInt(this.step + 1);
        // Генерируем случайное направление (вверх, вниз, влево или вправо)
        int direction = random.nextInt(4);
        // Вычисляем новые координаты в зависимости от направления
        int newRow = getRow();
        int newColumn = getColumn();
        switch (direction) {
            case 0 -> // Вверх
                    newRow -= randomCells;
            case 1 -> // Вниз
                    newRow += randomCells;
            case 2 -> // Влево
                    newColumn -= randomCells;
            case 3 -> // Вправо
                    newColumn += randomCells;
        }
        // Проверяем, чтобы новые координаты не выходили за границы поля
        while (newRow < 0 || newRow >= Field.getInstance().getNumRows() || newColumn < 0 || newColumn >= Field.getInstance().getNumColumns()) {
            randomCells = random.nextInt(this.step + 1);
            direction = random.nextInt(4);

            newRow = getRow();
            newColumn = getColumn();
            switch (direction) {
                case 0 -> // Вверх
                        newRow -= randomCells;
                case 1 -> // Вниз
                        newRow += randomCells;
                case 2 -> // Влево
                        newColumn -= randomCells;
                case 3 -> // Вправо
                        newColumn += randomCells;
            }
        }

        Field.getInstance().removeAnimal(this, getRow(), getColumn());
        // Обновляем новые координаты
        setRow(newRow);
        setColumn(newColumn);
       Field.getInstance().addAnimal(this, newRow, newColumn);
       return direction;
    }

    @Override
    public void reproduce(Animal partner)
    {

        if (this.getName().equals(partner.getName()))
        {
            Location location = Field.getInstance().getLocation(partner.getRow(), partner.getColumn());

            try {
                Constructor<Animal> newAnimal = (Constructor<Animal>) Class.forName(partner.getName()).getConstructor();
                Field.getInstance().addAnimal(newAnimal.newInstance(), location.getRow(), location.getColumn());
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getStep() {
        return step;
    }
}
