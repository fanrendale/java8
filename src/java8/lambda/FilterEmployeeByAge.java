package java8.lambda;

/**
 * @Author: xjf
 * @Date: 2019/10/6 11:12
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
