/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fashionshoppen;

import Domain.Product;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import Domain.Webshop;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author aleksander
 */
public class FXMLDocumentController implements Initializable {


    private HashMap<CheckBox, String> cbMapGender;
    private HashMap<CheckBox, String> cbMapCategory;
    private ArrayList products;
    private Webshop webshop;
    @FXML
    private Pane RegisterPane;
    @FXML
    private TextField LoginEmail;
    @FXML
    private TextField LoginPW;
    @FXML
    private TextField TFsearch;
    @FXML
    private TabPane MainTabPane;
    @FXML
    private TextField regFirstName;
    @FXML
    private TextField regLastName;
    @FXML
    private TextField regEmail;
    @FXML
    private TextField regPW1;
    @FXML
    private TextField regPW2;
    @FXML
    private Tab TabMainpage;
    @FXML
    private Label LblLogin;
    @FXML
    private Button searchBtn;
    @FXML
    private GridPane productWindow;
    @FXML
    private Tab TabLogin;
    @FXML
    private Button BTNLogin;
    @FXML
    private Label LblNewReg;
    @FXML
    private Button BTNRegister;
    @FXML
    private CheckBox womanCB;
    @FXML
    private CheckBox unisexCB;
    @FXML
    private CheckBox manCB;
    @FXML
    private CheckBox tshirtCB;
    @FXML
    private CheckBox kjoleCB;
    @FXML
    private ImageView productPhoto;
    @FXML
    private Label nameTag;
    @FXML
    private Label priceTag;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        webshop = new Webshop();
        cbMapGender = new HashMap();
        cbMapCategory = new HashMap();

        //Fylder products array op med alle produkter
        products = webshop.showProducts();

        //Køn checkboxe puttes i map med deres values
        cbMapGender.put(womanCB, "dame");
        cbMapGender.put(manCB, "herre");
        cbMapGender.put(unisexCB, "unisex");

        //Kategori checkboxe puttes i map med deres values
        cbMapCategory.put(tshirtCB, "t-shirt");
        cbMapCategory.put(kjoleCB, "kjole");

        //Nedenstående metodekald sørger for at alle produkter bliver vist
        //som default når applikationen starter.
        filter();
        handleSearch(new ActionEvent());
    }

    @FXML
    private void handleLogin(MouseEvent event)
    {

        MainTabPane.getSelectionModel().select(1);
        RegisterPane.setVisible(false);

    }

    @FXML
    private void handleShowBasket(MouseEvent event)
    {
        MainTabPane.getSelectionModel().select(3);
    }

    @FXML
    private void handleLoginUser(ActionEvent event)
    {
        String email = LoginEmail.getText();
        String password = LoginPW.getText();
        
        webshop.checkUserType(email, password);
        
    }

    @FXML
    private void handleRegister(ActionEvent event)
    {
        String firstName = regFirstName.getText();
        String lastName = regLastName.getText();
        String email = regEmail.getText();
        if (regPW1.getText().equals(regPW2.getText())) {
            String password = regPW1.getText();

            webshop.registerCustomer(firstName, lastName, email, password);
        } else {

        }

        RegisterPane.setVisible(false);

    }

    @FXML
    private void handleCreateCustomer(MouseEvent event)
    {
        RegisterPane.setVisible(true);

    }

    //filter() er en metode som står for at filtrere produkter ud fra
    //hvilke checkboxes der er valgt samt hvad der er skrevet i søg
    private ArrayList filter()
    {
        String st = TFsearch.getText(); //Får text fra søgefelt - skal bruges til at filtrere på navn

        ArrayList<Product> productsToReturn = new ArrayList(); //ArrayList som fyldes op med de filtrerede resultater og returneres til handleSearch()

        String genderString = "";
        String categoryString = "";

        //For loop som tjekker om køn checkboxes er checked.
        //Hvis checkboxe er checked indsættes keysettenes values i en string separeret med ";"
        for (CheckBox cb : cbMapGender.keySet()) {
            if (cb.isSelected()) {
                genderString += cbMapGender.get(cb) + ";";
            }
        }

        //For loop som tjekker om kategori checkboxes er checked
        //Hvis checkboxe er checked indsættes keysettenes values i en string separeret med ";"
        for (CheckBox cb : cbMapCategory.keySet()) {
            if (cb.isSelected()) {
                categoryString += cbMapCategory.get(cb) + ";";
            }
        }

        //Stringsne fra loopsne splittes ind i et array
        String[] genderStrings = genderString.split(";");
        String[] categoryStrings = categoryString.split(";");

        if (genderString.isEmpty() != true && categoryString.isEmpty() != true) { //Bliver kaldt hvis der både er valgt køn og kategori
            for (int i = 0; i < products.size(); i++) {
                Boolean genderMatch = false;
                Boolean categoryMatch = false;
                webshop.createProduct((Product) products.get(i));
                

                for (int k = 0; k < genderStrings.length; k++) { //looper igennem genderStrings array og tjekker om valgte køn matcher produkters

                    if (webshop.getProduct().getGender().equals(genderStrings[k])) {
                        genderMatch = true;
                    }

                    for (int j = 0; j < categoryStrings.length; j++) { //looper igennem categoryStrings array og tjekker om valgte kategorier matcher produkters

                        if (webshop.getProduct().getCategory().equals(categoryStrings[j])) {
                            categoryMatch = true;
                        }

                    }

                }

                if (genderMatch && categoryMatch) { //Bliver kaldt hvis både valgte køn og kategorier matcher samme produkt
                    productsToReturn.add(webshop.getProduct()); //Produktet bliver tilføjet til ArrayList
                }

                genderMatch = false;
                categoryMatch = false;
            }

        } else if (genderString.isEmpty() != true) { //Bliver kaldt hvis der kun er valgt køn
            for (int i = 0; i < products.size(); i++) {
                webshop.createProduct((Product) products.get(i));

                for (int k = 0; k < genderStrings.length; k++) {
                    if (webshop.getProduct().getGender().equals(genderStrings[k])) {
                        productsToReturn.add(webshop.getProduct());
                    }
                }

            }
        } else if (categoryString.isEmpty() != true) { //Bliver kaldt hvis der kun er kategori
            for (int i = 0; i < products.size(); i++) {
                webshop.createProduct((Product) products.get(i));
                for (int k = 0; k < categoryStrings.length; k++) {
                    if (webshop.getProduct().getCategory().equals(categoryStrings[k])) {
                        productsToReturn.add(webshop.getProduct());
                    }
                }
            }
        } else { //Bliver kaldt hvis intet er valgt, og returnerer alle produkter uden filter
            for (int i = 0; i < products.size(); i++) {
                webshop.createProduct((Product) products.get(i));
                productsToReturn.add(webshop.getProduct());

            }

        }
        return productsToReturn;
    }

