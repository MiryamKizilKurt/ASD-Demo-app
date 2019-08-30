/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd.demo.test;

import asd.demo.model.Users;
import asd.demo.model.dao.MongoDBConnector;
import java.net.UnknownHostException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author George
 */
public class UnitTest {
    private static MongoDBConnector mdb;
       
    public UnitTest() {  }

    @BeforeClass //Create an instance of MongoDBConnector using admin credentials for mLab
    public static void setUpClass() throws UnknownHostException {
        System.out.println("\n<-- Starting Unit test -->");  
        mdb = new MongoDBConnector("<mLab Username>","<mLab Password>");      
    }
    
    @Test
    public void testMongoDBConnect() throws UnknownHostException {
        Assert.assertNotNull("Cannot establish connection to MDB",mdb);        
        System.out.println("\n- Connecting to MongoDB ");
    }
       
    @Test 
    public void fetchUsers(){
        Users users = mdb.loadUsers();
        Assert.assertNotNull("Cannot fetch ASD users",users);
        System.out.println("\nFetching ASD users...");
        System.out.println("-------------------------------------");
        mdb.showall(users);
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.print("\n<-- Unit Test Finished : ");        
    }     
}
