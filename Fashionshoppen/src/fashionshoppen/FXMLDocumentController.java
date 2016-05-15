/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fashionshoppen;

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
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author aleksander
 */
public class FXMLDocumentController implements Initializable {

    private int checkboxCounter = 0;
    private HashMap<CheckBox, String> cbMapGender;
    private HashMap<CheckBox, String> cbMapCategory;
    private Boolean isGenderSelected = false;
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
    private AnchorPane productWindow;
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        webshop = new Webshop();
        cbMapGender = new HashMap();
        cbMapCategory = new HashMap();
        cbMapGender.put(womanCB, "'dame'");
        cbMapGender.put(manCB, "'herre'");
        cbMapGender.put(unisexCB, "'unisex'");
        
        cbMapCategory.put(tshirtCB, "'t-shirt'");
        cbMapCategory.put(kjoleCB, "'kjole'");

    }

//    private void showDClothes(ActionEvent event)
//    {
//        populateCBD();
//
//    }
//    private void populateCBH()
//    {
//        CBCloth.getItems().clear();
//        CBShoes.getItems().clear();
//        CBAcc.getItems().clear();
//
//        CBCloth.getItems().addAll(
//                "Nyheder", "Jakker og frakker", "T-shirts", "Jeans", "Skjorter", "Bukser", "Shorts",
//                "Polo", "Badeshorts", "Trøjer", "Undertøj", "Strømper", "Jakkesæt");
//
//        CBShoes.getItems().addAll(
//                "Nyheder", "Hjemmesko", "Sandaler", "Sneakers", "Støvler");
//
//        CBAcc.getItems().addAll(
//                "Nyheder", "Tasker", "Handsker", "Punge", "Huer og kasketter", "Bælter", "Ure", "Butterfly",
//                "Smykker", "Hatte", "Kortholder", "Halstørklæder");
//
//    }
//
//    private void populateCBD()
//    {
//        CBCloth.getItems().clear();
//        CBShoes.getItems().clear();
//        CBAcc.getItems().clear();
//
//        CBCloth.getItems().addAll(
//                "Nyheder", "Kjoler", "Bukser", "Jakker og frakker", "Nederdele", "Jeans", "Nattøj", "Badetøj", "Bluser",
//                "Skjorter", "Toppe", "Shorts", "T-shirts", "Tunika", "Lingerie", "Strømpebukser", "Undertøj", "Heldragt");
//
//        CBShoes.getItems().addAll(
//                "Nyheder", "Støvler", "Sandaler", "Ballerina Sko", "Sneakers", "Gummistøvler", "Stiletter", "Støvletter",
//                "Hjemmesko");
//
//        CBAcc.getItems().addAll(
//                "Nyheder", "Tasker", "Punge", "Smykker", "Bælter", "Tørklæder", "Ure",
//                "Handsker", "Hatte", "Huer og kasketter", "Mobil- og tabletholder", "Solbriller");
//
//    }
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

    @FXML
    private void handleSearch(ActionEvent event)
    {
        String st = TFsearch.getText();
        String genderQuery = "";
        String categoryQuery = "";
        String completeQuery;
        CheckBox[] checkedBoxes;
        
        for (CheckBox cb : cbMapGender.keySet()) {
            if (cb.isSelected()) {
                checkboxCounter++;
                isGenderSelected = true;
                if(genderQuery.isEmpty()){
                    genderQuery = "product_gender = " + cbMapGender.get(cb);
                } else {
                    genderQuery += "OR product_gender = " + cbMapGender.get(cb);
                }
            }

        }
        
        for (CheckBox cb : cbMapCategory.keySet()){
            if (cb.isSelected()){
                checkboxCounter++;
                
                if(categoryQuery.isEmpty()){
                    if(isGenderSelected){
                        categoryQuery = " AND product_category = " + cbMapCategory.get(cb);
                    } else {
                        categoryQuery = " product_category = " + cbMapCategory.get(cb);
                    }
                    
                } else {
                    categoryQuery += " OR product_category = " + cbMapCategory.get(cb);
                }
            }
        }
        
        completeQuery = "SELECT * FROM PRODUCTS WHERE " + genderQuery  + categoryQuery;
        
        if (checkboxCounter > 0) {
            webshop.browseCategory(completeQuery, st);
        } else {
            webshop.browseProductName(st);
        }

        checkboxCounter = 0;
        isGenderSelected = false;
    }

}
