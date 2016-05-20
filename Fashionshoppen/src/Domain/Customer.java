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

    Customer customer;
    private Address address;
    private String phoneNr;
    private Order order;

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
        public void registerUser(String firstName, String lastName, String email, String password)
    {
        
        String encryptedPass = encryptPassword(password);
        sf.registerUser(firstName, lastName, email, encryptedPass);
    }
    


    @Override
    public Customer loginUser(User user)     //Når en bruger logges ind gemmes alle deres oplysninger, 
                                             //så de kan bruges til orders/ændring af oplysninger
    {
        customer = new Customer("", "", "", "");
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

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
    
    

}
