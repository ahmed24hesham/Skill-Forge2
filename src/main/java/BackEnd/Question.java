/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;
import java.util.ArrayList;
/**
 *
 * @author Mohamed
 */
import java.util.ArrayList;
public class Question {
    private String text;
    private ArrayList<Choice> choices;
    private String correctAnswerLetter;

    public Question(String text, ArrayList<Choice> choices, String correctAnswerLetter) {
        if (choices.size() != 4) {
            throw new IllegalArgumentException("Each question must have exactly 4 choices.");
        }
        if (!correctAnswerLetter.matches("[ABCD]")) {
            throw new IllegalArgumentException("Correct answer must be A, B, C, or D.");
        }
        this.text = text;
        this.choices = choices;
        this.correctAnswerLetter = correctAnswerLetter;
    }

    public String getText() { return text; }
    public ArrayList<Choice> getChoices() { return choices; }
    public String getCorrectAnswerLetter() { return correctAnswerLetter; }
}
