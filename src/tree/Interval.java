package tree;

/**
 * Created by poorvank.b on 19/11/17.
 */
public class Interval {

    public int low;
    public int high;

    public Interval(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "low=" + low +
                ", high=" + high +
                '}';
    }
}
