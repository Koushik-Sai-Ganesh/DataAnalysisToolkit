import java.util.*;

public interface Analyzer {
    double sum(List<Double> d);
    double average(List<Double> d);
    double correlation(List<Double> a, List<Double> b);
    double slope(List<Double> a, List<Double> b);
    List<Double> filterBelow(List<Double> d, double value);
}
