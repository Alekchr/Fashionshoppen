
package Domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aleksander
 */
public class Employee extends User
{
    
    public Employee(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
    }
    
    @Override
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
    @Override
    public void registerUser(User user){
    
    }
}
