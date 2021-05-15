/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Questions;
import Entity.Quiz;

import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
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
public class ServiceQuestion {

    
     public ArrayList<Questions> listall;
    public ArrayList<Questions> tasks;
    public static ServiceQuestion instance = null;
//    public boolean resultOK;
    private ConnectionRequest req;

    
    
    public ServiceQuestion() {

        req = new ConnectionRequest();
    }

    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }

    public boolean addQuestion(Questions t) {

//       String url = Statics.BASE_URL + "/ADDQUESTIONN/" + t.getQuestion()+ "/" + t.getOption1()+"/"+t.getOption2()"/" + t.getOption3()+"/""/" + t.getOption4()+"/""/" + t.getAnswer();
//        req.setUrl(url);
//        req.addResponseListener((e) -> {
//            String str = new String();
//            req.getResponseData();
//            
//           System.out.println("data==" + str);
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
        return false;

    }
    
    
    public ArrayList<Questions> listAll(String jsonText) {

        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJsonn = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJsonn.get("root");
            for (Map<String, Object> obj : list) {
                Questions t = new Questions();

                t.setQuestion(obj.get("question").toString());
//                 t.setOption1(obj.get("1ére choix").toString());
//
//                  t.setOption2(obj.get("2éme choix").toString());
//                  t.setOption3(obj.get("3éme choix").toString());
//                  t.setOption4(obj.get("4éme choix").toString());
//                  t.setAnswer(obj.get("Réponse").toString());
//                  t.setQuiz(obj.get("quiz").toString());
                tasks.add(t);

            }

        } catch (IOException ex) {

        }
        return tasks;
    }
    
     public ArrayList<Questions> getListalls(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL +"/AllQuestions");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceQuestion ser = new ServiceQuestion();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }

    
//     public boolean supprimer(int questionId ) {
//        
//        String url = Statics.BASE_URL + "/SuppQuestion/" + questionId;
//        req.setUrl(url);
//        req.addResponseListener((event) -> {
//            String str = new String(req.getResponseData());
//            System.out.println(str);
//           
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return true;
//    }
     
     public void supprimerQuestion(Questions e) {

        String url = Statics.BASE_URL + "/SuppQuestion/" + e.getQuestionId();
        req.setUrl(url);
        req.addResponseListener((event) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
            //Dialog.show("", "Evenement supprimer", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

}
