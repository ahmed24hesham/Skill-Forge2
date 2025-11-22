/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author eslamia
 */
public class QuizResult {
    private String lessonId;
    private double score;
    private int attemptNumber;

    public QuizResult(String lessonId, double score , int attempt) {
        this.lessonId = lessonId;
        this.score = score;
        this.attemptNumber = attempt;
    }

    public String getLessonId() { return lessonId; }
    public double getScore() { return score; }
    public int getAttemptNumber(){return attemptNumber;}
    
}
