/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Questions;
import entities.Quiz;
import utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author IHEB
 */
public class ServiceQuiz {

    public ArrayList<Quiz> listall;
  
    public ArrayList<Quiz> tasks;
  
    public static ServiceQuiz instance = null;
   public boolean resultOK;
    
    private ConnectionRequest req;

    public ServiceQuiz() {
        req = new ConnectionRequest();
    }

    public static ServiceQuiz getInstance() {
        if (instance == null) {
            instance = new ServiceQuiz();
        }
        return instance;
    }

    public ArrayList<Quiz> listAll(String jsonText) {

        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Quiz t = new Quiz();
         float quizid = Float.parseFloat(obj.get("quizid").toString());
                t.setQuizId((int)quizid);

                t.setTitle(obj.get("title").toString());
                // t.setPrenomenfant(obj.get("prenomenfant").toString());
                float isamericain = Float.parseFloat(obj.get("isamericain").toString());

                t.setIsamericain((int) isamericain);

                // t.setIdparent((int) obj.get("idparent"));
                //  t.setPassword(obj.get("password").toString());
                //  t.setImage(obj.get("image").toString());
                tasks.add(t);

            }

        } catch (IOException ex) {

        }
        return tasks;
    }

    public ArrayList<Quiz> getListalls() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/APIquiz");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceQuiz ser = new ServiceQuiz();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
    




    
    
    public boolean addQuiz(Quiz e){
        
         String url = Statics.BASE_URL +"/ADDQUIZZ/new?title="+e.getTitle()+"&isamericain="+e.getIsamericain();
         req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
     }
    
     
//public boolean supprimerQuiz(int quizid)
//    {
//        String url = Statics.BASE_URL+"/SuppQuiz/"+quizid;
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//               resultOK = req.getResponseCode() == 200;
//                req.removeResponseCodeListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
    
//    
    
//    
    
    public  boolean supprimerQuiz(String id){
    String url=Statics.BASE_URL+"/SuppQuiz/"+id;
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
              req.removeResponseListener(this);
            
        }
        
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    return resultOK;
}
    

}
