package users;

import java.sql.ResultSet;
import java.sql.SQLException;
import static services.AccessLevel.EMPLOYEE_ACCESS;
import services.ServicesFacade;

public class Employee extends User
{

    public Employee(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
        access = EMPLOYEE_ACCESS;
    }

    public Employee(String email, String password)
    {
        super(email, password);
        access = EMPLOYEE_ACCESS;

    }

    public Employee loginUser(User user)
    {
        Employee employee = null;
        try
        {
            ResultSet rs = ServicesFacade.getInstance().loginUser(user.getEmail(), user.getPassword());
            while (rs.next())
            {
                employee = new Employee(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"));
                employee.setUser_id(rs.getInt("user_id"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return employee;
    }

    
}
