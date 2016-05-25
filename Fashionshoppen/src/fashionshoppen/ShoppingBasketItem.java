
package fashionshoppen;

import javafx.scene.image.WritableImage;
import products.Item;
import products.Product;


public class ShoppingBasketItem
{
private final Product product;
private final Item item;

    public ShoppingBasketItem(Item item){
    this.product = item.getProduct();
    this.item = item;
    }
    
    
    public void setAmount(int amount){
        this.item.setAmount(amount);
    }
    
    public int getAmount(){
        return this.item.getAmount();
        
    }
    
   public String getName()
   {
        return this.product.getName();
   }
   
   public double getPrice()
   {
       return this.product.getProductPrice() * this.item.getAmount();
   }
   
   public String getSize(){
       return this.item.getSize();
   }
   
   public void setSize(String size){
       this.item.setSize(size);
   }
   
    public WritableImage getImage(){
        return this.product.getImage();
    }
   
   
}
