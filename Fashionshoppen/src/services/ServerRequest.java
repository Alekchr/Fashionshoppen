package services;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerRequest {

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
        try {
            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ServerRequest.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);

        }

    }

    public void browseCategory(String query, String name)
    {
        String finalQuery;
        if (name.isEmpty()) {
            finalQuery = query;
        } else {
            finalQuery = query + "' AND LOWER(product_name) LIKE LOWER('%" + name + "%')";
        }

        try {
            st = con.createStatement();
            rs = st.executeQuery(finalQuery);

            while (rs.next()) {
                System.out.println(rs.getString(2) + rs.getString(3) + rs.getDouble(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet browseProductName(String name)
    {
        query = "SELECT * FROM products WHERE LOWER(product_name) LIKE LOWER('%" + name + "%')";

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString(2) + rs.getString(3) + rs.getDouble(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getProducts(){
        
        query = "SELECT * from products";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    //Denne metode registrer en bruger, og sørger for at det samme user_id bliver gemt i alle database tables, så de senere kan ændres.
    public void registerUser(String firstName, String lastName, String email, String password)  
    {
        try {
            st = con.createStatement();
            query = "INSERT INTO users (firstname, lastname, email, password) VALUES (" + "'"
                    + firstName + "', " + "'" + lastName + "', " + "'" + email + "', " + "'" + password + "')";

            st.execute(query);

            
            query = "INSERT INTO customer (user_id, phone) VALUES ('" + findUserID(email) + "',"
                    + " ' ' )";
                    
            st.execute(query);
            
            query = "INSERT INTO address (user_id) VALUES (" + findUserID(email) +")";  
                    
            st.execute(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet findCustomerAddress(String email){     //Returnerer et resultset, som bruges til at oprette en instans
                                                            //af addresse til den bruger der har logget ind.
        
        int user_id = findUserID(email);
        
        try
        {    
            st = con.createStatement();
            query = "SELECT * FROM Address WHERE user_id ='" + user_id + "'";
            rs = st.executeQuery(query);
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            
            while(rs.next())
            {
                user_id = rs.getInt("user_id");
            }   
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_id;
    } 
            
    public ResultSet loginUser(String email, String password)
    {

        try {

            st = con.createStatement();
            String query = "SELECT * FROM users WHERE LOWER(email) = LOWER('" + email + "') AND password = '" + password + "'";

            rs = st.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    //Checker om det er en customer eller employee der har logget ind, og returnerer ResultSet fra det table som matcher.
    public ResultSet checkLoginType(String email)
    {
        int user_id = 0;
        try
        {

            st = con.createStatement();
            query = "SELECT user_id FROM users WHERE LOWER(email) = LOWER('" + email + "')";
            rs = st.executeQuery(query);

            while (rs.next())
            {
                user_id = rs.getInt("user_id");
                
            }
            query = "SELECT * FROM employee WHERE user_id = '" + user_id + "'";
            rs = st.executeQuery(query);
            if(rs == null)
            {
            query = "SELECT * FROM users WHERE user_id = '" + user_id + "'";
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
}
