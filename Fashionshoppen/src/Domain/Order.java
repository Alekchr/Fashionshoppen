/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Domain.Item;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author aleksander
 */
public class Order implements OrderManager
{

    private int customer_id;
    private int order_id;
    private Date order_date;
    private double price;
    private Address shippingAddress;
    private double shippingCharge;
    private double finalPrice;
    private String payment_option;
    private String Status;
    private ArrayList<Item> items;

    public Order(int customer_id, Address shippingAddress, Item item)
    {
        this.customer_id = customer_id;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList();
        items.add(item);
    }


    public void addItem(Item item){
    items.add(item);
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

    public String getStatus()
    {
        return Status;
    }

    public void setStatus(String Status)
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

    @Override
    public void createOrder()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItem()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
