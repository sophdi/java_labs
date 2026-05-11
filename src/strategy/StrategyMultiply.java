package strategy;

public class StrategyMultiply implements Strategy {
    @Override
    public double execute(double a, double b) {
        return a * b;
    }
}