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
import utils.Statics;
import entities.Conseil;
import entities.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceConseilForm {
    
     public static ServiceConseilForm instance = null;
     private ConnectionRequest req;
     public static boolean resultOK=true;
     
     public static ServiceConseilForm getInstance(){
         if(instance == null)
             instance=new ServiceConseilForm(); 
             return instance;
     }
     
    public ServiceConseilForm()
    {
        req=new ConnectionRequest();
    }
    
    public void ajouterConseil(Conseil con){
          String url = Statics.BASE_URL + "/addConseil?nom="+ con.getNomc()+ "&prenom=" + con.getPrenomc()+ "&conseil=" + con.getConseil(); //création de l'URL
        
          req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((e)->
        {String str= new String (req.getResponseData());
            System.out.println("data== "+str);
        });
            
        NetworkManager.getInstance().addToQueueAndWait(req);
  
    }
     public ArrayList<Conseil> AfficheConseils(){
        
         ArrayList<Conseil> result = new ArrayList<>();
         String url=Statics.BASE_URL+"/displayConseils";
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
            JSONParser jsonp;
            jsonp= new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

              try {
            Map<String,Object> mapConseils = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapConseils.get("root");
            
            
            for(Map<String,Object> obj : listOfMaps){
                
                Conseil re = new Conseil();
                float id = Float.parseFloat(obj.get("idc").toString());
                String nomc = (obj.get("nom").toString());
                String prenomc = (obj.get("prenom").toString());
                String conseil = (obj.get("conseil").toString());
               
                
                re.setIdc((int)id);
                re.setNomc(nomc);
                re.setPrenomc(prenomc);
                re.setConseil(conseil);
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                result.add(re);
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        }

             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
         
        return result;
       
    }
     
     public Conseil DetailConseil( int id, Conseil conseil)
     {
       
         String url=Statics.BASE_URL+"/detailConseil?"+id;
         req.setUrl(url);
         String str= new String (req.getResponseData());
         req.addResponseListener(((evt)-> {
   
          
            JSONParser jsonp = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

              try {
            Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
            
            
            
          
                
                Conseil re = new Conseil();
                
                re.setNomc(obj.get("nomc").toString());
                re.setPrenomc(obj.get("prenomc").toString());
                re.setConseil(obj.get("Conseil").toString());
             
       
        } catch (IOException ex) {
           // ex.printStackTrace();
                  System.out.println("erreur"+ex.getMessage());
        }
             System.out.println("data=="+str);
           
         }));
       NetworkManager.getInstance().addToQueueAndWait(req);
         
        return conseil;
     }
     
  public boolean deleteConseil (int id){
      String url=Statics.BASE_URL+"/deleteConseil?id="+id;
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 req.removeResponseCodeListener(this);
             }});
           NetworkManager.getInstance().addToQueueAndWait(req);
         
        return resultOK;

  }
  
    
}
