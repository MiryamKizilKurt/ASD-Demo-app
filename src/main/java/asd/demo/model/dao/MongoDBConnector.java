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

public class MongoDBConnector {
    
    private List<Document> users = new ArrayList();
    private MongoClientURI uri ;
    private MongoClient client ;
    private MongoDatabase db;
    private String owner = "Georges034302";
    private String password = "darkside666";
    public MongoDBConnector() throws UnknownHostException { connect(); }
    
    private void connect() throws UnknownHostException{
        uri  = new MongoClientURI("mongodb://Georges034302:darkside666@ds029496.mlab.com:29496/heroku_59pxdn6j"); 
        client = new MongoClient(uri);
        db = client.getDatabase(uri.getDatabase());
    }
    public void add(User user){
        users.add(new Document("Username",user.getEmail()).append("Password", user.getPassword()).append("Name", user.getName()).append("Phone", user.getPhone()));
        MongoCollection<Document> userlist = db.getCollection("ASD-Demo-app-users");
        userlist.insertMany(users);
    }    
      
    public void close(){ client.close(); }    
    public MongoClient getClient(){ return client; }
    public MongoDatabase createMDB(){ return db; }
}
