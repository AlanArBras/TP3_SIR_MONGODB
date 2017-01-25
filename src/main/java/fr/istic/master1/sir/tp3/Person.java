package fr.istic.master1.sir.tp3;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by alan on 1/18/17.
 */
@Entity
public class Person {
    @Id
    private ObjectId objectId;
    private String name;
    private Address address;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public void createAddress(String street, String city, String postCode, String country) {
        address = new Address(street, city, postCode, country);
    }

    public void createAddress(Address address) throws CloneNotSupportedException {
        this.address = Address.copy(address);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BSONObject toBson() {
        BasicBSONObject res = new BasicBSONObject();
        if (objectId != null)
            res.append("objectId", objectId.toString());
        res.append("name", name);
        res.append("address", address.toBson());
        return res;
    }
}
