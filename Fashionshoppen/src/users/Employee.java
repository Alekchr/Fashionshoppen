package users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends User
{

    public Employee(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
    }

    public Employee(String email, String password)
    {
        super(email, password);

    }

    public Employee loginUser(User user)
    {
        Employee employee = null;
        try
        {
            ResultSet rs = sf.loginUser(user.getEmail(), user.getPassword());
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

    public void registerUser(String firstName, String lastName, String email, String password)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
