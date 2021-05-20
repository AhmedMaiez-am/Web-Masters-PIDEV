/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import entities.Reclamation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceReclamation {
    
     public static ServiceReclamation instance = null;
     private ConnectionRequest req;
     public static boolean resultOK=true;
     
     public static ServiceReclamation getInstance(){
         if(instance == null)
             instance=new ServiceReclamation(); 
             return instance;
     }
     
    public ServiceReclamation()
    {
        req=new ConnectionRequest();
    }
    
    public void ajouterReclamation(Reclamation reclamation){
          String url = Statics.BASE_URL + "/addReclamation?nom="+ reclamation.getNom()+ "&prenom=" + reclamation.getPrenom()+ "&mail=" + reclamation.getEmail()+ "&reclamation=" + reclamation.getReclamation()+ "&type=" + reclamation.getType()+ "&etat=" + reclamation.getEtat(); //création de l'URL
        
          req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((e)->
        {String str= new String (req.getResponseData());
            System.out.println("data== "+str);
        });
            
        NetworkManager.getInstance().addToQueueAndWait(req);
  
    }
     public ArrayList<Reclamation> AfficherReclamations(){
        
         ArrayList<Reclamation> result = new ArrayList<>();
         String url=Statics.BASE_URL+"/displayReclamations";
         req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
            JSONParser jsonp;
            jsonp= new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

              try {
            Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapReclamations.get("root");
            
            
            for(Map<String,Object> obj : listOfMaps){
                
                Reclamation re = new Reclamation();
                float id = Float.parseFloat(obj.get("idr").toString());
                String nom = (obj.get("nom").toString());
                String prenom = (obj.get("prenom").toString());
                String mail = (obj.get("mail").toString());
                String reclamation = (obj.get("reclamation").toString());
                String etat = (obj.get("etat").toString());
                String type = (obj.get("type").toString());
                
                re.setIdr((int)id);
                re.setNom(nom);
                re.setPrenom(prenom);
                re.setEmail(mail);
                re.setReclamation(reclamation);
                re.setEtat(etat);
                re.setType(type);
                
                
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
     
     public Reclamation DetailReclamation( int id, Reclamation reclamation)
     {
       
         String url=Statics.BASE_URL+"/detailReclamation?"+id;
         req.setUrl(url);
         String str= new String (req.getResponseData());
         req.addResponseListener(((evt)-> {
   
          
            JSONParser jsonp = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

              try {
            Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
            
            
            
          
                
                Reclamation re = new Reclamation();
                
                re.setNom(obj.get("nom").toString());
                re.setPrenom(obj.get("prenom").toString());
                re.setEmail(obj.get("mail").toString());
                re.setReclamation(obj.get("reclamation").toString());
                re.setEtat(obj.get("etat").toString());
                re.setType(obj.get("type").toString());
       
        } catch (IOException ex) {
           // ex.printStackTrace();
                  System.out.println("erreur"+ex.getMessage());
        }
             System.out.println("data=="+str);
           
         }));
       NetworkManager.getInstance().addToQueueAndWait(req);
         
        return reclamation;
     }
     
  public boolean deleteReclamation (int id){
      String url=Statics.BASE_URL+"/deleteReclamation?id="+id;
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
