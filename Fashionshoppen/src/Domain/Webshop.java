/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import products.Item;

import products.ProductCatalog;
import products.Product;
import java.util.ArrayList;

import java.security.MessageDigest;

import java.util.List;

import services.*;
import users.IUserManager;
import users.UserManager;

public final class Webshop
{

    private static Webshop instance = null;
    ProductCatalog catalog;
    Product product;
    IUserManager um;
    ServicesFacade sf;
    MessageDigest md;
    private int orderID;

    private Webshop()
    {

        catalog = new ProductCatalog();
        um = new UserManager();
        sf = new ServicesFacade();

    }

    public static Webshop getInstance()
    {

        if (instance == null)
        {
            instance = new Webshop();
        }

        return instance;
    }

    public ArrayList createProductsArray()
    {
        ArrayList products = catalog.showProducts();
        return products;

    }

    public List<Item> getShoppingBasketItems()
    {
        return um.getShoppingBasketItems();
    }

    public void createProduct(String name, String category, String gender, Double price)
    {
        sf.createProduct(name, category, gender, price);
    }

    public void deleteProduct(int productId)
    {
        sf.deleteProduct(productId);
    }

    public void editProductName(int productId, String productName)
    {
        sf.editProductName(productId, productName);
    }

    public void editProductCategory(int productId, String productCategory)
    {
        sf.editProductCategory(productId, productCategory);
    }

    public void editProductGender(int productId, String productGender)
    {
        sf.editProductGender(productId, productGender);
    }

    public void editProductPrice(int productId, Double price)
    {
        sf.editProductPrice(productId, price);
    }

    public void editProductPicture(int productId, String imagePath)
    {
        sf.editProductPicture(productId, imagePath);
    }

    public void browseCategory(String category, String name)
    {
        sf.browseCategory(category, name);
    }

    public void browseProductName(String name)
    {
        sf.browseProductName(name);
    }

    public void registerCustomer(String firstName, String lastName, String email, String password)
    {
        um.createUser(firstName, lastName, email, password);
    }

    public void loginUser(String email, String password)
    {
        um.loginUser(email, password);
    }

    public void displayProduct(Product product)
    {
        this.product = product;
    }



    public Product getProduct()
    {
        
        return product;
    }

    public void addItem(Product product, int amount, String size)
    {
        if (!um.isUserLoggedIn())
        {
            um.createGuestUser();

        }
        if (!um.userHasBasket())
        {
            um.createBasket(orderID++);
            um.addItem(product, amount, size);
        }
        else
        {
            um.addItem(product, amount, size);

            System.out.println("item was added to basket");
        }
    }

    public void storeOrder(int payment_option, String firstName, String lastName, String email, String streetName, 
            String houseNumber, String zipCode, String shippingCity) //Address skal have autoudfyld.
    {
        um.storeOrder(payment_option, firstName, lastName, email, streetName, houseNumber, zipCode, shippingCity);
                
    }
    
    public boolean userHasShoppingBasket(){
        return um.userHasBasket();
    }
    
    public void removeItem(Item item)
    {
    um.removeItem(item);
    }
}
