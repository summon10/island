package inhabitants.animals;

import java.lang.reflect.InvocationTargetException;

public interface Reproducible {

    void reproduce (Animal animal) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

}
