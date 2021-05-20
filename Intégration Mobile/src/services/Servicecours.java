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
import com.codename1.ui.events.ActionListener;
import entities.Contes;
import entities.Cours;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author asus
 */
public class Servicecours {
     public ArrayList<Cours> cours;
     public ArrayList<Contes> contes;
    public static Servicecours instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public Servicecours() {
         req = new ConnectionRequest();
    }

    public static Servicecours getInstance() {
        if (instance == null) {
            instance = new Servicecours();
        }
        return instance;
    }
    public boolean addUser(Cours c) {
String url = Statics.BASE_URL + "/cours/_wdt/6e5560?nom=" +c.getNom()+ "&type="+ c.getType()+ "&description=" + c.getDescription() + "&cours=" + c.getCours() + "&prix=" + c.getPrix() + "&rate=" + c.getRate();        
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
public boolean deletecours(Cours c) {
   String url = Statics.BASE_URL + "/cours/_wdt/c04215/"+c.getIdc();
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
    
   public boolean updatecours(Cours c) {
   String url = Statics.BASE_URL + "/cours/_wdt/32de06/"+c.getIdc()+"?nom="+c.getNom() + "&type=" +c.getType()+"&description="+c.getDescription()+"&cours="+c.getCours() +"&prix=" + c.getPrix()+ "&rate=" + c.getRate();
        
        
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
     /*
                   
     public ArrayList<Cours> affichagecours(){
      ArrayList<Cours>cours=new ArrayList();
         String url = Statics.BASE_URL+"cours/_wdt/b0ac5a";
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              JSONParser jsonp;
         jsonp=new JSONParser();
         try{
             Map<String,Object>MapReclamation=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
         List<Map<String,Object>>list = (List<Map<String,Object>>)MapReclamation.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){ 
                //Création des tâches et récupération de leurs données
                Cours c = new Cours();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int)id);
                c.setNom(obj.get("nom").toString());
                c.setType(obj.get("type").toString());
                c.setDescription(obj.get("decription").toString());
               // c.setCours(obj.get("cours").toString());
                c.setPrix(obj.get("prix").toString());
                 float rate = Float.parseFloat(obj.get("rate").toString());
                c.setRate((int)rate);  
                //Ajouter la tâche extraite de la réponse Json à la liste
               cours.add(c);
         
         
            }
         }      catch (IOException ex) { 
                    ex.printStackTrace();
                }
 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }*/
    
    
    
    
 public ArrayList<Cours> parseUsers(String jsonText){
        try {
            cours=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Cours u = new Cours();
                u.setIdc((int)Double.parseDouble(obj.get("idc").toString()));
                u.setNom(obj.get("nom").toString());
                u.setType(obj.get("type").toString());
                u.setDescription(obj.get("description").toString());
              //  u.setPrix(obj.get("cours").toString());
                u.setPrix(obj.get("prix").toString());
               // u.setRate((int)Double.parseDouble(obj.get("rate").toString()));
                
       
                cours.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return cours;
    }
        
        
    public ArrayList<Cours> getAllUsers(){
        String url = Statics.BASE_URL+"/cours/_wdt/b0ac5a";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }

public void searchhh(Cours c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/cours/searchStudentx";
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }


   
     
    

}
         
         
              
        
                                
       
   
                 
                 

