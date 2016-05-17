package Domain;

import services.ServerRequest;
import fashionshoppen.Fashionshoppen;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import services.ServicesFacade;
import javafx.scene.image.*;

public class Catalog {

    ServicesFacade sf = new ServicesFacade();
    Product product;
    Connection con;
    ServerRequest SR = new ServerRequest();
    ResultSet productResultSet;
    ArrayList products = new ArrayList();

    public Catalog()
    {
        productResultSet = getProducts();

    }

    public ResultSet getProducts()
    {
        ResultSet rs = sf.getProducts();
        return rs;
    }

    public ArrayList showProducts()
    {
            
        try {
              
            while (productResultSet.next()) {
                Product product = new Product(productResultSet.getString("product_name"), productResultSet.getString("product_gender"), productResultSet.getString("product_category"), productResultSet.getDouble("product_price"), "/Users/jonaspedersen/github folder/Fashionshoppen/Fashionshoppen/src/placeholder1.png");
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}
