package inhabitants;

public abstract class Inhabitant {
    private int row;
    protected double weight; // Вес животного в кг
    protected String name; // Имя животного
    private int column;
    protected int maxPopulation; // Максимальное количество вида животного на 1 клетке

    public Inhabitant(double weight, String name, int maxPopulation) {
        this.weight = weight;
        this.name = name;
        this.maxPopulation = maxPopulation;
    }
    public abstract String getPicture();
    public int getMaxPopulation() {
        return maxPopulation;
    }

    public double getWeight() {
        return weight;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }
}
