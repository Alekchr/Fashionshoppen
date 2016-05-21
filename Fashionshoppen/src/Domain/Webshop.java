/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.*;

/**
 *
 * @author jonaspedersen
 */
public final class Webshop
{

    private static Webshop instance = null;
    Catalog catalog;
    Customer customer;
    Employee employee;
    Item item;
    Product product;
    User user;
    ServicesFacade sf;
    Order order;
    MessageDigest md;

    public Webshop()
    {

        catalog = new Catalog();
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
    
        public ArrayList createOrdersArray(){
        ArrayList orderProducts = new ArrayList();
        for(int i=0;i<customer.getOrder().getItems().size();i++){
        orderProducts.add(customer.getOrder().getItems().get(i).getProduct());
        }
        return orderProducts;
        
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
        customer.registerUser(firstName, lastName, email, password);
    }

    public User checkUserType(String email, String password)
    {
        
        
        customer = new Customer(email, password);
        Customer returnedCustomer = (Customer) customer.checkUserType(customer);
        if (returnedCustomer == null)
        {
            employee = new Employee(email, password);
            Employee returnedEmployee = (Employee) employee.checkUserType(employee);
            return returnedEmployee;
        }
        else
        {
            return returnedCustomer;
        }

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

    public void addItem(Product product, int amount)
    {
        if (customer == null)
        {
            customer = new Customer(" ", " ");
            System.out.println("created random GUEST customer");
        }
        
        if (customer.getOrder() == null)
        {
            customer.setOrder(new Order(customer.getUser_id(), customer.getAddress(), new Item(product, amount)));
            System.out.println("Order was created and item added");
        }
        else
        {
            customer.getOrder().addItem(new Item(product, amount));
            System.out.println("item was added to basket");
        }
    }

    public void storeOrder(String payment_option, Address shippingAddress) //Address skal have autoudfyld.
    {
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getOrder().getItems().toString().split(";"));
        System.out.println(sb);
        customer.getOrder().setPayment_option(payment_option);
        customer.getOrder().setShippingAddress(shippingAddress);
        //customer.getOrder().setPrice(price);
    }
}
