package users;

import products.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import services.OrderStatus;

public class Customer extends User
{

    private Customer customer;

    private String phoneNr;

    public Customer(String email, String password)
    {
        super(email, password);
        orders = new HashMap();

    }

    public Customer(String firstName, String lastName, String email, String password)
    {
        super(firstName, lastName, email, password);
        orders = new HashMap();

    }



    public void setPhoneNr(String phoneNr)
    {
        this.phoneNr = phoneNr;
    }



    public String getPhoneNr()
    {
        return phoneNr;
    }

    public void registerUser(String firstName, String lastName, String email, String password)
    {

        sf.registerUser(firstName, lastName, email, password);
    }

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

    public Order findShoppingBasket()
    {
        for (Order order : orders.values())
        {
            if (order.getStatus() == OrderStatus.SHOPPING_BASKET)
            {
                return order;
            }

        }
        return null;
    }
    
    

}
