
package source;

/**
 *
 * @author aleksander
 */
public class Product
{
    private String productName;
    private double productPrice;
    private String category;

    public Product(String productName, String category, double productPrice)
    {
        this.productName = productName;
        this.category = category;
        this.productPrice = productPrice;
        
    }
    
    public Product getProduct(String name){
        
        if(name.equalsIgnoreCase(productName)){
            return null;
        }
        else{
            return this;
        }
        
    }
    

    
}