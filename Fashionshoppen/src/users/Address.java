
package users;

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


    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }



    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }



    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }



    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    
    
}
