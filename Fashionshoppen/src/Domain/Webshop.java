/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

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
    UserManager um;

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

    public void browseCategory(String category, String name)
    {
        sf.browseCategory(category, name);
    }

    public void browseProductName(String name)
    {
        sf.browseProductName(name);
    }

    public User loginUser(User user)
    {
        return um.loginUser(user);

    }

    public void registerCustomer(String firstName, String lastName, String email, String password)
    {

        customer = new Customer(firstName, lastName, email, password);
        um.registerUser(customer);
    }

    public void checkUserType(String email)
    {
        customer = new Customer(email, password);
        Customer returnedCustomer = (Customer) webshop.loginUser(customer);
        if (returnedCustomer == null)
        {
            employee = new Customer(email, password);
            Customer returnedCustomer = (Employee) webshop.loginUser(customer);
        }
        else
        {
            System.out.println("" + returnedCustomer.getAddress());
        }

    }
