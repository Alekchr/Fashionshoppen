package products;

public class Item
{

    private Product product;
    private int amount;
    private String size;
    private double itemPrice;
    private String productName;

    public Item(Product product, int amount, String size)
    {
        this.product = product;
        this.amount = amount;
        this.size = size;
        this.productName = product.getName();
        this.itemPrice = product.getProductPrice() * amount;

    }

    public String getName(){
        return productName;
    }
    public Product getProduct()
    {
        return product;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    
        public double getItemPrice()
    {
        return this.itemPrice;
    }

    public void updateItemPrice()
    {
        this.itemPrice = this.amount*this.getProduct().getProductPrice();
    }


    
    

}
