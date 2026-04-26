package flyweight;

public class Circle implements Shape {

    int r = 10;

    @Override
    public void draw(int x, int y) {
        System.out.println("x: " + x + ", y: " + y + ", r: " + r);
    }
}
