/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import products.Product;
import products.Item;
import products.Order;
import java.util.List;

/**
 *
 * @author Morten
 */
public interface IUserManager {

    void createGuestUser();

    void createUser(String firstName, String lastName, String email, String password);

    User getOnlineUser();
    
    boolean checkUserType(String email, String password);
    
    boolean isUserLoggedIn();
    
    boolean userHasBasket();
    
    void logout();
    
    void createOrder(int orderID);

    void addItem(Product product, int quantity, String size);
    
    void removeItem(Item item);

    void changeAmount(Item item, int amount);
    
    List<Item> getShoppingBasketItems();


    

    
    
}
