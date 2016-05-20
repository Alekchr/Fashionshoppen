/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Domain.Order;

/**
 *
 * @author aleksander
 */
public interface ServiceFacade
{
    public void editProductName(int productId, String productName);
    

    public void editProductCategory(int productId, String productCategory);

    public void editProductGender(int productId, String productGender);
    

    public void editProductPrice(int productId, Double price);

    public void editProductPicture(int productId, String imagePath);
    
    public void storeOrder(Order order);
}
