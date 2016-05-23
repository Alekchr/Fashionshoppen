/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import products.Item;
import products.Order;
import users.User;
import users.Address;
import users.Customer;
import users.Employee;
import products.ProductCatalog;
import products.Product;
import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.*;
import users.UserManager;


public final class Webshop
{

    private static Webshop instance = null;
    ProductCatalog catalog;
    Customer customer;
    Employee employee;
    Item item;
    Product product;
    User user;
    UserManager um;
    ServicesFacade sf;
    MessageDigest md;
    private int orderID;
    
    public Webshop()
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
    

    public ArrayList createProductsArray(){
        ArrayList products = catalog.showProducts();
        return products;
        
    }
    
    public List<Item> getShoppingBasketItems() {
        return um.getShoppingBasketItems();
    }
        
    
    
       public void createProduct(String name, String category, String gender, Double price){
        sf.createProduct(name, category, gender, price);
    }
       
       public void deleteProduct(int productId){
        sf.deleteProduct(productId);
    }
    
        public void editProductName(int productId, String productName){
        sf.editProductName(productId, productName);
    }
    
    public void editProductCategory(int productId, String productCategory){
        sf.editProductCategory(productId, productCategory);
    }
    
    public void editProductGender(int productId, String productGender){
        sf.editProductGender(productId, productGender);
    }
    
    public void editProductPrice(int productId, Double price){
        sf.editProductPrice(productId, price);
    }
    
    public void editProductPicture(int productId, String imagePath){
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
        um.checkUserType(email, password);
    }

    
    public void displayProduct(Product product){
        this.product = product;
    }
    
    public void createProduct(Product product){
        
    }

    public Product getProduct()
    {
        System.out.println(product);
        return product;
    }   


    public void addItem(int amount, String size)
    {
        if (!um.isUserLoggedIn())
        {
        um.createGuestUser();
                
        }
        if (!um.userHasBasket()) {
            um.createOrder(orderID++);

        }
        else
        {
            um.addItem(getProduct(), amount, size);
            
            System.out.println("item was added to basket");
        }
    }

    public void storeOrder(String payment_option, Address shippingAddress) //Address skal have autoudfyld.
    {
        StringBuilder sb = new StringBuilder();
        sb.append(um.getShoppingBasket().getItems().toString().split(";"));
        System.out.println(sb);
        um.getShoppingBasket().setPayment_option(payment_option);
        um.getShoppingBasket().setShippingAddress(shippingAddress);
        //customer.getOrder().setPrice(price);
    }
}
