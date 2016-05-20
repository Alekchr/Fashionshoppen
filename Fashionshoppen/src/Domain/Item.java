package Domain;

/**
 *
 * @author aleksander
 */
public class Item
{

    private int product_id;
    private int amount;

    public Item(int product_id, int amount)
    {
        this.product_id = product_id;
        this.amount = amount;
    }

    public int getProduct_id()
    {
        return product_id;
    }

    public void setProduct_id(int product_id)
    {
        this.product_id = product_id;
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
