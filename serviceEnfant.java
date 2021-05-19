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
import java.util.ArrayList;
import entites.Enfant ;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import utils.statics;


/**
 *
 * @author HP
 */
public class serviceEnfant {
    public static serviceEnfant instance=null;
    public ArrayList<Enfant> listall;
    public ArrayList<Enfant> tasks;
 public boolean resultOK;
    private ConnectionRequest req;
    public serviceEnfant() {
         req = new ConnectionRequest();
    }
        public static serviceEnfant getInstance() {
        if (instance == null) {
            instance = new serviceEnfant();
        }
        return instance;
    }
    
            
       
    public boolean addEnfant(Enfant e){
        
         String url = statics.BASE_URL +"/ajouter?nomenfant="+e.getNomenfant()+"&prenomenfant="+e.getPrenomenfant()+"&age="+e.getAge()+"&idparent="+e.getIdparent()+"&password="+e.getPassword();
         req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
     }
     
      public ArrayList<Enfant> listAll(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               Enfant t = new Enfant ();
         
                t.setNomenfant(obj.get("nomenfant").toString());
                t.setPrenomenfant(obj.get("prenomenfant").toString());
                t.setIde(((int)Float.parseFloat(obj.get("ide").toString())));
               // t.setIdparent((int) obj.get("idparent"));
              //  t.setPassword(obj.get("password").toString());
              //  t.setImage(obj.get("image").toString());
                tasks.add(t);

            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
       public ArrayList<Enfant> getListalls(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(statics.BASE_URL +"/APIenfant");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                serviceEnfant ser = new serviceEnfant();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
      public boolean supprimer(String id){
        
        String url = statics.BASE_URL+"/remove/"+id;
        
        
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
}
