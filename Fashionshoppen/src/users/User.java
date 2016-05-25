package users;

import java.util.List;
import java.util.Map;

import products.Item;
import products.Order;
import products.Product;
import services.*;

public abstract class User
{

    private int user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    int accesslvl;
    protected Address address;
    ServicesFacade sf = new ServicesFacade();
    protected int access;

    protected Map<Integer, Order> orders;

    public User(String firstName, String lastName, String email, String password)
    {
        this.user_id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;

    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void createBasket(int orderID)
    {
        double shippingCharge = 25.0;
        orders.put(orderID, new Order(orderID, address, OrderStatus.SHOPPING_BASKET));
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

    public List<Item> getShoppingBasketItems()
    {
        return findShoppingBasket().getItems();
    }


    public void addItem(Product product, int quantity, String size)
    {
        findShoppingBasket().addItem(product, quantity, size);
    }

    public void changeAmount(Item item, int amount)
    {
        findShoppingBasket().changeAmount(item, amount);
    }

    public void removeItem(Item item)
    {
        Order shoppingBasket = this.findShoppingBasket();
        shoppingBasket.removeItem(item);
    }

    public void inBasket(Order o)
    {
        orders.put(o.getOrder_id(), o);
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public int getAccess()
    {
        return access;
    }

    public void setAccess(int access)
    {
        this.access = access;
    }
    
    
    

}
