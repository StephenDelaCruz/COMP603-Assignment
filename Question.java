/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author mcste
 */
public class Question {
    private String questionText;
    private int questionDifficulty;
    private String category;
    
    public Question(String questionText, int questionDifficulty, String category){
        this.questionText = questionText;
        this.questionDifficulty = questionDifficulty;
        this.category = category;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public int getQuestionDifficulty() {
        return questionDifficulty;
    }
    
    public void setQuestionDifficulty(int questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
