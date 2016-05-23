/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import users.Address;
import products.Item;
import java.util.Date;
import java.util.ArrayList;


public class Order
{

    private int customer_id;
    private int order_id;
    private Date order_date;
    private double price;
    private Address shippingAddress;
    private double shippingCharge;
    private double finalPrice;
    private String payment_option;
    private int Status;
    private ArrayList<Item> items;

    public Order(int customer_id, Address shippingAddress, int Status)
    {
        this.customer_id = customer_id;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList();
        
        order_date = new Date();
    }


    
    public void addItem(Product product, int quantity, String size) {
        items.add(new Item(product, quantity, size));
    }
    
    public void totalPrice(){
    
    
    }

    public void finishOrder(Order order)
    {
        items.toString();
    }

    public int getOrder_id()
    {
        return order_id;
    }

    public void setOrder_id(int order_id)
    {
        this.order_id = order_id;
    }

    public Date getOrder_date()
    {
        return order_date;
    }

    public void setOrder_date(Date order_date)
    {
        this.order_date = order_date;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }



    public double getShippingCharge()
    {
        return shippingCharge;
    }

    public void setShippingCharge(double shippingCharge)
    {
        this.shippingCharge = shippingCharge;
    }

    public double getFinalPrice()
    {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice)
    {
        this.finalPrice = finalPrice;
    }

    public String getPayment_option()
    {
        return payment_option;
    }

    public void setPayment_option(String payment_option)
    {
        this.payment_option = payment_option;
    }

    public int getStatus()
    {
        return Status;
    }

    public void setStatus(int Status)
    {
        this.Status = Status;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    
    
    public void changeAmount(Item item, int amount) {
        for(Item i : items) {
            if (item.equals(i)) {
                item.setAmount(amount);
            }
        }
    }

 
    public void addItem()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    public void removeItem(Item item) {
        items.remove(item);
    }
}
