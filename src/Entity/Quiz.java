/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author IHEB
 */
public class Quiz {
    
    private int quizId;
    private String Title;
    private int  isamericain;

    public Quiz() {
    }

    public Quiz(int quizId, String Title, int isamericain) {
        this.quizId = quizId;
        this.Title = Title;
        this.isamericain = isamericain;
    }

    public Quiz(String Title, int isamericain) {
        this.Title = Title;
        this.isamericain = isamericain;
    }

    

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.quizId;
        hash = 67 * hash + Objects.hashCode(this.Title);
        hash = 67 * hash + this.isamericain;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quiz other = (Quiz) obj;
        if (this.quizId != other.quizId) {
            return false;
        }
        if (this.isamericain != other.isamericain) {
            return false;
        }
        if (!Objects.equals(this.Title, other.Title)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizId + ", Title=" + Title + ", isamericain=" + isamericain + '}';
    }

    

    
    

   

    
    
}
