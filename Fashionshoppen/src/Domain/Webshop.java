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
import services.ServicesFacade;
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
    Login login;
    Product product;
    User user;
    ServicesFacade sf;
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
    

    public ArrayList showProducts(){
        ArrayList products = catalog.showProducts();
        return products;
        
    }
    
        public void editProductName(int productId, String productName){
        sf.editProductName(productId, productName);
    }
    
    public void editProductCategory(int productId, String productCategory){
        sf.editProductName(productId, productCategory);
    }
    
    public void editProductGender(int productId, String productGender){
        sf.editProductName(productId, productGender);
    }
    
    public void editProductPrice(int productId, Double price){
        sf.editProductPrice(productId, price);
    }
    
    public void editProductPicture(int productId, String imagePath){
        sf.editProductName(productId, imagePath);
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

        String encryptedPass = encryptPassword(password);
        customer = new Customer(firstName, lastName, email, encryptedPass);
        customer.registerUser(customer);
    }

    public User checkUserType(String email, String password)
    {
        Customer returnedCustomer = null;
        Employee returnedEmployee = null;
        String encryptedPass = encryptPassword(password);
        customer = new Customer(email, encryptedPass);
        returnedCustomer = (Customer) customer.loginUser(customer);

        if (returnedCustomer == null)
        {
            employee = new Employee(email, encryptedPass);
            returnedEmployee = (Employee) employee.loginUser(employee);
            return returnedEmployee;
        }
        else
        {
            return returnedCustomer;
        }

    }
    public String encryptPassword(String password)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            md = MessageDigest.getInstance("MD5");
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
            Logger.getLogger(Webshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
}
