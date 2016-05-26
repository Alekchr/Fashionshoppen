
package products;

import users.Address;

import java.util.Date;
import java.util.ArrayList;


public class Order
{

    private int customer_id;
    private int order_id;
    private String customerName;
    private String order_date;
    private double price;
    private Address shippingAddress;
    private double shippingCharge;
    private double finalPrice;
    private String payment_option;
    private String status;
    private ArrayList<Item> items;
    private int totalPrice;

    public Order(int customer_id, Address shippingAddress, String status)
    {
        this.customer_id = customer_id;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList();
        this.status = status;
        order_date = new Date().toString();
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }
    
    public String getCustomerName(){
        return customerName;
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

    public String getOrder_date()
    {
        return order_date;
    }

    public void setOrder_date(Date order_date)
    {
        this.order_date = order_date.toString();
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

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public void setOrder_date(String order_date)
    {
        this.order_date = order_date;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }


    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    public double getTotalPrice(){
        
        for(Item item: getItems())
        {
            
        }
        return totalPrice;
    }
    
    public void changeAmount(Item item, int amount) {
        for(Item i : items) {
            if (item.equals(i)) {
                item.setAmount(amount);
            }
        }
    }

        
    public void removeItem(Item item) {
        items.remove(item);
    }
}
