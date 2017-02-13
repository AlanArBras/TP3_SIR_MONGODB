package fr.istic.master1.sir.tp3;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.mongodb.morphia.annotations.Embedded;


/**
 * Bean d'une adresse composée d'une rue, une ville, un code postal et un pays.
 * Created by alan on 1/18/17.
 */
@Embedded
public class Address implements Cloneable {
    private String street;
    private String city;
    private String postCode;
    private String country;

    public Address() {
    }

    Address(String street, String city, String postCode, String country) {
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    static Address copy(Address address) throws CloneNotSupportedException {
        return (Address) address.clone();
    }

    BSONObject toBson(){
        BasicBSONObject res = new BasicBSONObject();
        res.put("rue", street);
        res.put("ville", city);
        res.put("code postal", postCode);
        res.put("pays", country);
        return res;
    }
}
