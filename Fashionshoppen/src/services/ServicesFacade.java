/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import products.Order;
import java.sql.ResultSet;


public final class ServicesFacade {

    private static ServicesFacade instance = null;
    public ServerRequest sr;
    

    private ServicesFacade()
    {
        sr = new ServerRequest();
    }

    public static ServicesFacade getInstance()
    {

        if (instance == null) {
            instance = new ServicesFacade();
        }

        return instance;
    }

    public ResultSet getProducts()
    {
        ResultSet rs = sr.getProducts();
        return rs;
    }

    public void createProduct(String name, String category, String gender, Double price, String description, String imagePath)
    {
        sr.createProduct(name, category, gender, price, description, imagePath);
    }
    
    public void deleteProduct(int productId){
        sr.deleteProduct(productId);
    }

    public void browseProductName(String name)
    {
        sr.browseProductName(name);
    }

    public void browseCategory(String category, String name)
    {
        sr.browseCategory(category, name);
    }

    public ResultSet loginUser(String email, String password)
    {
        return sr.loginUser(email, password);

    }

    public void registerUser(String firstName, String lastName, String email, String password)
    {
        sr.registerUser(firstName, lastName, email, password);
    }

    public ResultSet loginCustomer(String email)
    {
        return sr.checkLoginType(email);
    }

    public ResultSet getCustomerAddress(String email)
    {
        return sr.findCustomerAddress(email);
    }

    public void editProductDescription(int productId, String description){
        sr.editProductDescription(productId, description);
    }
            
    public void editProductName(int productId, String productName)
    {
        sr.editProductName(productId, productName);
    }

    public void editProductCategory(int productId, String productCategory)
    {
        sr.editProductCategory(productId, productCategory);
    }

    public void editProductGender(int productId, String productGender)
    {
        sr.editProductGender(productId, productGender);
    }

    public void editProductPrice(int productId, Double price)
    {
        sr.editProductPrice(productId, price);
    }

    public void editProductPicture(int productId, String imagePath)
    {
        sr.editProductPicture(productId, imagePath);
    }
    
    public void editOrderStatus(int orderId, String status){
        sr.editOrderStatus(orderId, status);
    }
    
    public void storeOrder(Order order, int customer_id){
        sr.storeOrder(order, customer_id);
    }
    
    public ResultSet getOrders(){
        return sr.getOrders();

        
    }
    
        public ResultSet getCustomerNameFromId(int userId){
        ResultSet rs = sr.getCustomerNameFromId(userId);
        return rs;
    }
    
    public ResultSet selectAddressFromId(int userId){
        ResultSet rs = sr.selectAddressFromId(userId);
        return rs;
    }
    
    public int findUserID(String email) 
    {
    return sr.findUserID(email);
    }
    
    
    public void saveGuestCustomer(String firstName, String lastName, String email, String streetName, 
            String houseNumber, String zipcode, String shippingCity){
    sr.saveGuestCustomer(firstName, lastName, email, streetName, 
            houseNumber, zipcode, shippingCity);
    }
        
    
}
