package products;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import services.ServicesFacade;

public class ProductCatalog {

    ServicesFacade sf = new ServicesFacade();
    Product product;
    ResultSet productResultSet;
    ArrayList products;

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
            productResultSet = getProducts();
            products = new ArrayList();
        try {
              
            while (productResultSet.next()) {
                product = new Product(productResultSet.getString("product_name"), productResultSet.getString("product_gender"), productResultSet.getString("product_category"), productResultSet.getDouble("product_price"),productResultSet.getString("product_information"), productResultSet.getString("image_path"));
                product.setProduct_id(productResultSet.getInt("product_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}
