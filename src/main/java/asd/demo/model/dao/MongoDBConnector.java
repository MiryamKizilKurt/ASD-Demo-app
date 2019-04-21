/*
 * Author: Georges Bou Ghantous
 *
 * This class provides the methods to establish connection between maven-app-heroku
 * and MongoDBLab cloud Database and save IoT generated data at button click on injex.jsp.
 * The python scripts excuted remotely using SSHExec.java methods on RPIB collect data from
 * sensors (Sensor_A and Sensor_B). The data is saved dynamically on mLab cloud database as
 * as JSON format.
 */
package asd.demo.model.dao;

import java.net.UnknownHostException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.*;
import asd.demo.model.*;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnector {

    private List<Document> users = new ArrayList();
    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase db;
    private String owner;
    private String password;

    public MongoDBConnector(String owner, String password) throws UnknownHostException {
        this.owner = owner;
        this.password = password;
        connect();
    }

    public static void main(String[] args) throws UnknownHostException {
        MongoDBConnector db = new MongoDBConnector("Georges034302", "darkside666");
        db.connect();
        db.showUsers();
        User user = db.user("john.smith@uts.com","helloworld123");
        System.out.println("User email: " + user.getEmail());
        db.showall(db.loadUsers());
    }

    public void showall(Users users) {
        for (User u : users.getList()) {
            System.out.println(u.getName());
        }
    }

    private void connect() throws UnknownHostException {
        uri = new MongoClientURI("mongodb://" + owner + ":" + password + "@ds029496.mlab.com:29496/heroku_59pxdn6j");
        client = new MongoClient(uri);
        db = client.getDatabase(uri.getDatabase());
    }

    public void add(User user) {
        users.add(new Document("Username", user.getEmail()).append("Password", user.getPassword()).append("Name", user.getName()).append("Phone", user.getPhone()));
        MongoCollection<Document> userlist = db.getCollection("ASD-Demo-app-users");
        userlist.insertMany(users);
    }

    public void showUsers() {
        MongoCollection<Document> userlist = db.getCollection("ASD-Demo-app-users");
        try (MongoCursor<Document> cursor = userlist.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    public Users loadUsers() {
        Users users = new Users();
        MongoCollection<Document> userlist = db.getCollection("ASD-Demo-app-users");
        for (Document doc : userlist.find()) {
            User user = new User((String) doc.get("Name"), (String) doc.get("Username"), (String) doc.get("Password"), (String) doc.get("Phone"));
            users.addUser(user);
        }
        return users;
    }

    public User user(String email,String password) {
        MongoCollection<Document> userlist = db.getCollection("ASD-Demo-app-users");
        Document doc = userlist.find(and(eq("Username", email),eq("Password",password))).first();
        User user = new User((String) doc.get("Name"), (String) doc.get("Username"), (String) doc.get("Password"), (String) doc.get("Phone"));
        return user;
    }

    public void close() {
        client.close();
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getMDB() {
        return db;
    }
}
