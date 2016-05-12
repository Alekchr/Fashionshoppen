package source;

import services.ServerRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aleksander
 */
public class Customer extends User
{

    private String address = " ";
    private String phoneNr = " ";

    public Customer(String email, String password)
    {
        super(email, password);

    }

    ServerRequest SR = new ServerRequest();

    public Customer(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
        this.address = " ";
        this.phoneNr = " ";
    }

    private void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhoneNr(String phoneNr)
    {
        this.phoneNr = phoneNr;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhoneNr()
    {
        return phoneNr;
    }

    public void registerCustomer(User user)
    {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();

        SR.saveUser(firstName, lastName, email, password);

    }

    public Customer loginCustomer(User user)
    {
        Customer customer = null;
        try
        {
            ResultSet rs = SR.loginUser(user.getEmail(), user.getPassword());
            while (rs.next())
            {
                customer = new Customer(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"));
            }

            rs = SR.loginCustomer(user.getEmail());

            while (rs.next())
            {
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNr(rs.getString("phone"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return customer;
    }
}
