package fr.istic.master1.sir.tp3;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean d'un article composé d'un nom, un nombre d'étoiles et une liste d'acheteurs.
 * Created by alan on 1/18/17.
 */
@Entity
public class Article {
    @Id
    private ObjectId objectId;
    private String name;
    private int stars;
    private List<Person> buyers;

    public Article() {
    }

    Article(String name, int stars) {
        this.name = name;
        this.stars = stars;
        buyers = new ArrayList<Person>();
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Person> getBuyers() {
        return buyers;
    }

    boolean addBuyer(Person buyer) {
        if (buyer == null)
            return false;
        else
            buyers.add(buyer);
        return true;
    }

    public void setBuyers(List<Person> buyers) {
        this.buyers = buyers;
    }

    BSONObject toBson() {
        BasicBSONObject res = new BasicBSONObject();
        res.put("objectId", objectId.toString());
        res.put("name", name);
        res.put("stars", stars);
        BasicBSONObject buyersBSON = new BasicBSONObject();
        for (Person p : buyers)
            buyersBSON.put(p.getObjectId().toString(), p.toBson());
        res.put("buyers", buyersBSON);
        return res;
    }
}
