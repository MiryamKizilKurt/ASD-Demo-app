/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd.demo.model.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.io.Serializable;

/**
 *
 * @author George
 */
public class MongoDBClientImpl implements Serializable{
    private MongoClient client;
    
    public MongoDBClientImpl(MongoClientURI uri){
        client = new MongoClient(uri);
    }
    
    public MongoClient getMongoClient(){return client;}
}
