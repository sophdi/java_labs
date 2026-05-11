package strategy;

public class ContextStrategy implements Strategy {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double execute(double a, double b) {
        return strategy.execute(a, b);
    }
}