package Domain;

import services.ServerRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import services.ServicesFacade;

/**
 *
 * @author aleksander
 */
public class Customer extends User
{

    private Address address;
    private String phoneNr;

    public Customer(String email, String password)
    {
        super(email, password);

    }

    public Customer(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);

    }

    private void setAddress(Address address)
    {
        this.address = address;
    }

    public void setPhoneNr(String phoneNr)
    {
        this.phoneNr = phoneNr;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getPhoneNr()
    {
        return phoneNr;
    }

    @Override
    public void registerUser(User user)
    {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();

        sf.registerUser(firstName, lastName, email, password);

    }

    @Override
    public Customer loginUser(User user)
    {
        Customer customer = new Customer("", "", "", "");
        try
        {

            ResultSet rs = sf.loginUser(user.getEmail(), user.getPassword());
            while (rs.next())
            {
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setUser_id(rs.getInt("user_id"));
            }

            rs = sf.loginCustomer(customer.getEmail());

            while (rs.next())
            {
                customer.setPhoneNr(rs.getString("phone"));
            }

            rs = sf.getCustomerAddress(customer.getEmail());

            while (rs.next())
            {
                customer.setAddress(new Address(rs.getInt("user_id"), rs.getString("streetname"), rs.getString("housenumber"),
                        rs.getString("zipcode"), rs.getString("city")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return customer;
    }

}
