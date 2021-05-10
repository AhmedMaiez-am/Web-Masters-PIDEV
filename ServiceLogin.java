/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import entites.Parents;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceLogin {
       public static Parents loggedUser = null;
    
    public static Parents getLoggedUser(){
        return loggedUser;
    }
    
    public static void setLoggedInUser(Parents loggedInUser) {
        ServiceLogin.loggedUser = loggedInUser;
    }
    
   /* public static String hashPassword(String password_plaintext)
    {
        int workload = 13;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return hashed_password;
    }*/

    
    public static int recupererUser(String username, String password){
        int existe = 2;
        Map<String, Object> result;
        Parents loggedin = new Parents();
        String url = "http://localhost/Jardin/web/app_dev.php/api/getUser?username=" + username;
        ConnectionRequest request = new ConnectionRequest(url,false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        JSONParser j = new JSONParser();
        String json = new String(request.getResponseData()) + "";
        try{
            result = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if(result.isEmpty() ){
                System.out.println("user not found");
                existe = 1;
            }
            
           
            else{
                int id = (int)Double.parseDouble(result.get("id").toString());
                String db_pass  = (String)result.get("password");
             
                    loggedin.setIdP(id);
                    loggedin.setEmailP(result.get("username").toString());
                    loggedin.setPasswordP(result.get("email").toString());
                    loggedUser = loggedin;
                    existe = 0;
            System.out.println("user correct, password correct");

               
                
            }
            
        }
        catch (IOException ex) {
                ex.printStackTrace();
            }
        return existe;
    }
    
    
}
