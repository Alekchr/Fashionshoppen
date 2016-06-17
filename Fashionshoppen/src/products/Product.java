package products;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.*;
import javax.imageio.ImageIO;

public class Product {

    private int productId;
    private String productName;
    private double productPrice;
    private String category;
    private String gender;
    private String description;
    private String imagePath;
    private WritableImage writableImg;

    public Product(String productName, String gender, String category, double productPrice, String description, String imagePath)
    {
        this.productName = productName;
        this.gender = gender;
        this.category = category;
        this.productPrice = productPrice;
        this.description = description;
        this.imagePath = imagePath;
        if (imagePath != null) {
            this.writableImg = createImage(imagePath);
        }

    }

    public WritableImage getImage()
    {
        return writableImg;
    }

    //createImage() er en metode som returnerer WritableImage ud fra imagePath.
    //Image er den størrelse som det er, og skal resizes i controller.
    public WritableImage createImage(String imagePath)
    {
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
    
    public String getImagePath(){
        return imagePath;
    }

    public String getName()
    {
        return productName;
    }

    public String getCategory()
    {
        return category;
    }

    public String getGender()
    {
        return gender;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProduct_id(int productId)
    {
        this.productId = productId;
    }
    
    public String getDescription(){
        return description;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

}
