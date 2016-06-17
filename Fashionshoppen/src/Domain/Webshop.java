/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import products.Item;
import products.Order;
import users.Address;

import products.ProductCatalog;
import products.Product;
import java.util.ArrayList;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javafx.scene.control.CheckBox;

import services.*;
import users.IUserManager;
import users.UserManager;

public final class Webshop {

    private static Webshop instance = null;
    private final ProductCatalog catalog;
    private final IUserManager um;
    //private Product product;
    private MessageDigest md;
    private int orderID;

    private Webshop()
    {

        catalog = new ProductCatalog();
        um = new UserManager();

    }

    public static Webshop getInstance()
    {

        if (instance == null) {
            instance = new Webshop();
        }

        return instance;
    }
    
    public void showProducts(){
        catalog.showProducts();
    }

    public ArrayList createProductsArray()
    {
        ArrayList products = catalog.showProducts();
        return products;

    }

    public Address selectAddressFromId(int userId)
    {
        ResultSet rs = ServicesFacade.getInstance().selectAddressFromId(userId);
        Address addr = new Address(0, "dummy", "dummy", "dummy", "dummy");
        try {
            while (rs.next()) {
                addr.setCustomer_id(rs.getInt("user_id"));
                addr.setStreetName(rs.getString("streetname"));
                addr.setHouseNumber(rs.getString("housenumber"));
                addr.setZipCode(rs.getString("zipcode"));
                addr.setCity(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public String getCustomerNameFromId(int userId)
    {
        ResultSet rs = ServicesFacade.getInstance().getCustomerNameFromId(userId);
        String customerName = "";
        try {
            rs.next();
            customerName = rs.getString("firstname") + " " + rs.getString("lastname");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerName;
    }

    public ArrayList createOrdersArray()
    {
        ResultSet rs = ServicesFacade.getInstance().getOrders();
        ArrayList<Order> orders = new ArrayList();
        try {
            while (rs.next()) {
                Address addr = selectAddressFromId(rs.getInt("user_id"));
                Order newOrder = new Order(rs.getInt("user_id"), addr, rs.getString("status"));
                newOrder.setOrder_id(rs.getInt("order_id"));
                newOrder.setFinalPrice(rs.getDouble("finalprice"));
                newOrder.setStatus(rs.getString("status"));
                newOrder.setCustomer_id(rs.getInt("user_id"));
                newOrder.setOrder_date(rs.getDate("order_date").toString());
                orders.add(newOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;

    }

    private ArrayList filter(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        return catalog.filter(genderBoxes, categoryBoxes, inputString);
    }

    public ArrayList getProductName(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductName(returnedProducts);
    }

    public ArrayList getProductPrice(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductPrice(returnedProducts);
    }

    public ArrayList getProductDescription(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductName(returnedProducts);
    }

    public ArrayList getProductImage(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductImage(returnedProducts);
    }

    public ArrayList getProductCategory(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductCategory(returnedProducts);
    }

    public ArrayList imagePathToReturn(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.imagePathToReturn(returnedProducts);
    }

    public ArrayList getProductGender(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductGender(returnedProducts);
    }

    public ArrayList getProductId(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        ArrayList returnedProducts = filter(genderBoxes, categoryBoxes, inputString);
        return catalog.getProductId(returnedProducts);
    }

    public List<Item> getShoppingBasketItems()
    {
        return um.getShoppingBasketItems();
    }

    public void createProduct(String name, String category, String gender, Double price, String description, String imagePath)
    {
        ServicesFacade.getInstance().createProduct(name, category, gender, price, description, imagePath);
    }

    public void deleteProduct(int productId)
    {
        ServicesFacade.getInstance().deleteProduct(productId);
    }

    public void editProductDescription(int productId, String description)
    {
        ServicesFacade.getInstance().editProductDescription(productId, description);
    }

    public void editProductName(int productId, String productName)
    {
        ServicesFacade.getInstance().editProductName(productId, productName);
    }

    public void editProductCategory(int productId, String productCategory)
    {
        ServicesFacade.getInstance().editProductCategory(productId, productCategory);
    }

    public void editProductGender(int productId, String productGender)
    {
        ServicesFacade.getInstance().editProductGender(productId, productGender);
    }

    public void editProductPrice(int productId, Double price)
    {
        ServicesFacade.getInstance().editProductPrice(productId, price);
    }

    public void editProductPicture(int productId, String imagePath)
    {
        ServicesFacade.getInstance().editProductPicture(productId, imagePath);
    }

    public void browseCategory(String category, String name)
    {
        ServicesFacade.getInstance().browseCategory(category, name);
    }

    public void browseProductName(String name)
    {
        ServicesFacade.getInstance().browseProductName(name);
    }

    public void registerCustomer(String firstName, String lastName, String email, String password)
    {
        um.createUser(firstName, lastName, email, password);
    }

    public void loginUser(String email, String password)
    {
        um.loginUser(email, password);
    }

    public void addItem(String productName, String gender, String category, double productPrice, String description, String imagePath, int amount, String size)
    {
        Product product = new Product(productName, gender, category, productPrice, description, imagePath);
        System.out.println(product.getName());
        if (!um.isUserLoggedIn()) {
            um.createGuestUser();

        }

        if (!um.userHasBasket()) {
            um.createBasket(orderID++);
            um.addItem(product, amount, size);
        } else {
            um.addItem(product, amount, size);
        }
    }

    public void editOrderStatus(int orderId, String status)
    {
        ServicesFacade.getInstance().editOrderStatus(orderId, status);
    }

    public void storeOrder(String payment_option, String firstName, String lastName, String email, String streetName,
            String houseNumber, String zipCode, String shippingCity) //Address skal have autoudfyld.
    {
        um.storeOrder(payment_option, firstName, lastName, email, streetName, houseNumber, zipCode, shippingCity);

    }

    public boolean userHasShoppingBasket()
    {
        return um.userHasBasket();
    }

    public void removeItem(Item item)
    {
        um.removeItem(item);
    }
}
