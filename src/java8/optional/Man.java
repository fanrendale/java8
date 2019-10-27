package java8.optional;

/**
 * @Author: xjf
 * @Date: 2019/10/27 23:10
 */
public class Man {

    private Goddness goddness;

    public Man() {
    }

    public Man(Goddness goddness) {
        this.goddness = goddness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "goddness=" + goddness +
                '}';
    }

    public Goddness getGoddness() {
        return goddness;
    }

    public void setGoddness(Goddness goddness) {
        this.goddness = goddness;
    }
}
