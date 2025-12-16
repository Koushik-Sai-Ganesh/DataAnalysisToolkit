import java.util.*;

public class StatAnalyzer implements Analyzer {

    public double sum(List<Double> d) {
        double s = 0;
        for (double x : d) s += x;
        return s;
    }

    public double average(List<Double> d) {
        return sum(d) / d.size();
    }

    public double correlation(List<Double> x, List<Double> y) {
        double mx = average(x), my = average(y);
        double num = 0, dx = 0, dy = 0;

        for (int i = 0; i < x.size(); i++) {
            num += (x.get(i) - mx) * (y.get(i) - my);
            dx += Math.pow(x.get(i) - mx, 2);
            dy += Math.pow(y.get(i) - my, 2);
        }
        return num / Math.sqrt(dx * dy);
    }

    public double slope(List<Double> x, List<Double> y) {
        double mx = average(x), my = average(y);
        double num = 0, den = 0;

        for (int i = 0; i < x.size(); i++) {
            num += (x.get(i) - mx) * (y.get(i) - my);
            den += Math.pow(x.get(i) - mx, 2);
        }
        return num / den;
    }

    public List<Double> filterBelow(List<Double> d, double value) {
        List<Double> res = new ArrayList<>();
        for (double x : d)
            if (x < value) res.add(x);
        return res;
    }
}
