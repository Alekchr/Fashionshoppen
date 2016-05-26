package fashionshoppen;

import products.Product;
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
import com.sun.prism.impl.Disposer.Record;
import java.io.File;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import products.Item;
import products.Order;

import services.PaymentOptions;
import static java.lang.Integer.parseInt;
import java.util.Collections;
import java.util.Comparator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    //<editor-fold>
    static private Comparator<Product> sortByName;
    static private Comparator<Product> sortByPrice;
    static private Comparator<Product> sortByCategory;
    private String path = "";
    private final int REMOVE_PRODUCT = 1;
    private final int REMOVE_BASKET_PRODUCT = 2;
    private final String HOME_DELIVERY = "Home delivery";
    private final String STORE_DELIVERY = "Store delivery";
    private HashMap<CheckBox, String> cbMapGender;
    private HashMap<CheckBox, String> cbMapCategory;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private ArrayList<CheckBox> sortCBArray;
    String deliveryMethod;

    private List<Item> basketProducts;

    private ObservableList<Product> obsProductList = FXCollections.observableArrayList();
    private ObservableList<Order> obsManageOrderList = FXCollections.observableArrayList();
    private ObservableList<Item> obsBasketList;
    private ObservableList<String> sizes = FXCollections.observableArrayList("Small", "Medium", "Large", "X-Large");
    private ObservableList<Integer> amount = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //</editor-fold> 
    
    //<editor-fold>
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
    private TilePane productWindow;
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
    private Button addProductBtn;
    @FXML
    private TextField setNameField;
    @FXML
    private TextField setPriceField;
    @FXML
    private ComboBox categoryCMB;
    @FXML
    private ComboBox genderCMB;
    @FXML
    private ScrollPane productWindowScrollPane;
    @FXML
    private GridPane productList;
    @FXML
    private AnchorPane manageProductsPane;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, String> typeCol;
    @FXML
    private TableColumn<Product, Double> priceCol;
    @FXML
    private TableColumn<Product, String> genderCol;
    @FXML
    private TableColumn<Record, Boolean> btnCol;
    @FXML
    private TextField editNameField;
    @FXML
    private TextField editPriceField;
    @FXML
    private ComboBox editCategoryCMB;
    @FXML
    private ComboBox editGenderCMB;
    @FXML
    private Button saveChangesBtn;
    @FXML
    private Label productIdLabel;
    @FXML
    private TableView<Item> basketTable;
    @FXML
    private TableColumn<Product, ?> basketPic;
    @FXML
    private TableColumn<Item, String> basketName;
    @FXML
    private TableColumn<Item, String> basketSize;
    @FXML
    private TableColumn<Item, Integer> basketAmount;
    @FXML
    private TableColumn<Item, Double> basketPrice;
    @FXML
    private TableColumn<Record, Boolean> basketBtn;
    @FXML
    private Button acceptBasket;
    @FXML
    private TextField orderFirstName;
    @FXML
    private TextField orderLastName;
    @FXML
    private TextField orderStreet;
    @FXML
    private TextField orderZip;
    @FXML
    private TextField orderCity;
    @FXML
    private TextField orderEmail;
    @FXML
    private TextField orderEmailCheck;
    @FXML
    private ToggleGroup pickupChoice;
    @FXML
    private ComboBox storeChoice;
    @FXML
    private RadioButton cardPayment;
    @FXML
    private RadioButton storePayment;
    @FXML
    private RadioButton paypalPayment;
    @FXML
    private TableColumn<Order, Integer> orderIdCol;
    @FXML
    private TableColumn<Order, String> orderDateCol;
    @FXML
    private TableColumn<Order, String> orderCustomerCol;
    @FXML
    private TableColumn<Order, Double> orderPriceCol;
    @FXML
    private TableColumn<Order, Integer> orderStatusCol;
    @FXML
    private Label orderIdLabel;
    @FXML
    private TableView<Order> manageOrderTable;
    @FXML
    private CheckBox jacketsCB;
    @FXML
    private CheckBox jeansCB;
    @FXML
    private CheckBox shirtsCB;
    @FXML
    private CheckBox pantsCB;
    @FXML
    private CheckBox shortsCB;
    @FXML
    private RadioButton homeDelivery;
    @FXML
    private RadioButton storeDelivery;
    @FXML
    private Button endOrder;
    @FXML
    private Label deniedEmailLabel;
    @FXML
    private Label deniedAllFieldsLabel;
    @FXML
    private Label deniedPassMatchLabel;
    @FXML
    private Label deniedPassLabel;
    @FXML
    private Label deniedFirstNameLabel;
    @FXML
    private Label deniedLastNameLabel;
    @FXML
    private CheckBox sortPriceCB;
    @FXML
    private CheckBox sortNameCB;
    @FXML
    private CheckBox sortCatCB;
    @FXML
    private TextArea setProductDescriptionTA;
    @FXML
    private TextArea editProductDescriptionTA;
    @FXML
    private Label orderFirstNameLabel;
    @FXML
    private Label orderLastNameLabel;
    @FXML
    private Label orderAddressLabel;
    @FXML
    private Label orderZipLabel;
    @FXML
    private Label orderCityLabel;
    @FXML
    private Label orderEmailLabel1;
    @FXML
    private Label orderEmailLabel2;
    @FXML
    private Label orderPaymentMethod;
    @FXML
    private Label orderErrorLabel;
    @FXML
    private Label orderDeliveryLabel;
    @FXML
    private Text productDescriptionView;
    @FXML
    private Button productPageBuyButton;

    //</editor-fold> 
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        cbMapGender = new HashMap();
        cbMapCategory = new HashMap();
        sortCBArray = new ArrayList();

        sortCBArray.add(sortNameCB);
        sortCBArray.add(sortPriceCB);
        sortCBArray.add(sortCatCB);
        sortCBArray.stream().forEach((cb) -> {
            cb.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                handleSearch();
            });
        });

        TFsearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                handleSearch();
            }

        });

        
        productPageBuyButton.getStyleClass().add("buy-button");
        
        productPageBuyButton.setOnMouseEntered((MouseEvent event) -> {
            productPageBuyButton.setCursor(Cursor.HAND);
        });
        
        productPageBuyButton.setOnMouseExited((MouseEvent event) -> {
            productPageBuyButton.setCursor(Cursor.HAND);
        });
        
        
        productWindowScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        productWindowScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);

        productWindow.setPrefColumns(5);
        productWindow.setVgap(25);
        productWindow.setHgap(25);

        deniedFirstNameLabel.setVisible(false);
        deniedLastNameLabel.setVisible(false);
        deniedEmailLabel.setVisible(false);
        deniedAllFieldsLabel.setVisible(false);
        deniedPassMatchLabel.setVisible(false);
        deniedPassLabel.setVisible(false);
        
        orderFirstNameLabel.setVisible(false);
        orderLastNameLabel.setVisible(false);
        orderEmailLabel1.setVisible(false);
        orderEmailLabel2.setVisible(false);
        orderErrorLabel.setVisible(false);
        orderAddressLabel.setVisible(false);
        orderZipLabel.setVisible(false);
        orderCityLabel.setVisible(false);
        orderPaymentMethod.setVisible(false);
        orderDeliveryLabel.setVisible(false);
        
        storeChoice.setVisible(false);
        storeChoice.getItems().addAll("Nyborgvej 23, Odense", "Oluf bagers gade 77, Odense C", "");
        
        //Fylder products array op med alle produkter
        products = Webshop.getInstance().createProductsArray();

        //Fylder orders array op med alle ordrer()
        orders = Webshop.getInstance().createOrdersArray();

        //Køn checkboxe puttes i map med deres values
        cbMapGender.put(womanCB, "dame");
        cbMapGender.put(manCB, "herre");
        cbMapGender.put(unisexCB, "unisex");

        //Kategori checkboxe puttes i map med deres values
        cbMapCategory.put(tshirtCB, "t-shirt");
        cbMapCategory.put(kjoleCB, "kjole");
        cbMapCategory.put(shirtsCB, "skjorte");
        cbMapCategory.put(pantsCB, "bukser");
        cbMapCategory.put(jeansCB, "jeans");
        cbMapCategory.put(shortsCB, "shorts");

        cbMapCategory.keySet().stream().forEach((cb) -> {
            cb.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                handleSearch();
            });
        });

        cbMapGender.keySet().stream().forEach((cb) -> {
            cb.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                handleSearch();
            });
        });

        categoryCMB.getItems().addAll("kjole", "t-shirt", "jeans", "skjorter", "bukser", "shorts", "jakker");
        genderCMB.getItems().addAll("herre", "dame", "unisex");

        editCategoryCMB.getItems().addAll("kjole", "t-shirt", "jeans", "skjorter", "bukser", "shorts", "jakker");
        editGenderCMB.getItems().addAll("herre", "dame", "unisex");

        createProductList();
        showProductList();
        createManageOrderList();
        showManageOrderList();

        //Nedenstående metodekald sørger for at alle produkter bliver vist
        //som default når applikationen starter.
        handleButtonSearch(new ActionEvent());
    }

    @FXML
    private void handleSaveChanges(ActionEvent event)
    {
        int productID = parseInt(productIdLabel.getText());
        String name = editNameField.getText();
        String gender = editGenderCMB.getValue().toString();
        String category = editCategoryCMB.getValue().toString();
        Double price = parseDouble(editPriceField.getText());
        String description = editProductDescriptionTA.getText();
        String imagePath = path;

        Webshop.getInstance().editProductName(productID, name);
        Webshop.getInstance().editProductCategory(productID, category);
        Webshop.getInstance().editProductGender(productID, gender);
        Webshop.getInstance().editProductPrice(productID, price);
        Webshop.getInstance().editProductDescription(productID, description);
        Webshop.getInstance().editProductPicture(productID, imagePath);

        refreshTable();

    }

    @FXML
    private void handleBasketAccept(ActionEvent event)
    {
        if (Webshop.getInstance().userHasShoppingBasket()) {
            MainTabPane.getSelectionModel().select(4);
        } else {
            System.out.println("Din kurv er tom, så du kan ikke tjekke ud.");
        }
    }

    public void showLabel(String input, Label label)
        {
        if (input.isEmpty()) {
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }
        }
    
    @FXML
    private void handleEndOrder(ActionEvent event)
    {
        String firstName = orderFirstName.getText();
        String lastName = orderLastName.getText();
        String street = orderStreet.getText();
        String zip = orderZip.getText();
        String city = orderCity.getText();
        String email = orderEmail.getText();
        String payment_option = getPaymentOption();
        Boolean validEmail;

        
        
        if (!email.contains("@") || !(email.contains(".com") || email.contains(".dk"))) {
            orderEmailLabel1.setVisible(true);
            validEmail = false;
        } else {
            orderEmailLabel1.setVisible(false);
            validEmail = true;
        }
        showLabel(firstName, orderFirstNameLabel);
        showLabel(lastName, orderLastNameLabel);
        showLabel(street, orderAddressLabel);
        showLabel(zip, orderZipLabel);
        showLabel(city, orderCityLabel);
        showLabel(payment_option, orderPaymentMethod);
        showLabel(deliveryMethod, orderDeliveryLabel);

        if (regPW1.getText().equals(regPW2.getText()) && !regPW1.getText().isEmpty() && !regPW2.getText().isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && validEmail) {
            String password = regPW1.getText();

            
            deniedAllFieldsLabel.setVisible(false);
            RegisterPane.setVisible(false);
        } else if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            deniedAllFieldsLabel.setVisible(true);
        } else {
            deniedAllFieldsLabel.setVisible(false);
        }
        
        
        if (!orderEmail.getText().equals(orderEmailCheck.getText())) {
            System.out.println("De 2 emails du indtastede matcher ikke hinanden.");
        } else {
            Webshop.getInstance().storeOrder(payment_option, firstName, lastName, email, street, lastName, zip, city);
        }

    }


    String getPaymentOption()
    {
        String payment_option;
        if (paypalPayment.isSelected()) {
            payment_option = PaymentOptions.PAYPAL;
        } else if (cardPayment.isSelected()) {
            payment_option = PaymentOptions.CREDIT_CARD;
        } else {
            payment_option = PaymentOptions.IN_STORE;
        }

        return payment_option;
    }

    @FXML
    private void handleHomeDelivery(ActionEvent event)
    {
        deliveryMethod = HOME_DELIVERY;
    }

    @FXML
    private void handleStoreDelivery(ActionEvent event)
    {
        deliveryMethod = STORE_DELIVERY;
        storeChoice.setVisible(true);
    }

    @FXML
    private void handleFileChooser(ActionEvent event)
    {
        path = "";
        Stage stage = (Stage) MainTabPane.getScene().getWindow();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        path = selectedFile.getAbsolutePath();

    }

    public class ButtonCell extends TableCell<Record, Boolean> {

        final Button cellButton = new Button("Slet");

        ButtonCell(int function)
        {

            cellButton.setStyle("-fx-base: #FF0000; -fx-font-weight: bold");
            cellButton.setMinWidth(70);
            cellButton.setOnAction((ActionEvent t)
                    -> {

                if (function == REMOVE_PRODUCT) {
                    Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    removeProduct(currentProduct);
                } else if (function == REMOVE_BASKET_PRODUCT) {
                    Item currentItem = (Item) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    removeBasketItem(currentItem);
                }

            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty)
        {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }

            if (empty) {
                setGraphic(null);
            }
        }

    }

    private void removeProduct(Product currentProduct)
    {
        obsProductList.remove(currentProduct);
        Webshop.getInstance().deleteProduct(currentProduct.getProductId());
        refreshTable();
    }

    protected void removeBasketItem(Item currentItem)
    {

        obsBasketList.remove(currentItem);
        for (int i = 0; i < basketProducts.size(); i++) {
            if (currentItem == Webshop.getInstance().getShoppingBasketItems().get(i)) {
                Webshop.getInstance().removeItem(Webshop.getInstance().getShoppingBasketItems().get(i));
            }
        }

    }

    @FXML
    private void handleLogin(MouseEvent event)
    {

        MainTabPane.getSelectionModel().select(2);
        RegisterPane.setVisible(false);

    }

    static {
        sortByName = (Product o1, Product o2) -> o1.getName().compareTo(o2.getName());

        sortByPrice = (Product o1, Product o2) -> {
            Double d1 = o1.getProductPrice();
            Double d2 = o2.getProductPrice();
            return d1.compareTo(d2);
        };

        sortByCategory = (Product o1, Product o2) -> o1.getCategory().compareTo(o2.getCategory());

    }

    public void sortByName(ArrayList al)
    {
        Collections.sort(al, sortByName);
    }

    public void sortByPrice(ArrayList al)
    {
        Collections.sort(al, sortByPrice);
    }

    public void sortByCategory(ArrayList al)
    {
        Collections.sort(al, sortByCategory);
    }

    @FXML
    private void handleShowBasket(MouseEvent event)
    {
        MainTabPane.getSelectionModel().select(3);
        showBasketList();
    }

    @FXML
    private void handleLoginUser(ActionEvent event)
    {
        String email = LoginEmail.getText();
        String password = LoginPW.getText();

        Webshop.getInstance().loginUser(email, password);

    }


    
    @FXML
    private void handleRegister(ActionEvent event)
    {
        String firstName = regFirstName.getText();
        String lastName = regLastName.getText();
        String email = regEmail.getText();
        String pass = regPW1.getText();
        String confirmPass = regPW2.getText();
        Boolean validEmail;

        if (!email.contains("@") || !(email.contains(".com") || email.contains(".dk"))) {
            deniedEmailLabel.setVisible(true);
            validEmail = false;
        } else {
            deniedEmailLabel.setVisible(false);
            validEmail = true;
        }
        showLabel(firstName, deniedFirstNameLabel);
        showLabel(lastName, deniedLastNameLabel);

        if (pass.length() < 8) {
            deniedPassLabel.setVisible(true);
        } else {
            deniedPassLabel.setVisible(false);
        }

        if (!pass.equals(confirmPass)) {
            deniedPassMatchLabel.setVisible(true);
        } else {
            deniedPassMatchLabel.setVisible(false);
        }

        if (regPW1.getText().equals(regPW2.getText()) && !regPW1.getText().isEmpty() && !regPW2.getText().isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && validEmail) {
            String password = regPW1.getText();

            Webshop.getInstance().registerCustomer(firstName, lastName, email, password);
            deniedAllFieldsLabel.setVisible(false);
            RegisterPane.setVisible(false);
        } else if (pass.isEmpty() || confirmPass.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            deniedAllFieldsLabel.setVisible(true);
        } else {
            deniedAllFieldsLabel.setVisible(false);
        }

    }

    private void updateProducts()
    {

        products = Webshop.getInstance().createProductsArray();
        handleButtonSearch(new ActionEvent());

    }

    public void createProductList()
    {

        ObservableList<Product> tempProdList = FXCollections.observableArrayList();
        products.stream().forEach((prod) -> {
            tempProdList.add(prod);
        });

        obsProductList = tempProdList;

    }

//    
    public void createBasketList()
    {

        ObservableList<Item> tempBasketList = FXCollections.observableArrayList();
        basketProducts.stream().forEach((item) -> {
            tempBasketList.add(item);
        });

        obsBasketList = tempBasketList;

    }

    public void createManageOrderList()
    {
        ObservableList<Order> tempOrderList = FXCollections.observableArrayList();
        orders.stream().forEach((order) -> {
            tempOrderList.add(order);
        });
        obsManageOrderList = tempOrderList;
    }

    private void refreshTable()
    {
        updateProducts();
        createProductList();
        productTable.getItems().clear();
        productTable.getItems().addAll(obsProductList);
    }

    private void showProductList()
    {

        //public  ObservableList<Product> obsProductList = createProductList();
        //obsProductList
        //createProductList();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        btnCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        btnCol.setSortable(false);
        btnCol.setMinWidth(50);
        productTable.setItems(obsProductList);

        btnCol.setCellValueFactory((TableColumn.CellDataFeatures<Record, Boolean> p)
                -> new SimpleBooleanProperty(p.getValue() != null));

        btnCol.setCellFactory((TableColumn<Record, Boolean> p) -> new ButtonCell(REMOVE_PRODUCT));

        productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (productTable.getSelectionModel().getSelectedItem() != null) {
                productIdLabel.setText(productTable.getSelectionModel().getSelectedItem().getProductId() + "");
                editNameField.setText(productTable.getSelectionModel().getSelectedItem().getName());
                editCategoryCMB.setValue(productTable.getSelectionModel().getSelectedItem().getCategory());
                editGenderCMB.setValue(productTable.getSelectionModel().getSelectedItem().getGender());
                editPriceField.setText(productTable.getSelectionModel().getSelectedItem().getProductPrice() + "");
            }

        });
    }

    private void showManageOrderList()
    {
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        orderCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        manageOrderTable.setItems(obsManageOrderList);
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
        String searchString = TFsearch.getText().toLowerCase(); //Får text fra søgefelt - skal bruges til at filtrere på navn

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
                Webshop.getInstance().displayProduct((Product) products.get(i));

                for (String genderString1 : genderStrings) {
                    //looper igennem genderStrings array og tjekker om valgte køn matcher produkters
                    if (Webshop.getInstance().getProduct().getGender().equals(genderString1)) {
                        genderMatch = true;
                    }
                    for (String categoryString1 : categoryStrings) {
                        //looper igennem categoryStrings array og tjekker om valgte kategorier matcher produkters
                        if (Webshop.getInstance().getProduct().getCategory().equals(categoryString1)) {
                            categoryMatch = true;
                        }
                    }
                }

                if (genderMatch && categoryMatch && products.get(i).getName().toLowerCase().contains(searchString)) { //Bliver kaldt hvis både valgte køn og kategorier matcher samme produkt
                    productsToReturn.add(Webshop.getInstance().getProduct()); //Produktet bliver tilføjet til ArrayList
                }

                genderMatch = false;
                categoryMatch = false;
            }

        } else if (genderString.isEmpty() != true) { //Bliver kaldt hvis der kun er valgt køn
            for (int i = 0; i < products.size(); i++) {
                Webshop.getInstance().displayProduct((Product) products.get(i));

                for (String genderString1 : genderStrings) {
                    if (Webshop.getInstance().getProduct().getGender().equals(genderString1) && products.get(i).getName().toLowerCase().contains(searchString)) {
                        productsToReturn.add(Webshop.getInstance().getProduct());
                    }
                }

            }
        } else if (categoryString.isEmpty() != true) { //Bliver kaldt hvis der kun er kategori
            for (int i = 0; i < products.size(); i++) {
                Webshop.getInstance().displayProduct((Product) products.get(i));
                for (String categoryString1 : categoryStrings) {
                    if (Webshop.getInstance().getProduct().getCategory().equals(categoryString1) && products.get(i).getName().toLowerCase().contains(searchString)) {
                        productsToReturn.add(Webshop.getInstance().getProduct());
                    }
                }
            }
        } else { //Bliver kaldt hvis intet er valgt, og returnerer alle produkter uden filter
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().toLowerCase().contains(searchString)) {
                    Webshop.getInstance().displayProduct((Product) products.get(i));
                    productsToReturn.add(Webshop.getInstance().getProduct());
                }
            }

        }
        return productsToReturn;
    }

    private void handleLiveSearch(ChangeListener CL)
    {
        handleSearch();
    }

    @FXML
    private void handleButtonSearch(ActionEvent e)
    {
        handleSearch();
    }

    private void handleSearch()
    {
        ArrayList<Product> productsToReturn = filter();

        if (sortNameCB.isSelected()) {
            sortByName(productsToReturn);
        }

        if (sortPriceCB.isSelected()) {
            sortByPrice(productsToReturn);
        }

        if (sortCatCB.isSelected()) {
            sortByCategory(productsToReturn);
        }

        productWindow.getChildren().clear();

        int colCount = 0;
        int rowsInThumb = 0;

        for (Product prod : productsToReturn) { //Looper igennem de filtrerede produkter

            //GUI elementer instantieres for produkt
            Label priceLabel = new Label(prod.getProductPrice() + " KR");
            Label nameLabel = new Label(prod.getName().toUpperCase());
            Button buyButton = new Button("Læg i kurv");
            GridPane productThumbnail = new GridPane();
            ImageView imView = new ImageView(prod.getImage());

            productWindow.getChildren().add(productThumbnail);
            productWindow.setPadding(new Insets(30, 0, 0, 30));
            colCount++;

            productThumbnail.setOnMouseEntered((MouseEvent event) -> {
                productThumbnail.setOpacity(0.8);
                productThumbnail.setCursor(Cursor.HAND);
            });

            productThumbnail.setOnMouseClicked((MouseEvent event1)
                    -> {

                MainTabPane.getSelectionModel().select(1);
                productPhoto.setImage(prod.getImage());
                priceTag.setText(prod.getProductPrice() + " KR");
                nameTag.setText(prod.getName());
                productDescriptionView.setText(prod.getDescription());
                
                productPageBuyButton.setOnAction((ActionEvent event) -> {
                    Webshop.getInstance().addItem(prod, 1, "Small");
                });

            });

            productThumbnail.setOnMouseExited((MouseEvent event1)
                    -> {
                productThumbnail.setOpacity(1);
                productThumbnail.setCursor(Cursor.DEFAULT);
            });

            productThumbnail.setMaxSize(220, 245);
            productThumbnail.setMinSize(220, 245);
            productThumbnail.setAlignment(Pos.BOTTOM_CENTER);
            productThumbnail.getStyleClass().add("product-thumbnail");
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
            nameLabel.setPadding(new Insets(20, 0, 10, 0));
            nameLabel.setStyle("-fx-font-weight: bold");
            rowsInThumb++;

            //Produktpris modificieres og indsættes i GUI
            productThumbnail.add(priceLabel, colCount, rowsInThumb);
            priceLabel.setContentDisplay(ContentDisplay.CENTER);
            priceLabel.setMaxWidth(Double.MAX_VALUE);
            priceLabel.setAlignment(Pos.CENTER);
            priceLabel.setPadding(new Insets(0, 0, 10, 0));
            rowsInThumb++;

            //Køb knap modificieres og indsættes i GUI
            productThumbnail.add(buyButton, colCount, rowsInThumb);
            buyButton.getStyleClass().add("buy-button");
            //buyButton.setStyle("-fx-base: #52cc14; -fx-font-weight: bold");
            buyButton.setMinSize(220, 45);
            buyButton.setAlignment(Pos.CENTER);
            buyButton.setOnAction((ActionEvent event1)
                    -> {
                Webshop.getInstance().addItem(prod, 1, "Small");

            });

        }

    }

    private void handleBack(ActionEvent event) //Går tilbage til startside fra produktside
    {
        MainTabPane.getSelectionModel().select(0);
    }

    @FXML
    private void handleAddProduct(ActionEvent event)
    {

        String name = setNameField.getText();
        String category = categoryCMB.getValue().toString();
        String gender = genderCMB.getValue().toString();
        Double price = parseDouble(setPriceField.getText());
        String description = setProductDescriptionTA.getText();
        String imagePath = path;

        if (name != null && category != null && gender != null) {
            Webshop.getInstance().createProduct(name, category, gender, price, description, imagePath);
            setNameField.clear();
            setPriceField.clear();
            refreshTable();
            handleButtonSearch(new ActionEvent());

        }

    }

    @FXML
    private void returnMainPage(MouseEvent event)
    {
        MainTabPane.getSelectionModel().select(0);
    }

    public void updateBasketList()
    {
        basketProducts = Webshop.getInstance().getShoppingBasketItems();
        createBasketList();
        basketTable.setItems(obsBasketList);

    }

    public void showBasketList()
    {

        basketTable.setEditable(true);
        basketName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        basketPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        basketBtn.setSortable(false);
        basketSize.setSortable(false);
        basketBtn.setMinWidth(35);

        updateBasketList();
        basketBtn.setCellValueFactory((TableColumn.CellDataFeatures<Record, Boolean> p)
                -> new SimpleBooleanProperty(p.getValue() != null));

        basketBtn.setCellFactory((TableColumn<Record, Boolean> p) -> new ButtonCell(REMOVE_BASKET_PRODUCT));

        basketTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        basketSize.setCellFactory(ComboBoxTableCell.forTableColumn(sizes));
        basketAmount.setCellFactory(ComboBoxTableCell.forTableColumn(amount));
        basketAmount.setOnEditCommit((TableColumn.CellEditEvent<Item, Integer> e)
                -> {
            {
                for (int i = 0; i < basketProducts.size(); i++) {
                    Item selectedItem = basketTable.getSelectionModel().getSelectedItem();

                    if (selectedItem.equals(Webshop.getInstance().getShoppingBasketItems().get(i))) {
                        System.out.println("asdasdad");
                        Webshop.getInstance().getShoppingBasketItems().get(i).setAmount(e.getNewValue());
                        Webshop.getInstance().getShoppingBasketItems().get(i).updateItemPrice();
                        updateBasketList();

                    }
                }
            }

        });

    }

}
