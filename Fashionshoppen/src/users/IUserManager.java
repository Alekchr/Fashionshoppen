package users;

import products.Product;
import products.Item;

import java.util.List;

public interface IUserManager
{

    
    void createGuestUser();

    void createUser(String firstName, String lastName, String email, String password);

    User getOnlineUser();

    boolean loginUser(String email, String password);

    boolean isUserLoggedIn();

    boolean userHasBasket();

    void logout();

    void createBasket(int orderID);

    void addItem(Product product, int quantity, String size);

    void removeItem(Item item);

    void changeAmount(Item item, int amount);

    List<Item> getShoppingBasketItems();
    
    void storeOrder(String payment_option, String firstName, String lastName, String email, String streetName, 
            String houseNumber, String zipCode, String shippingCity);

}
