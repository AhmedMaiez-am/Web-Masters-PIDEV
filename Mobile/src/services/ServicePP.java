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
import entities.Pp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static services.ServiceCours.instance;
import utils.Statics;

/**
 *
 * @author maiez
 */
public class ServicePP {
    public static ServicePP instance = null;
    private ConnectionRequest req;
    
    public static ServicePP getInstance(){
        if (instance == null)
        
            instance = new ServicePP();
        return instance;
    }
    public ServicePP(){
        req= new ConnectionRequest();
    }
    public ArrayList<Pp>afficherp()
    {
        ArrayList<Pp> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/displayParent";
        req.setUrl(url);
        
        req.addResponseListener( new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String, Object>mapPp = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> listOfMaps = (List<Map<String,Object>>) mapPp.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps)
                    {
                        Pp c = new Pp();
                        float idp = Float.parseFloat(obj.get("idp").toString());
                        
                        String emailp = obj.get("emailp").toString();
                        
                        String nomp = obj.get("nomp").toString();
                        
                        String prenomp = obj.get("prenomp").toString();
                        
                        String telp = obj.get("telp").toString();
                        
                        String passwordp = obj.get("passwordp").toString();
                        
                        String numcarte = obj.get("numcarte").toString();
                        
                        String passcarte = obj.get("passcarte").toString();
                        
                        String portefeuille = obj.get("portefeuille").toString();
                        
                        //String codep = obj.get("codep").toString();
                        
                        String block = obj.get("block").toString();
                        
                        c.setIdp((int)idp);
                        c.setEmailp(emailp);
                        c.setNomp(nomp);
                        c.setPrenomp(prenomp);
                        c.setTelp(telp);
                        c.setPasswordp(passwordp);
                        c.setNumcarte(numcarte);
                        c.setPasscarte(passcarte);
                        c.setPortefeuille(portefeuille);
                        
                        result.add(c);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
                NetworkManager.getInstance().addToQueueAndWait(req);
                return result;  
    }
}
