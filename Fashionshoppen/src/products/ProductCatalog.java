package products;

import Domain.Webshop;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import services.ServicesFacade;

public class ProductCatalog {

    Product product;
    ResultSet productResultSet;
    ArrayList<Product> products;

    //getProducts() er en metode som returnerer ResultSet fra servicelaget
    //ResultSettet bruges i showproducts til at oprette array af products.
    public ResultSet getProducts()
    {
        ResultSet rs = ServicesFacade.getInstance().getProducts();
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
    
    
    public ArrayList filter(Map<CheckBox, String> genderBoxes, Map<CheckBox, String> categoryBoxes, String inputString)
    {
        //String searchString = TFsearch.getText().toLowerCase(); //Får text fra søgefelt - skal bruges til at filtrere på navn
        String searchString = inputString.toLowerCase();
        ArrayList<Product> productsToReturn = new ArrayList(); //ArrayList som fyldes op med de filtrerede resultater og returneres til handleSearch()

        String genderString = "";
        String categoryString = "";

        //For loop som tjekker om køn checkboxes er checked.
        //Hvis checkboxe er checked indsættes keysettenes values i en string separeret med ";"
        for (CheckBox cb : genderBoxes.keySet()) {
            if (cb.isSelected()) {
                genderString += genderBoxes.get(cb) + ";";
            }
        }

        //For loop som tjekker om kategori checkboxes er checked
        //Hvis checkboxe er checked indsættes keysettenes values i en string separeret med ";"
        for (CheckBox cb : categoryBoxes.keySet()) {
            if (cb.isSelected()) {
                categoryString += categoryBoxes.get(cb) + ";";
            }
        }

        //Stringsne fra loopsne splittes ind i et array
        String[] genderStrings = genderString.split(";");
        String[] categoryStrings = categoryString.split(";");

        if (genderString.isEmpty() != true && categoryString.isEmpty() != true) { //Bliver kaldt hvis der både er valgt køn og kategori
            for (int i = 0; i < products.size(); i++) {
                Boolean genderMatch = false;
                Boolean categoryMatch = false;

                for (String genderString1 : genderStrings) {
                    //looper igennem genderStrings array og tjekker om valgte køn matcher produkters
                    if (products.get(i).getGender().equals(genderString1)) {
                        genderMatch = true;
                    }
                    for (String categoryString1 : categoryStrings) {
                        //looper igennem categoryStrings array og tjekker om valgte kategorier matcher produkters
                        if (products.get(i).getCategory().equals(categoryString1)) {
                            categoryMatch = true;
                        }
                    }
                }

                if (genderMatch && categoryMatch && products.get(i).getName().toLowerCase().contains(searchString)) { //Bliver kaldt hvis både valgte køn og kategorier matcher samme produkt
                    productsToReturn.add(products.get(i)); //Produktet bliver tilføjet til ArrayList
                }

                genderMatch = false;
                categoryMatch = false;
            }

        } else if (genderString.isEmpty() != true) { //Bliver kaldt hvis der kun er valgt køn
            for (int i = 0; i < products.size(); i++) {

                for (String genderString1 : genderStrings) {
                    if (products.get(i).getGender().equals(genderString1) && products.get(i).getName().toLowerCase().contains(searchString)) {
                        productsToReturn.add(products.get(i));
                    }
                }

            }
        } else if (categoryString.isEmpty() != true) { //Bliver kaldt hvis der kun er kategori
            for (int i = 0; i < products.size(); i++) {
                for (String categoryString1 : categoryStrings) {
                    if (products.get(i).getCategory().equals(categoryString1) && products.get(i).getName().toLowerCase().contains(searchString)) {
                        productsToReturn.add(products.get(i));
                    }
                }
            }
        } else { //Bliver kaldt hvis intet er valgt, og returnerer alle produkter uden filter
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().toLowerCase().contains(searchString)) {
                    productsToReturn.add(products.get(i));
                }
            }

        }
        return productsToReturn;
    }
    
    public ArrayList getProductName(ArrayList<Product> returnedProducts){
        ArrayList<String> nameList = new ArrayList();
        for(Product prod : returnedProducts){
            nameList.add(prod.getName());
        }
        
        return nameList;
    }
    
        public ArrayList getProductCategory(ArrayList<Product> returnedProducts){
        ArrayList<String> categoryList = new ArrayList();
        for(Product prod : returnedProducts){
            categoryList.add(prod.getName());
        }
        
        return categoryList;
    }
    
                public ArrayList imagePathToReturn(ArrayList<Product> returnedProducts){
        ArrayList<String> imatePathList = new ArrayList();
        for(Product prod : returnedProducts){
            imatePathList.add(prod.getName());
        }
        
        return imatePathList;
    }
    
    
    
        public ArrayList getProductGender(ArrayList<Product> returnedProducts){
        ArrayList<String> genderList = new ArrayList();
        for(Product prod : returnedProducts){
            genderList.add(prod.getGender());
        }
        
        return genderList;
    }
    
    public ArrayList getProductPrice(ArrayList<Product> returnedProducts){
        ArrayList<Double> priceList = new ArrayList();
        for(Product prod : returnedProducts){
            priceList.add(prod.getProductPrice());
        }
        
        return priceList;
    }
    
    public ArrayList getProductDescription(ArrayList<Product> returnedProducts){
        ArrayList<String> descriptionList = new ArrayList();
        for(Product prod : returnedProducts){
            descriptionList.add(prod.getName());
        }
        
        return descriptionList;
    }
    
    public ArrayList getProductImage(ArrayList<Product> returnedProducts){
        ArrayList<WritableImage> imagePathList = new ArrayList();
        for(Product prod : returnedProducts){
            imagePathList.add(prod.getImage());
        }
        
        return imagePathList;
    }
    
        public ArrayList getProductId(ArrayList<Product> returnedProducts){
        ArrayList<Integer> idList = new ArrayList();
        for(Product prod : returnedProducts){
            idList.add(prod.getProductId());
        }
        
        return idList;
    }


}
