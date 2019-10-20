package java8.lambda;

/**
 * @Author: xjf
 * @Date: 2019/10/6 11:16
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
