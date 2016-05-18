package Domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import services.ServicesFacade;

public class Catalog {

    ServicesFacade sf = new ServicesFacade();
    Product product;
    Connection con;
    ResultSet productResultSet;
    ArrayList products = new ArrayList();

    public Catalog()
    {
        productResultSet = getProducts();

    }

    //getProducts() er en metode som returnerer ResultSet fra servicelaget
    //ResultSettet bruges i showproducts til at oprette array af products.
    public ResultSet getProducts()
    {
        ResultSet rs = sf.getProducts();
        return rs;
    }

    
    //showProducts() er en metode som returnerer et array af alle produkter fra databasen
    //Produkterne har følgende: Navn, Kategori, Køn, Pris, billede-path
    public ArrayList showProducts()
    {
            String pathString = "";
        try {
              
            while (productResultSet.next()) {
                
                if(productResultSet.getString("product_category").equals("kjole")){
                    pathString = "/home/aleksander/UNI/Software/2. semester/Projekt/Fashionshoppenv1/Fashionshoppen/src/dressplaceholder.png";
                } else {
                    pathString = "/home/aleksander/UNI/Software/2. semester/Projekt/Fashionshoppenv1/Fashionshoppen/src/placeholder1.png";
                }
                product = new Product(productResultSet.getString("product_name"), productResultSet.getString("product_gender"), productResultSet.getString("product_category"), productResultSet.getDouble("product_price"), pathString);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}
