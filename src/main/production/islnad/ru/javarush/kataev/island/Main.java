import island.Field;
import live.Round;

public class Main {
    public static void main(String[] args) {
        Field.getInstance().initializeLocations();
        Round.getInstance().createAndRunIsland(40,80);
    }
}
