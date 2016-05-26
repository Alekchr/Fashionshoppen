package services;

import products.Order;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRequest
{

    String url = "jdbc:postgresql://localhost:5432/Fashionshoppen";
    String user = "postgres";
    String password = "Snuden123";
    Connection con = null;
    ResultSet rs;
    Statement st;
    String query;
    String query2;

    public ServerRequest()
    {

        connect();
    }

    private void connect()
    {
        try
        {
            con = DriverManager.getConnection(url, user, password);

        }
        catch (SQLException ex)
        {
            Logger lgr = Logger.getLogger(ServerRequest.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);

        }

    }

    public void browseCategory(String query, String name)
    {

        String finalQuery;
        if (name.isEmpty())
        {
            finalQuery = query;
        }
        else
        {
            finalQuery = query + "' AND LOWER(product_name) LIKE LOWER('%" + name + "%')";
        }
        rs = runRSQuery(finalQuery);

    }

    public ResultSet browseProductName(String name)
    {
        query = "SELECT * FROM products WHERE LOWER(product_name) LIKE LOWER('%" + name + "%')";
        runRSQuery(query);
        return rs;
    }

    public ResultSet getProducts()
    {

        query = "SELECT * from products";
        runRSQuery(query);
        return rs;
    }

    public void deleteProduct(int productId)
    {
        query = "DELETE FROM products WHERE product_id = " + productId;
        runQuery(query);
    }

    //Denne metode registrer en bruger, og sørger for at det samme user_id bliver gemt i alle database tables, så de senere kan ændres.
    public void registerUser(String firstName, String lastName, String email, String password)
    {

        query = "INSERT INTO users (firstname, lastname, email, password) VALUES (" + "'"
                + firstName + "', " + "'" + lastName + "', " + "'" + email + "', " + "'" + password + "')";

        runQuery(query);

        query = "INSERT INTO customer (user_id) VALUES ('" + findUserID(email) + "')";

        runQuery(query);

        query = "INSERT INTO address (user_id) VALUES (" + findUserID(email) + ")";

        runQuery(query);

    }

    public void saveGuestCustomer(String firstName, String lastName, String email, String streetName, 
            String houseNumber, String zipcode, String shippingCity){
        query = "INSERT INTO users (firstname, lastname, email) VALUES (" + "'"
                + firstName + "', " + "'" + lastName + "', " + "'" + email + "')"; 
        runQuery(query);
        
        query = "INSERT INTO customer (user_id) VALUES ('" + findUserID(email) + "')";

        runQuery(query);
        
        query = "INSERT INTO address (user_id, streetname, housenumber, zipcode, city) VALUES ('"
                + findUserID(email) + "', '" + streetName + "', '" + houseNumber + "', '" + zipcode + "', '" + shippingCity + "')";
        
        runQuery(query);
    }
    
    public ResultSet findCustomerAddress(String email)
    {     //Returnerer et resultset, som bruges til at oprette en instans
        //af addresse til den bruger der har logget ind.

        int user_id = findUserID(email);

        query = "SELECT * FROM Address WHERE user_id ='" + user_id + "'";
        runRSQuery(query);

        return rs;
    }

    public int findUserID(String email)     //Metode til at finde user_id ud fra email, bruges når der logges ind med email.
    {
        int user_id = 0;
        try
        {
            st = con.createStatement();
            query = "SELECT user_id FROM users WHERE email ='" + email + "'";
            rs = st.executeQuery(query);

            while (rs.next())
            {
                user_id = rs.getInt("user_id");
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, e);
        }

        return user_id;

    }

    public ResultSet loginUser(String email, String password)
    {

        query = "SELECT * FROM users WHERE LOWER(email) = LOWER('" + email + "') AND password = '" + password + "'";

        runRSQuery(query);
        return rs;
    }

    //Checker om det er en customer eller employee der har logget ind, og returnerer ResultSet fra det table som matcher.
    public ResultSet checkLoginType(String email)
    {
        int user_id = 0;
        try
        {

            query = "SELECT user_id FROM users WHERE LOWER(email) = LOWER('" + email + "')";
            runRSQuery(query);

            while (rs.next())
            {
                user_id = rs.getInt("user_id");

            }
            query = "SELECT * FROM employee WHERE user_id = '" + user_id + "'";
            rs = st.executeQuery(query);
            if (rs == null)
            {
                query = "SELECT * FROM customer WHERE user_id = '" + user_id + "'";
                rs = st.executeQuery(query);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rs;

    }

    //    public void createOrder(){
//        query = "INSERT INTO TABLE orders ()"
//    
//    }
    public void createProduct(String name, String category, String gender, Double price)
    {

        query = "INSERT INTO products(product_name, product_category, product_gender, product_price) VALUES('" + name + "', '" + category + "', '" + gender + "', '" + price + "');";
        runQuery(query);

    }

    public void editProductName(int productId, String name)
    {
        query = "UPDATE products SET product_name = '" + name + "' WHERE product_id = '" + productId + "';";
        runQuery(query);
    }

    public void editProductCategory(int productId, String category)
    {
        query = "UPDATE products SET product_category = '" + category + "' WHERE product_id = '" + productId + "';";
        runQuery(query);
    }

    public void editProductGender(int productId, String gender)
    {
        query = "UPDATE products SET product_gender = '" + gender + "' WHERE product_id = '" + productId + "';";
        runQuery(query);
    }

    public void editProductPrice(int productId, Double price)
    {
        query = "UPDATE products SET product_price = '" + price + "' WHERE product_id = '" + productId + "';";
        runQuery(query);
    }

    public void editProductPicture(int productId, String imagePath)
    {
        query = "UPDATE products SET product_image_path = '" + imagePath + "' WHERE product_id = '" + productId + "';";
        runQuery(query);
    }

    public void runQuery(String query)
    {
        try
        {
            st = con.createStatement();
            st.execute(query);

        }
        catch (SQLException e)
        {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ResultSet runRSQuery(String query)
    {
        try
        {
            st = con.createStatement();
            rs = st.executeQuery(query);

        }
        catch (SQLException e)
        {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }

    public void editOrderStatus(int orderId, String status){
        query = "UPDATE orders SET status = '" + status + "' WHERE order_id = '" + orderId + "';";
        runQuery(query);
    }
    
    public ResultSet getOrders(){
        query = "SELECT * FROM orders";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            

        } catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getCustomerNameFromId(int userId){
        query = "SELECT firstname, lastname FROM users WHERE user_id = '" + userId + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet selectAddressFromId(int userId){
        query = "SELECT * FROM address WHERE user_id = '" + userId + "';";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
    
    public void storeOrder(Order order, int user_id)
    {
        query = "INSERT INTO orders (order_date, price, shippingcharge, finalprice, paymentOption, user_id, status)"
                + "VALUES ( '" + order.getOrder_date() + "', '" + order.getPrice() + "', '" + order.getShippingCharge()
                + "', '" + order.getFinalPrice() + "', '" + order.getPayment_option() + "', '" + user_id + "', '" + "CONFIRMED" + "');";
        runQuery(query);

    }

}
