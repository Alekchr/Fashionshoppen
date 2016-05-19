
package Domain;

/**
 *
 * @author aleksander
 */


public class Address
{

    private int customer_id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;

    public Address(int customer_id, String streetName, String houseNumber, String zipCode, String city)
    {
        this.customer_id = customer_id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public String getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    
    
}
