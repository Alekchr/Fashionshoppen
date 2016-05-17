/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fashionshoppen;

import Domain.Product;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.ServerRequest;

import Domain.Webshop;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Image;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author aleksander
 */
public class FXMLDocumentController implements Initializable {

    private int checkboxCounter = 0;
    private HashMap<CheckBox, String> cbMapGender;
    private HashMap<CheckBox, String> cbMapCategory;
    private ArrayList<CheckBox> cbGenderArray;
    private Boolean isGenderSelected = false;
    private ArrayList products;
    private Webshop webshop;
    private CheckBox[] cbArray;
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
        cbGenderArray = new ArrayList();
        MainTabPane.getTabs();

        products = webshop.showProducts();

        cbGenderArray.add(womanCB);
        cbGenderArray.add(manCB);
        cbGenderArray.add(unisexCB);

        cbMapGender.put(womanCB, "dame");
        cbMapGender.put(manCB, "herre");
        cbMapGender.put(unisexCB, "unisex");

        cbMapCategory.put(tshirtCB, "t-shirt");
        cbMapCategory.put(kjoleCB, "kjole");
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

    private ArrayList filter()
    {
        String st = TFsearch.getText();
        ArrayList<Product> productsToReturn = new ArrayList();

        String genderString = "";
        String categoryString = "";

        for (CheckBox cb : cbMapGender.keySet()) {
            if (cb.isSelected()) {
                genderString += cbMapGender.get(cb) + ";";
            }
        }

        for (CheckBox cb : cbMapCategory.keySet()) {
            if (cb.isSelected()) {
                categoryString += cbMapCategory.get(cb) + ";";
            }
        }
        String[] genderStrings = genderString.split(";");
        String[] categoryStrings = categoryString.split(";");

        if (genderString.isEmpty() != true && categoryString.isEmpty() != true) {
            for (int i = 0; i < products.size(); i++) {
                Boolean genderMatch = false;
                Boolean categoryMatch = false;
                Product product = (Product) products.get(i);

                for (int k = 0; k < genderStrings.length; k++) {

                    if (product.getGender().equals(genderStrings[k])) {
                        genderMatch = true;
                    }

                    for (int j = 0; j < categoryStrings.length; j++) {

                        if (product.getCategory().equals(categoryStrings[j])) {
                            categoryMatch = true;
                        }

                    }

                }

                if (genderMatch && categoryMatch) {
                    productsToReturn.add(product);
                }

                genderMatch = false;
                categoryMatch = false;
            }
        } else if (genderString.isEmpty() != true) {
            for (int i = 0; i < products.size(); i++) {
                Product product = (Product) products.get(i);

                for (int k = 0; k < genderStrings.length; k++) {
                    if (product.getGender().equals(genderStrings[k])) {
                        productsToReturn.add(product);
                    }
                }

            }
        } else if (categoryString.isEmpty() != true) {
            for (int i = 0; i < products.size(); i++) {
                Product product = (Product) products.get(i);
                for (int k = 0; k < categoryStrings.length; k++) {
                    if (product.getCategory().equals(categoryStrings[k])) {
                        productsToReturn.add(product);
                    }
                }
            }
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product product = (Product) products.get(i);
                productsToReturn.add(product);

            }

        }
        return productsToReturn;
    }

//        
    @FXML
    private void handleSearch(ActionEvent event)
    {
        String st = TFsearch.getText();
        ArrayList<Product> productsToReturn = filter();
        productWindow.getChildren().clear();
        

        if (productsToReturn.isEmpty()) {
            System.out.println("productstoreturn er tom");
        } else {

            int rowCount = 0;
            int colCount = 0;
            int rowsInThumb = 0;

            for (Product prod : productsToReturn) {
                if (colCount >= 5) {
                    colCount = 0;
                    rowCount++;
                }
                Label priceLabel;
                Label nameLabel;
                Button buyButton;
                GridPane productThumbnail;

                ImageView imView = new ImageView(prod.getImage());
                imView.setFitHeight(100);
                imView.setFitWidth(220);
                

                productWindow.add(productThumbnail = new GridPane(), colCount, rowCount);
                productWindow.setPadding(new Insets(10, 10, 10, 30));
                colCount++;
                
                productThumbnail.setMaxSize(220, 220);
                productThumbnail.setMinSize(220, 220);
                productThumbnail.setAlignment(Pos.BOTTOM_CENTER);
                productThumbnail.setStyle("-fx-background-color: #FFFFFF");
                productThumbnail.setPrefSize(220, 220);
                productThumbnail.add(imView, colCount, rowsInThumb);
                rowsInThumb++;
                productThumbnail.add(nameLabel = new Label(prod.getName()), colCount, rowsInThumb);
                nameLabel.setMaxWidth(Double.MAX_VALUE);
                nameLabel.setAlignment(Pos.CENTER);
                rowsInThumb++;

                productThumbnail.add(priceLabel = new Label(prod.getPrice() + " KR"), colCount, rowsInThumb);
                priceLabel.setContentDisplay(ContentDisplay.CENTER);
                priceLabel.setMaxWidth(Double.MAX_VALUE);
                priceLabel.setAlignment(Pos.CENTER);
                rowsInThumb++;

                productThumbnail.add(buyButton = new Button("Læg i kurv"), colCount, rowsInThumb);
                buyButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        MainTabPane.getSelectionModel().select(2);
                        productPhoto.setImage(prod.getImage());
                        priceTag.setText(prod.getPrice()+ " KR");
                        nameTag.setText(prod.getName());
                    }

                });
                buyButton.setStyle("-fx-base: #52cc14;");
                buyButton.setMinSize(220, 45);
                buyButton.setAlignment(Pos.CENTER);
            }
            if (colCount >= 5) {
                rowCount++;
                colCount = 0;
            }

        }
    }

    @FXML
    private void handleBack(ActionEvent event)
    {
        MainTabPane.getSelectionModel().select(0);
    }


}
