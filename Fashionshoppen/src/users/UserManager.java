package users;

import products.Item;
import products.Order;
import products.Product;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static services.OrderStatus.NOT_CONFIRMED;

public class UserManager implements IUserManager
{

    private User onlineUser;
    MessageDigest md;

    public UserManager()
    {

    }

    public boolean checkUserType(String email, String password)
    {

        String encryptedPass = encryptPassword(password);
        Customer customer = new Customer(email, encryptedPass);

        Customer returnedCustomer = (Customer) customer.loginUser(customer);
        if (returnedCustomer != null)
        {
            setOnlineUser(returnedCustomer);
            if (this.userHasBasket())
            {
                onlineUser.inBasket(customer.findShoppingBasket());
            }

        }
        else
        {
            Employee employee = new Employee(email, encryptedPass);
            Employee returnedEmployee = (Employee) employee.loginUser(employee);
            setOnlineUser(returnedEmployee);
        }
        if (!isUserLoggedIn())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    private void setOnlineUser(User user)
    {
        onlineUser = user;
    }

    @Override
    public User getOnlineUser()
    {
        return onlineUser;
    }

    @Override
    public boolean isUserLoggedIn()
    {
        return onlineUser != null;
    }

    @Override
    public void logout()
    {
        setOnlineUser(null);
    }

    @Override
    public void createUser(String firstName, String lastName, String email, String password)
    {

        String encryptedPass = encryptPassword(password);
        Customer customer = new Customer(firstName, lastName, email, encryptedPass);
        customer.registerUser(firstName, lastName, email, encryptedPass);

    }

    public String encryptPassword(String password)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            md.digest(passBytes);
            byte[] digest = md.digest(passBytes);

            for (int i = 0; i < digest.length; i++)
            {
                sb.append(Integer.toHexString(0xff & digest[i]));
            }

        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    @Override
    public void createBasket(int orderID)
    {
        onlineUser.createBasket(orderID);
    }

    @Override
    public boolean userHasBasket()
    {
        if (!isUserLoggedIn())
        {
            return false;
        }
        return onlineUser.findShoppingBasket() != null;
    }

    @Override
    public List<Item> getShoppingBasketItems()
    {
        return onlineUser.getShoppingBasketItems();
    }

    public Order getShoppingBasket()
    {
        return onlineUser.findShoppingBasket();
    }

    @Override
    public void addItem(Product product, int quantity, String size)
    {
        onlineUser.addItem(product, quantity, size);
    }

    public void changeAmount(Item item, int amount)
    {
        onlineUser.changeAmount(item, amount);

    }

    @Override
    public void removeItem(Item item)
    {
        onlineUser.removeItem(item);
    }

    private String createGuestEmail()
    {
        Random generator = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("guest");
        sb.append(generator.nextInt(500));
        sb.append("@fashionshoppen.com");
        return sb.toString();
    }

    @Override
    public void createGuestUser()
    {
        String email = createGuestEmail();

        User guestUser = new Customer(email, "");
        setOnlineUser(guestUser);
    }
    
    public void storeOrder(int payment_option, String firstName, String lastName, String email, String streetName, 
            String houseNumber, String zipcode, String shippingCity) //Address skal have autoudfyld.
    {
        StringBuilder sb = new StringBuilder();
        sb.append(onlineUser.getShoppingBasketItems().toString().split(";"));
        onlineUser.findShoppingBasket().setPayment_option(payment_option);
        onlineUser.setFirstName(firstName); onlineUser.setLastName(lastName); onlineUser.setEmail(email);
        onlineUser.setAddress(new Address(onlineUser.getUser_id(), streetName, houseNumber, zipcode, shippingCity));
        onlineUser.findShoppingBasket().setShippingAddress(onlineUser.getAddress());
        onlineUser.findShoppingBasket().setStatus(NOT_CONFIRMED);
        
    }

}
