/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author IHEB
 */
public class Quiz {

    private int quizid;
    private String Title;
    private int isamericain;

    public Quiz() {
    }

    public Quiz(int quizid, String Title, int isamericain) {
        this.quizid = quizid;
        this.Title = Title;
        this.isamericain = isamericain;
    }

    public Quiz(String Title, int isamericain) {
        this.Title = Title;
        this.isamericain = isamericain;
    }

    public int getQuizId() {
        return quizid;
    }

    public void setQuizId(int quizid) {
        this.quizid = quizid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getIsamericain() {
        return isamericain;
    }

    public void setIsamericain(int isamericain) {
        this.isamericain = isamericain;
    }

    

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizid + ", Title=" + Title + ", isamericain=" + isamericain + '}';
    }

}
