package java8.lambda;

/**
 * 员工类
 *
 * @Author: xjf
 * @Date: 2019/10/6 10:16
 */
public class Employee {

    private String name;
    private Integer age;
    private Double salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (age != null ? !age.equals(employee.age) : employee.age != null) return false;
        return salary != null ? salary.equals(employee.salary) : employee.salary == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
