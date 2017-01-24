package fr.istic.master1.sir.tp3;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by alan on 1/18/17.
 */
@Entity
public class Article {
    @Id
    private ObjectId id;
    private String name;
    private int stars;

    public Article() {
    }

    public Article(String name, int stars) {
        this.name = name;
        this.stars = stars;
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
}
