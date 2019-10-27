package java8.optional;

/**
 * @Author: xjf
 * @Date: 2019/10/27 23:11
 */
public class Goddness {

    private String name;

    public Goddness() {
    }

    public Goddness(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goddness{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
