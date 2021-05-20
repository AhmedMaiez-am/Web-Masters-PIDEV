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
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
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
 private ArrayList<Questions> lRec;
    public ArrayList<Questions> listall;
    public ArrayList<Questions> tasks;
     public ArrayList<Questions> listallQ;
    public ArrayList<Questions> tasksQ;
    public static ServiceQuestion instance = null;

    private ConnectionRequest req;
    public boolean resultOK;

    public ServiceQuestion() {

        req = new ConnectionRequest();
    }

    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }

//      
    
     public boolean addQuestion(Questions r){
         String url=Statics.BASE_URL+"/ADDQUESTIONN/new?question="+r.getQuestion()+"&option1="+r.getOption1()+
                 "&option2="+r.getOption2()+"&option3="+r.getOption3()+
                 "&option4="+r.getOption4()+"&answer="+r.getAnswer()+
                 "&quizid="+r.getQuizid();
    req.setUrl(url);
    ConnectionRequest req=new ConnectionRequest(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
          resultOK=req.getResponseCode()==200;
          req.removeResponseListener(this);
        }
       
    });
     NetworkManager.getInstance().addToQueue(req);
    return resultOK;
        
    }

//    
     
     public  boolean supprimerQuestion(int id){
    String url=Statics.BASE_URL+"/SuppQuestion/"+id;
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

//    public ArrayList<Questions> listAll(String jsonText) {
//
//        try {
//            tasks = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJsonn = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJsonn.get("root");
//            for (Map<String, Object> obj : list) {
//                Questions t = new Questions();
//
//                t.setQuestion(obj.get("question").toString());
////                 t.setOption1(obj.get("1ére choix").toString());
////
////                  t.setOption2(obj.get("2éme choix").toString());
////                  t.setOption3(obj.get("3éme choix").toString());
////                  t.setOption4(obj.get("4éme choix").toString());
////                  t.setAnswer(obj.get("Réponse").toString());
////                  t.setQuiz(obj.get("quiz").toString());
//                tasks.add(t);
//
//            }
//
//        } catch (IOException ex) {
//
//        }
//        return tasks;
//    }
//
//    public ArrayList<Questions> getListalls() {
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl(Statics.BASE_URL + "/AllQuestions");
//
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                ServiceQuestion ser = new ServiceQuestion();
//                listall = ser.listAll(new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listall;
//    }

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
//    public boolean supprimerQuestion(Questions e) {
//
//        String url = Statics.BASE_URL + "/SuppQuestion/" + e.getQuestionId();
//        req.setUrl(url);
//        req.addResponseListener((event) -> {
//            String str = new String(req.getResponseData());
//            System.out.println(str);
//            //Dialog.show("", "Evenement supprimer", "ok", null);
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return resultOK;
//    }
    
    
//    public void ModifierQuestion(Questions q) {
//        ConnectionRequest con = new ConnectionRequest();
//        String url = Statics.BASE_URL +"/UPQUESTION/"+q.getQuestionId()+"?question=" + q.getQuestion()+ "&option1=" + q.getOption1()+"&option2=" + q.getOption2()+"&option3=" + q.getOption3()+"&option4=" + q.getOption4()+"&answer=" + q.getAnswer()+"&quiz="+q.getQuiz();
//        con.setUrl(url);
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//    }
    
    
    public boolean modifierQuestions(Questions q,int id){
    String url=Statics.BASE_URL+"/UPQUESTION/"+id+"?question=" +
            q.getQuestion()+ "&option1=" + q.getOption1()+"&option2=" +
            q.getOption2()+"&option3=" + q.getOption3()+"&option4=" +
            q.getOption4()+"&answer=" + q.getAnswer()+"&quizid="+q.getQuizid();
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
           
        resultOK=req.getResponseCode()==200;
        req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    
    
//    public ArrayList<Questions> listAllQ(String json) throws ParseException {
//            ArrayList<Questions> listAllQ = new ArrayList<>();
//        try {
//           
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
//
//            List<Map<String, Object>> Questions = (List<Map<String, Object>>) tasksListJson.get("root");
//            for (Map<String, Object> obj : Questions) {
//                Questions t = new Questions();
//         float quizId = Float.parseFloat(obj.get("quizid").toString());
//                t.setQuiz((int)quizId);
//
//                t.setQuestion(obj.get("Question").toString());
//                
//                  t.setOption1(obj.get("Réponse").toString());
//                    t.setOption2(obj.get("Réponse").toString());
//                      t.setOption3(obj.get("Réponse").toString());
//                        t.setOption4(obj.get("Réponse").toString());
//                 t.setAnswer(obj.get("Réponse").toString());
//                 
////                float isamericain = Float.parseFloat(obj.get("isamericain").toString());
//
////                t.setIsamericain((int) isamericain);
//
//                // t.setIdparent((int) obj.get("idparent"));
//                //  t.setPassword(obj.get("password").toString());
//                //  t.setImage(obj.get("image").toString());
//                listAllQ.add(t);
//
//            }
//
//        } catch (IOException ex) {
//
//        }
//        return listAllQ;
//    }
//
//    public ArrayList<Questions> getListallsQ( Quiz q) {
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://127.0.0.1:8000/QuesQuiz/"+q.getQuizId());
//
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                try{
//                    listallQ = (new ServiceQuestion()).listAllQ(new String(con.getResponseData()));
//                } catch (ParseException ex) {
//                    ex.getMessage();
//                }
////                ServiceQuestion ser = new ServiceQuestion();
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listallQ;
//    }
    
    public ArrayList<Questions> parseRec(String jsonText){
    lRec=new ArrayList<>();
    JSONParser j=new JSONParser();
    Map<String,Object> RecListJSON;
        try {
            RecListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list=(List<Map<String,Object>>)RecListJSON.get("root");
            for(Map<String,Object> obj:list){
                
                Questions r=new Questions();
                float id=Float.parseFloat(obj.get("questionid").toString());
                r.setQuestionid((int) id);
                r.setQuestion(obj.get("question").toString());
//                r.setNbrPoint((int)Float.parseFloat(obj.get("nbrPoint").toString()));
                r.setOption1(obj.get("option1").toString());
                r.setOption2(obj.get("option2").toString());
                  r.setOption3(obj.get("option3").toString());
                    r.setOption4(obj.get("option4").toString());
                      r.setAnswer(obj.get("answer").toString());
                lRec.add(r);
                
            }
        } catch (IOException ex) {
         
        }
    return lRec;
   
    
}

public  ArrayList<Questions> getAllQuestions(){
    String url=Statics.BASE_URL+"/AllQuestions";
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
             lRec=parseRec(new String(req.getResponseData()));
              req.removeResponseListener(this);
        }

      
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return lRec;
    
} 

public  ArrayList<Questions> getAllQuestionsByQuiz(Quiz q){
    String url=Statics.BASE_URL+"/QuesQuiz/"+q.getQuizId();
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
             lRec=parseRec(new String(req.getResponseData()));
              req.removeResponseListener(this);
        }

      
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return lRec;
    
} 
  

//public  boolean supprimerRecuperation(int id){
//    String url=Statics.BASE_URL+"/deleteRecupJSON/"+id;
//    req.setUrl(url);
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//              req.removeResponseListener(this);
//            
//        }
//        
//    });
//    
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    
//    return resultOK;
//}

}
