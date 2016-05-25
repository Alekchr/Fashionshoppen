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
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.util.converter.DefaultStringConverter;
import products.Item;
import products.Order;

import services.PaymentOptions;

public class FXMLDocumentController implements Initializable {

    private final int REMOVE_PRODUCT = 1;
    private final int REMOVE_BASKET_PRODUCT = 2;
    private HashMap<CheckBox, String> cbMapGender;
    private HashMap<CheckBox, String> cbMapCategory;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private List<Item> orderProducts;
    private List<Item> basketProducts;
    public ArrayList<GridPane> productGridList;
    public ObservableList<Product> obsProductList = FXCollections.observableArrayList();
    public ObservableList<Product> obsOrderProductList = FXCollections.observableArrayList();
    public ObservableList<Order> obsManageOrderList = FXCollections.observableArrayList();
    public ObservableList<Product> obsBasketProductList = FXCollections.observableArrayList();

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
    private TableView<Product> basketTable;
    @FXML
    private TableColumn<Product, ?> basketPic;
    @FXML
    private TableColumn<Product, String> basketName;
    @FXML
    private TableColumn<ComboBox, String> basketSize;
    @FXML
    private TableColumn<ComboBox, Integer> basketAmount;
    @FXML
    private TableColumn<Product, Double> basketPrice;
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
    private ComboBox<?> storeChoice;
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        cbMapGender = new HashMap();
        cbMapCategory = new HashMap();

        productWindowScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        productWindowScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
//        productWindowScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
//        productWindowScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);

        productWindow.setPrefColumns(5);
        productWindow.setVgap(25);
        productWindow.setHgap(25);

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

        categoryCMB.getItems().addAll("kjole", "t-shirt");
        genderCMB.getItems().addAll("herre", "dame", "unisex");

        editCategoryCMB.getItems().addAll("kjole", "t-shirt");
        editGenderCMB.getItems().addAll("herre", "dame", "unisex");

        createProductList();
        showProductList();
        createManageOrderList();
        showManageOrderList();

