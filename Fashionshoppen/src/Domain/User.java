package Domain;

import services.ServicesFacade;

/**
 *
 * @author aleksander
 */
public abstract class User implements UserManager
{
    private int user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    ServicesFacade sf = new ServicesFacade();

    public User(String firstName, String lastName, String email, String password)
    {
        this.user_id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }
    
    public User(String email, String password)
    {
        this("", "", email, password);
        
    }
    
    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


}
