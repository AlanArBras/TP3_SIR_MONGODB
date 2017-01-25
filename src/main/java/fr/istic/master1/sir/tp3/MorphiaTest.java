package fr.istic.master1.sir.tp3;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by alan on 1/18/17.
 */
public class MorphiaTest {

    public static void main( String[] args ) throws UnknownHostException
    {
        Morphia morphia = new Morphia();
        MongoClient mongo = new MongoClient();
        morphia.map(Person.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "my_database");

        Person tintin = new Person();
        tintin.setName("Tintin");
        //set address
        tintin.createAddress("123 Some street","Some city","123 456","Some country");
        ds.save(tintin);

        Person gwendal = new Person();
        gwendal.setName("Gwendal");
        //set address
        gwendal.createAddress("321 Any street","Any city","654 321","Any country");
        ds.save(gwendal);

        // Save the POJO
        Article article = new Article("banane",3);
        article.addBuyer(gwendal);
        article.addBuyer(tintin);

        ds.save(article);
        for (Article a : ds.find(Article.class))
            System.err.println(a.toBson());
    }
}