        //Nedenstående metodekald sørger for at alle produkter bliver vist
        //som default når applikationen starter.
        filter();
        handleSearch(new ActionEvent());
    }

    @FXML
    private void handleSaveChanges(ActionEvent event)
    {
        int productID = parseInt(productIdLabel.getText());
        String name = editNameField.getText();
        String gender = editGenderCMB.getValue().toString();
        String category = editCategoryCMB.getValue().toString();
        Double price = parseDouble(editPriceField.getText());

        Webshop.getInstance().editProductName(productID, name);
        Webshop.getInstance().editProductCategory(productID, category);
        Webshop.getInstance().editProductGender(productID, gender);
        Webshop.getInstance().editProductPrice(productID, price);

        refreshTable();

    }

    @FXML
    private void handleBasketAccept(ActionEvent event)
    {
        if (Webshop.getInstance().userHasShoppingBasket()) {
            MainTabPane.getSelectionModel().select(4);
        } else {
            System.out.println("Du har ikke tilføjet nogle produkter til din indkøbskurv");
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
    }

    @FXML
    private void handleStoreDelivery(ActionEvent event
    )
    {
    }

    public class ButtonCell extends TableCell<Record, Boolean> {

        final Button cellButton = new Button("Slet");
        public Product productToRemove;

        ButtonCell(int function)
        {

            cellButton.setStyle("-fx-base: #FF0000; -fx-font-weight: bold");
            cellButton.setMinWidth(70);
            cellButton.setOnAction((ActionEvent t)
                    -> {
                Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                if (function == REMOVE_PRODUCT) {
                    removeProduct(currentProduct);
                } else if (function == REMOVE_BASKET_PRODUCT) {
                    removeOrderProduct(currentProduct);
                }

            });
        }

        ButtonCell()
        {

            cellButton.setStyle("-fx-base: #FF0000; -fx-font-weight: bold");
            cellButton.setMinWidth(70);
            cellButton.setOnAction((ActionEvent t)
                    -> {
                Product currentProduct = (Product) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());

                obsProductList.remove(currentProduct);
                Webshop.getInstance().deleteProduct(currentProduct.getProductId());
                refreshTable();
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

        protected void removeOrderProduct(Product currentProduct)
        {

            obsBasketProductList.remove(currentProduct);
            for (int i = 0; i < basketProducts.size(); i++) {
                if (currentProduct == Webshop.getInstance().getShoppingBasketItems().get(i).getProduct()) {
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

        @FXML
        private void handleShowBasket(MouseEvent event)
        {
            MainTabPane.getSelectionModel().select(3);
            //showOrderList();
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
            if (regPW1.getText().equals(regPW2.getText())) {
                String password = regPW1.getText();

                Webshop.getInstance().registerCustomer(firstName, lastName, email, password);
            } else {

            }

            RegisterPane.setVisible(false);

        }

        private void updateProducts()
        {

            products = Webshop.getInstance().createProductsArray();
            handleSearch(new ActionEvent());

        }

        public void createProductList()
        {

            ObservableList<Product> tempProdList = FXCollections.observableArrayList();
            for (Product prod : products) {
                tempProdList.add(prod);
            }

            obsProductList = tempProdList;

        }

        public void createOrderList()
        {

            ObservableList<Product> tempOrderList = FXCollections.observableArrayList();
            for (Item item : orderProducts) {
                tempOrderList.add(item.getProduct());
            }
        }

        public void createBasketList()
        {

            ObservableList<Product> tempBasketList = FXCollections.observableArrayList();
            for (Item item : basketProducts) {
                tempBasketList.add(item.getProduct());
            }

            obsBasketProductList = tempBasketList;

        }

        public void createManageOrderList()
        {
            ObservableList<Order> tempOrderList = FXCollections.observableArrayList();
            for (Order order : orders) {
                tempOrderList.add(order);
            }
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

            btnCol.setCellFactory((TableColumn<Record, Boolean> p) -> new ButtonCell());

            productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (productTable.getSelectionModel().getSelectedItem() != null) {
                    productIdLabel.setText(productTable.getSelectionModel().getSelectedItem().getProductId() + "");
                    editNameField.setText(productTable.getSelectionModel().getSelectedItem().getName());
                    editCategoryCMB.setValue(productTable.getSelectionModel().getSelectedItem().getCategory());
                    editGenderCMB.setValue(productTable.getSelectionModel().getSelectedItem().getGender());
                    editPriceField.setText(productTable.getSelectionModel().getSelectedItem().getPrice() + "");
                }
                btnCol.setCellFactory((TableColumn<Record, Boolean> p) -> new ButtonCell(REMOVE_PRODUCT));

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
        for (Product prod : products) {

//            GridPane productGrid = new GridPane();
//            
//            
//            Label priceLabel  = new Label(prod.getPrice() + " KR");
//            Label nameLabel  = new Label(prod.getName().toUpperCase());
//            Label categoryLabel = new Label(prod.getCategory().toUpperCase());
//            nameCol.setCe
//            ColumnConstraints nameCol = new ColumnConstraints();
//            nameCol.setPercentWidth(40);
//            ColumnConstraints priceCol = new ColumnConstraints();
//            priceCol.setPercentWidth(25);
//            ColumnConstraints categoryCol = new ColumnConstraints();
//            categoryCol.setPercentWidth(15);
//            ColumnConstraints buttonCol = new ColumnConstraints();
//            buttonCol.setPercentWidth(20);
//            productGrid.getColumnConstraints().addAll(nameCol, priceCol, buttonCol);
//            
//            Button deleteBtn = new Button("Slet");
//            deleteBtn.setOnAction(new EventHandler<ActionEvent>(){
//                    @Override
//                    public void handle(ActionEvent event)
//                    {
//                        webshop.deleteProduct(prod.getProductId());
//                        updateProducts();
//                        event.getSource().
//                        showProductList();
//                    }
//                
//            });
//            productGrid.setStyle("-fx-border-color: black; -fx-border-bottom-width: 1;");
//            productGrid.setMinHeight(40);
//            
//            priceLabel.setMinHeight(40);
//            deleteBtn.setMinHeight(30);
//            deleteBtn.setMinWidth(70);
//            deleteBtn.setPadding(new Insets(5, 0, 5, 0));
//            deleteBtn.setStyle("-fx-base: #FF0000; -fx-font-weight: bold");
//            
//            
//            nameLabel.setMinHeight(40);
//            
//                
//            productList.add(productGrid, 0, productCount);
//            productCount++;
//            productGrid.add(nameLabel, 0, 0);
//            productGrid.add(categoryLabel, 1, 0);
//            productGrid.add(priceLabel, 2, 0);
//            productGrid.add(deleteBtn, 3, 0);
//            
//            productGridList.add(productGrid);
        }
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
                Webshop.getInstance().displayProduct((Product) products.get(i));

                for (int k = 0; k < genderStrings.length; k++) { //looper igennem genderStrings array og tjekker om valgte køn matcher produkters

                    if (Webshop.getInstance().getProduct().getGender().equals(genderStrings[k])) {
                        genderMatch = true;
                    }

                    for (int j = 0; j < categoryStrings.length; j++) { //looper igennem categoryStrings array og tjekker om valgte kategorier matcher produkters

                        if (Webshop.getInstance().getProduct().getCategory().equals(categoryStrings[j])) {
                            categoryMatch = true;
                        }

                    }

                }

                if (genderMatch && categoryMatch) { //Bliver kaldt hvis både valgte køn og kategorier matcher samme produkt
                    productsToReturn.add(Webshop.getInstance().getProduct()); //Produktet bliver tilføjet til ArrayList
                }

                genderMatch = false;
                categoryMatch = false;
            }

        } else if (genderString.isEmpty() != true) { //Bliver kaldt hvis der kun er valgt køn
            for (int i = 0; i < products.size(); i++) {
                Webshop.getInstance().displayProduct((Product) products.get(i));

                for (int k = 0; k < genderStrings.length; k++) {
                    if (Webshop.getInstance().getProduct().getGender().equals(genderStrings[k])) {
                        productsToReturn.add(Webshop.getInstance().getProduct());
                    }
                }

            }
        } else if (categoryString.isEmpty() != true) { //Bliver kaldt hvis der kun er kategori
            for (int i = 0; i < products.size(); i++) {
                Webshop.getInstance().displayProduct((Product) products.get(i));
                for (int k = 0; k < categoryStrings.length; k++) {
                    if (Webshop.getInstance().getProduct().getCategory().equals(categoryStrings[k])) {
                        productsToReturn.add(Webshop.getInstance().getProduct());
                    }
                }
            }
        } else { //Bliver kaldt hvis intet er valgt, og returnerer alle produkter uden filter
            for (int i = 0; i < products.size(); i++) {
                Webshop.getInstance().displayProduct((Product) products.get(i));
                productsToReturn.add(Webshop.getInstance().getProduct());

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

        int colCount = 0;
        int rowsInThumb = 0;

        for (Product prod : productsToReturn) { //Looper igennem de filtrerede produkter

            //GUI elementer instantieres for produkt
            Label priceLabel = new Label(prod.getPrice() + " KR");
            Label nameLabel = new Label(prod.getName().toUpperCase());
            Button buyButton = new Button("Læg i kurv");
            GridPane productThumbnail = new GridPane();
            ImageView imView = new ImageView(prod.getImage());

            productWindow.getChildren().add(productThumbnail);
            productWindow.setPadding(new Insets(30, 0, 0, 30));
            colCount++;

            productThumbnail.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    productThumbnail.setOpacity(0.8);
                    productThumbnail.setCursor(Cursor.HAND);
                }

            });

            productThumbnail.setOnMouseClicked((MouseEvent event1)
                    -> {

                MainTabPane.getSelectionModel().select(1);
                productPhoto.setImage(prod.getImage());
                priceTag.setText(prod.getPrice() + " KR");
                nameTag.setText(prod.getName());

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

        if (name != null && category != null && gender != null) {
            Webshop.getInstance().createProduct(name, category, gender, price);
            setNameField.clear();
            setPriceField.clear();
            refreshTable();
            handleSearch(new ActionEvent());

        }

    }

    @FXML
    private void returnMainPage(MouseEvent event)
    {
        MainTabPane.getSelectionModel().select(0);
    }
//
//    public void showOrderList()
//    {
//        orderProducts = Webshop.getInstance().getShoppingBasketItems();
//        createOrderList();
//
//        orderPic.setCellValueFactory(new PropertyValueFactory<>("productpic"));
//        orderName.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        orderStr.setCellValueFactory(new PropertyValueFactory<>("productsize"));
//        orderColor.setCellValueFactory(new PropertyValueFactory<>("productcolor"));
//        orderAmount.setCellValueFactory(new PropertyValueFactory<>("itemAmount"));
//        orderPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
//        orderBtn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
//        btnCol.setSortable(false);
//        btnCol.setMinWidth(35);
//        System.out.println(obsOrderProductList);
//        orderTable.setItems(obsOrderProductList);
//
//        btnCol.setCellValueFactory((TableColumn.CellDataFeatures<Record, Boolean> p)
//                -> new SimpleBooleanProperty(p.getValue() != null));
//
//        btnCol.setCellFactory((TableColumn<Record, Boolean> p) -> new ButtonCell());
//
//        productTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//            //Check whether item is selected and set value of selected item to Label
//            if (productTable.getSelectionModel().getSelectedItem() != null) {
//                productIdLabel.setText(productTable.getSelectionModel().getSelectedItem().getProductId() + "");
//                editNameField.setText(productTable.getSelectionModel().getSelectedItem().getName());
//                editCategoryCMB.setValue(productTable.getSelectionModel().getSelectedItem().getCategory());
//                editGenderCMB.setValue(productTable.getSelectionModel().getSelectedItem().getGender());
//                editPriceField.setText(productTable.getSelectionModel().getSelectedItem().getPrice() + "");
//            }
//        });
//    }

    public void showBasketList()
    {
        basketProducts = Webshop.getInstance().getShoppingBasketItems();
        createBasketList();
        ObservableList<String> sizes = FXCollections.observableArrayList("Small", "Medium", "Large", "X-Large");
        
        basketPic.setCellValueFactory(new PropertyValueFactory<>("productpic"));
        basketName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        basketSize.setCellValueFactory(new PropertyValueFactory<>("sizes"));
        basketAmount.setCellValueFactory(new PropertyValueFactory<>("itemAmount"));
        basketPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        basketBtn.setSortable(false);
        basketSize.setSortable(false);
        basketBtn.setMinWidth(35);
        System.out.println(obsBasketProductList);
        basketTable.setItems(obsBasketProductList);

        basketBtn.setCellValueFactory((TableColumn.CellDataFeatures<Record, Boolean> p)
                -> new SimpleBooleanProperty(p.getValue() != null));

        basketBtn.setCellFactory((TableColumn<Record, Boolean> p) -> new ButtonCell(REMOVE_BASKET_PRODUCT));
        basketTable.setEditable(true);
        
        basketSize.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), sizes));
       
        
        



        
        
                
    }

}
