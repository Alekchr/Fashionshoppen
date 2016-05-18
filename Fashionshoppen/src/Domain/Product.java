
package Domain;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.*;
import javax.imageio.ImageIO;

/**
 *
 * @author aleksander
 */
public class Product
{
    private int product_id;
    private String productName;
    private double productPrice;
    private String category;
    private String gender;
    private WritableImage writableImg;
    

    public Product(String productName, String gender, String category, double productPrice, String imagePath)
    {
        this.productName = productName;
        this.gender = gender;
        this.category = category;
        this.productPrice = productPrice;
        this.writableImg = createImage(imagePath);
        
        
    }
    
    public WritableImage getImage(){
        return writableImg;
    }
    
    
    //createImage() er en metode som returnerer WritableImage ud fra imagePath.
    //Image er den størrelse som det er, og skal resizes i controller.
    public WritableImage createImage(String imagePath){
                        BufferedImage bf = null;
                try {
                    bf = ImageIO.read(new File(imagePath));
                } catch (IOException ex) {
                    System.out.println("Image failed to load.");
                }

                WritableImage wr = null;
                if (bf != null) {
                    wr = new WritableImage(bf.getWidth(), bf.getHeight());
                    PixelWriter pw = wr.getPixelWriter();
                    for (int x = 0; x < bf.getWidth(); x++) {
                        for (int y = 0; y < bf.getHeight(); y++) {
                            pw.setArgb(x, y, bf.getRGB(x, y));
                        }
                    }
                }
                return wr;

    }

    public String getName(){
        return productName;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getGender(){
        return gender;
    }
    
    public Double getPrice(){
        return productPrice;
    }

    public int getProduct_id()
    {
        return product_id;
    }

    public void setProduct_id(int product_id)
    {
        this.product_id = product_id;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(double productPrice)
    {
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