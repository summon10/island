package inhabitants.animals;

import inhabitants.Inhabitant;
import island.Field;

import java.util.Random;

public class Animal extends Inhabitant implements Eatable, Movable, Reproducable {
    private final int step; // Скорость перемещения, не более чем, клеток за ход
    private final double maxHp; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    private double hp; // Количество здоровья животного
    private final double weight; // Вес животного в кг
    private final int maxPopulation; // Максимальное количество вида животного на 1 клетке
    private final String name; // Имя животного


    public Animal(int step, double maxHp, double weight, int maxPopulation, String name) {
        this.step = step;
        this.maxHp = maxHp;
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.name = name;
        this.hp = maxHp; //на старте максимальное кол-во здоровья

    }

    @Override
    public float getChanceToEat(String nameWantToEat, String wantToBeEaten) {
        return 0;
    }

    @Override
    public float eat(Inhabitant food) {
        return 0;
    }

    @Override
    public void move() {
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
    }

    @Override
    public void reproduce(Inhabitant inhabitant) {
        if (this.getClass() == inhabitant.getClass())
        {
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
