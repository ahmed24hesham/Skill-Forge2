package BackEnd;
import java.util.ArrayList;
public class Quiz {
    private ArrayList<Question> questions;
    private int questionCount;
    private int passingScore ;
    private int maxAttempts  =3 ;

    public Quiz(int questionCount) {
        this.questionCount = questionCount;
        this.questions = new ArrayList<>();
        this.passingScore = 50;
    }
 
    public boolean addQuestion(Question q) { 
        if (questions.size() < questionCount) {
            questions.add(q);
            return true;
        }
        return false;
        
    }
    public ArrayList<Question> getQuestions() { return questions; }
    
    public int getQuestionCount() { return questionCount; }
    
    public int getPassingScore() {
        return passingScore;
    }
    
    public int getMaxAttempts() { return maxAttempts; }

    public double calculateScore(ArrayList<String> studentAnswers) {
        double score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (studentAnswers.get(i).equalsIgnoreCase(questions.get(i).getCorrectAnswerLetter())) {
                score +=(double) 100.0 / questions.size();
            }
        }
        return score;
    }
}

