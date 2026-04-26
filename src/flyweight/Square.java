package flyweight;

public class Square implements Shape {

    int a = 20;

    @Override
    public void draw(int x, int y) {
        System.out.println("x: " + x + ", y: " + y + ", a: " + a);
    }
}
