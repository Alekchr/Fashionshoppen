package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ProgressMonitor;

public class ServerRequest
{

    String url = "jdbc:postgresql://localhost:5432/Fashionshoppen";
    String user = "aleksander";
    String password = "a123456LA";
    Connection con = null;
    ResultSet rs;
    Statement st;
    String query;
    String query2;
    
    public ServerRequest()
    {
        //ProgressMonitor progressMonitor = new ProgressMonitor(null, "Please wait", "Loading", 0, 100);
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
    
        public void browseCategory(String category, String name){
        query = "SELECT * FROM products WHERE product_category = '" + category + "' AND LOWER(product_name) LIKE LOWER('%" + name + "%')";
        
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            
            while (rs.next()) {
                System.out.println(rs.getString(2) + rs.getString(3) + rs.getDouble(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void browseProductName(String name){
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
    }

    public void registerUser(String firstName, String lastName, String email, String password)
    {
        try
        {
            st = con.createStatement();
            query = "INSERT INTO users (firstname, lastname, email, password) VALUES (" + "'"
                    +  firstName + "', " + "'" + lastName + "', " + "'" + email + "', " + "'" + password + "')";

            st.execute(query);

        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet loginUser(String email, String password)
    {

        try
        {

            st = con.createStatement();
            String query = "SELECT * FROM users WHERE LOWER(email) = LOWER('" + email + "') AND password = '" + password + "'";

            rs = st.executeQuery(query);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    } 


    public ResultSet checkLoginType(String email)
    {
        try
        {

            st = con.createStatement();
            query = "SELECT * FROM customer WHERE LOWE R(email) = LOWER('" + email + "')";
            rs = st.executeQuery(query);
            if(rs == null)
            {
            query = "SELECT * FROM customer WHERE LOWER(email) = LOWER('" + email + "')";
            rs = st.executeQuery(query);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
    return rs;
    }
}