//        
    @FXML
    private void handleSearch(ActionEvent event)
    {
        ArrayList<Product> productsToReturn = filter();
        productWindow.getChildren().clear();

        if (productsToReturn.isEmpty()) {
            System.out.println("productstoreturn er tom");
        } else {

            int rowCount = 0;
            int colCount = 0;
            int rowsInThumb = 0;

            for (Product prod : productsToReturn) { //Looper igennem de filtrerede produkter
                
                if (colCount >= 5) { //Sørger for at der kun kan være 5 kolonner
                    colCount = 0;
                    rowCount++;
                }
                
                //GUI elementer instantieres for produkt
                Label priceLabel  = new Label(prod.getPrice() + " KR");
                Label nameLabel  = new Label(prod.getName().toUpperCase());
                Button buyButton  = new Button("Læg i kurv");
                GridPane productThumbnail = new GridPane();
                ImageView imView = new ImageView(prod.getImage());
                


                productWindow.add(productThumbnail, colCount, rowCount);
                productWindow.setPadding(new Insets(10, 10, 10, 30));
                colCount++;
 
                productThumbnail.setOnMouseEntered(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event)
                    {
                        productThumbnail.setOpacity(0.8);
                        productThumbnail.setCursor(Cursor.HAND);
                    }
                
            });
                
                productThumbnail.setOnMouseClicked((MouseEvent event1) ->
                {
                    MainTabPane.getSelectionModel().select(2);
                    productPhoto.setImage(prod.getImage());
                    priceTag.setText(prod.getPrice() + " KR");
                    nameTag.setText(prod.getName());
                });
                
                productThumbnail.setOnMouseExited((MouseEvent event1) ->
                {
                    productThumbnail.setOpacity(1);
                    productThumbnail.setCursor(Cursor.DEFAULT);
                });
                productThumbnail.setMaxSize(220, 245);
                productThumbnail.setMinSize(220, 245);
                productThumbnail.setAlignment(Pos.BOTTOM_CENTER);
                productThumbnail.setStyle("-fx-background-color: #FFFFFF");
                productThumbnail.setPrefSize(220, 245);
                
                //Produktbillede modificeres og indsættes i GUI
                imView.setFitHeight(126);
                imView.setFitWidth(220);
                productThumbnail.add(imView, colCount, rowsInThumb);
                rowsInThumb++;
                
                //Produktnavn modificieres og indsættes i GUI
                productThumbnail.add(nameLabel, colCount, rowsInThumb);
                nameLabel.setMaxWidth(Double.MAX_VALUE);
                nameLabel.setAlignment(Pos.CENTER);
                nameLabel.setPadding(new Insets(20,0,10,0));
                nameLabel.setStyle("-fx-font-weight: bold");
                rowsInThumb++;

                //Produktpris modificieres og indsættes i GUI
                productThumbnail.add(priceLabel, colCount, rowsInThumb);
                priceLabel.setContentDisplay(ContentDisplay.CENTER);
                priceLabel.setMaxWidth(Double.MAX_VALUE);
                priceLabel.setAlignment(Pos.CENTER);
                priceLabel.setPadding(new Insets(0,0,10,0));
                rowsInThumb++;

                //Køb knap modificieres og indsættes i GUI
                productThumbnail.add(buyButton, colCount, rowsInThumb);
                buyButton.setStyle("-fx-base: #52cc14; -fx-font-weight: bold");
                buyButton.setMinSize(220, 45);
                buyButton.setAlignment(Pos.CENTER);
                buyButton.setOnAction((ActionEvent event1) ->
                {
                    webshop.addItem(webshop.getProduct().getProduct_id());
                });
                
            }
        }
    }

    @FXML
    private void handleBack(ActionEvent event) //Går tilbage til startside fra produktside
    {
        MainTabPane.getSelectionModel().select(0);
    }

    
}
