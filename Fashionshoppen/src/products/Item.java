package products;

public class Item
{

    private Product product;
    private int amount;
    private String size;

    public Item(Product product, int amount, String size)
    {
        this.product = product;
        this.amount = amount;
        this.size = size;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

}
