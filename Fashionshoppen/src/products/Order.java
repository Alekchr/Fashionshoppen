package products;

import users.Address;

import java.util.Date;
import java.util.ArrayList;

public class Order
{

    private int customer_id;
    private int order_id;
    private String order_date;
    public double price;
    private Address shippingAddress;
    private double shippingCharge;
    private double finalPrice;
    private String payment_option;
    private String status;
    private ArrayList<Item> items;

    public Order(int customer_id, Address shippingAddress, String status)
    {
        this.customer_id = customer_id;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList();
        this.status = status;
        this.order_date = new Date().toString();
        
    }

    public void setCustomerId(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void addItem(Product product, int amount, String size)
    {
        items.add(new Item(product, amount, size));
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
        return this.order_date;
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
        return this.shippingCharge;
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

        public void updateFinalPrice(double totalPrice, double shippingCharge)
    {
        setFinalPrice(totalPrice + shippingCharge);
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

    public void setTotalPrice(double itemPrice)
    {
       this.price += itemPrice;
       
    }

    public void changeAmount(Item item, int amount)
    {
        for (Item i : items)
        {
            if (item.equals(i))
            {
                item.setAmount(amount);
            }
        }
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }

}