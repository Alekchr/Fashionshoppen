package users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import static services.AccessLevel.CUSTOMER_ACCESS;
import services.ServicesFacade;

public class Customer extends User
{

    private Customer customer;

    private String phoneNr;

    public Customer(String email, String password)
    {
        super(email, password);
        orders = new HashMap();
        access = CUSTOMER_ACCESS;

    }

    public Customer(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
        orders = new HashMap();
        access = CUSTOMER_ACCESS;

    }

    public void setPhoneNr(String phoneNr)
    {
        this.phoneNr = phoneNr;
    }



    public void registerUser(String firstName, String lastName, String email, String password)
    {
        ServicesFacade.getInstance().registerUser(firstName, lastName, email, password);
    }

    public Customer loginUser(User user)     //Når en bruger logges ind gemmes alle deres oplysninger, 
    //så de kan bruges til orders/ændring af oplysninger
    {
        customer = new Customer("", "", "", "");
        try
        {

            ResultSet rs = ServicesFacade.getInstance().loginUser(user.getEmail(), user.getPassword());
            while (rs.next())
            {
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setUser_id(rs.getInt("user_id"));
            }

            rs = ServicesFacade.getInstance().loginCustomer(customer.getEmail());

            while (rs.next())
            {
                customer.setPhoneNr(rs.getString("phone"));
            }

            rs = ServicesFacade.getInstance().getCustomerAddress(customer.getEmail());

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
