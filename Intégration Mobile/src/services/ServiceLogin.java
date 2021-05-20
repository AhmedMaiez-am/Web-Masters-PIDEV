/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entities.Parents;
import gui.ListeEnfant;
import java.io.IOException;
import java.util.Map;
import utils.Statics;
import com.codename1.ui.util.Resources;
import gui.Aziz;
import gui.Home;
import gui.HomeDirecteur;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class ServiceLogin {
       public Parents event ;
     public static ServiceLogin instance=null;
      private ConnectionRequest req;
       private ConnectionRequest reqq;
      Resources res ;
      public static String idparent ;
       public boolean resultOK;
       public ArrayList<Parents> listall , listall1;
    public ArrayList<Parents> tasks , tasks1;

  public ServiceLogin() {
         req = new ConnectionRequest();
    }
        public static ServiceLogin getInstance() {
        if (instance == null) {
            instance = new ServiceLogin();
        }
        return instance;
    }
    
    public  void signin(TextField username ,TextField password  )  {
        
    String url = Statics.BASE_URL+"/user/getPasswordByEmail?emailp="+username.getText().toString()+"&passwordp="+password.getText().toString();
         req.setUrl(url);
         req.addResponseListener((e)-> {
         JSONParser j = new JSONParser();
         String json = new String (req.getResponseData() )+ "" ;
           if (json.equals("[\"false\"]")) {
              
                 Dialog.show("Alter" , "Please fill all the fields" ,new Command("OK")) ;   
             }
             else { 
             System.out.println("data"+json);
               //   Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                 //System.out.println(user);
                // if (json.equals("[\"true\"]") )
                      Dialog.show("Connexion" , "Hello" ,new Command("OK")) ;
                   new Home(res).show();
                   ServiceLogin.idparent = json;
                    System.out.println(idparent);
             } 
         } );
             
     NetworkManager.getInstance().addToQueueAndWait(req); 
}
     public boolean updateParents (String id , Parents e){
      //  id = String.valueOf(ServiceLogin.idparent);
      id = ServiceLogin.idparent ;
        String url = Statics.BASE_URL+"/UpdateParent/"+id+"?emailp="+e.getEmailP()+"&nomp="+e.getNomP()+"&prenomp="+e.getPrenomP()+"&telp="+e.getTelP()+"&passwordp="+e.getPasswordP();
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
        return resultOK;
    }
      public  void signinDirecteur (TextField tfLogin ,TextField tfPassword )  {
        
    String url = Statics.BASE_URL+"/user/loginDirecteur?login="+tfLogin.getText().toString()+"&password="+tfPassword.getText().toString();
         req.setUrl(url);
         req.addResponseListener((e)-> {
         JSONParser j = new JSONParser();
         String json = new String (req.getResponseData() )+ "" ;
         
           if (json.equals("[\"false\"]")) {
              
                 Dialog.show("Alter" , "Please fill all the fields" ,new Command("OK")) ;   
             }
             else { 
             System.out.println("data"+json);
              
                      Dialog.show("Connexion" , "Hello" ,new Command("OK")) ;
                   new HomeDirecteur(res).show();
                   
             } 
         } );
             
     NetworkManager.getInstance().addToQueueAndWait(req); 
}/*
        public ArrayList<Parents> listAll (String jsonText){
        try {
            tasks1=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             Parents t = new Parents ();
         
               t.setEmailP(obj.get("Email").toString());
               t.setNomP(obj.get("Nom ").toString());
               t.setPrenomP(obj.get("PreNom ").toString());
           
               tasks1.add(t);
                
            }
            
        } catch (IOException ex) {
            
        }
        return tasks1;
    }
     public ArrayList<Parents> getListall(String id){ 
          id = ServiceLogin.idparent ;
          ConnectionRequest con = new ConnectionRequest();
       
        String url = statics.BASE_URL+"/Consulter/"+id;
        
        
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceLogin ser = new ServiceLogin();
                listall1 = ser.listAll(new String(con.getResponseData()));
                        System.out.println(listall);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall1;
    } */
     public Parents parseOneEventJson(String jsonText) {
       
        try {
            event = new Parents();
            JSONParser j = new JSONParser();
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray())); 
         Parents w = new Parents();
           //    ArrayList<Parents> ac = new ArrayList<>();
                w.setEmailP(eventsListJson.get("emailp").toString());
                w.setNomP(eventsListJson.get("nomp").toString());
                 w.setPrenomP(eventsListJson.get("prenomp").toString());
                  w.setTelP(eventsListJson.get("telp").toString());
                event =w;
        } 
         catch (IOException ex) {
        } 
        return event;
        
    }
     public Parents getEvent(String id){
         
         id = ServiceLogin.idparent ;
        String url = Statics.BASE_URL+"/Consulter/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(req.getResponseData());
                event = parseOneEventJson(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;
    }
      public  void signinTuteur(TextField tfLogin ,TextField tfPassword )  {
        
    String url = Statics.BASE_URL+"/user/loginTuteur?loginm="+tfLogin.getText().toString()+"&passwordm="+tfPassword.getText().toString();
         req.setUrl(url);
         req.addResponseListener((e)-> {
         JSONParser j = new JSONParser();
         String json = new String (req.getResponseData() )+ "" ;
         
           if (json.equals("[\"false\"]")) {
              
                 Dialog.show("Alter" , "Please fill all the fields" ,new Command("OK")) ;   
             }
             else { 
             System.out.println("data"+json);
              
                      Dialog.show("Connexion" , "Hello" ,new Command("OK")) ;
                   new Aziz(res).show();
                   
             } 
         } );
             
     NetworkManager.getInstance().addToQueueAndWait(req); 
}
}
