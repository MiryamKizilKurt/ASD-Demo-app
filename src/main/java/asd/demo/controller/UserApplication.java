
package asd.demo.controller;

import java.io.*;

import javax.xml.bind.*;
import asd.demo.model.Users;

public class UserApplication implements Serializable {

    private String filePath;
    private Users users;
    
    public UserApplication() {  }

    public UserApplication(String filePath, Users users) {
        super();
        this.filePath = filePath;
        this.users = users;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Unmarshaller u = jc.createUnmarshaller();
        this.filePath = filePath;        
        FileInputStream fin = new FileInputStream(filePath);
        users = (Users) u.unmarshal(fin); 		
        fin.close();
    }    
    public void saveUsers() throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        FileOutputStream fout = new FileOutputStream(filePath);
        m.marshal(users, fout);
        fout.close();
    }

    public void updateXML(Users users, String filePath) throws Exception {
        this.users = users;
        this.filePath = filePath;
        JAXBContext jc = JAXBContext.newInstance(Users.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        FileOutputStream fout = new FileOutputStream(filePath);
        m.marshal(users, fout);
        fout.close();
    }
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}

