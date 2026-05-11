package flyweight;

import java.util.HashMap;
import java.util.Map;

public class FactoryShare {

    private static final Map<String, Shape> shares = new HashMap<>();

    public Shape getShape(String nameShape) {

        Shape shape = shares.get(nameShape);

        if (shape == null) {
            switch (nameShape) {
                case "точка":
                    shape = new Point();
                    break;
                case "коло":
                    shape = new Circle();
                    break;
                case "квадрат":
                    shape = new Square();
                    break;
            }
            shares.put(nameShape, shape);
        }

        return shape;
    }
}