/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Contes;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class Servicecontes {
 public ArrayList<Contes> contes;
  public static Servicecontes instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public Servicecontes() {
         req = new ConnectionRequest();
    }

    public static Servicecontes getInstance() {
        if (instance == null) {
            instance = new Servicecontes();
        }
        return instance;
    }   
 
 
 public ArrayList<Contes> parsecontes(String jsonText){
        try {
            contes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Contes c = new Contes();
                c.setIdcontes((int)Double.parseDouble(obj.get("idconte").toString()));
                c.setTitre(obj.get("titre").toString());
                c.setAuteur(obj.get("auteur").toString());
               
                //u.setDescription(obj.get("description").toString());
              //  u.setPrix(obj.get("cours").toString());
               // u.setPrix(obj.get("prix").toString());
               // u.setRate((int)Double.parseDouble(obj.get("rate").toString()));
                
       
                contes.add(c);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return contes;
    }
        
     public ArrayList<Contes> getcontes(){
        String url = Statics.BASE_URL+"contes/_wdt/8fa9d5";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                contes = parsecontes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return contes;
    }
     
     public boolean addcontes(Contes c) {
//String url = Statics.BASE_URL + "contes/_wdt/6e5560titre=" +c.getTitre()+ "&autuer="c.getAuteur()  + "&rate=" + c.getRate();        
  String url=Statics.BASE_URL +"contes/_wdt/eb6479?titre="+c.getTitre()+"&auteur="+c.getAuteur()+"&contes="+c.getContes()+"&rate="+c.getRate();
          
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
     
     public boolean updatecontes(Contes c) {
   String url = Statics.BASE_URL + "contes/_wdt/4fb61e/"+c.getIdcontes()+"?titre="+c.getTitre() + "&auteur=" +c.getAuteur()+"&contes="+c.getContes()+"&rate=" + c.getRate();
        
        
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
     
     public boolean deletecontes(Contes c) {
   String url = Statics.BASE_URL + "contes/_wdt/c791c6/"+c.getIdcontes();
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
