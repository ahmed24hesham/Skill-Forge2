package BackEnd;
import java.util.ArrayList;
public class Quiz {
    private ArrayList<Question> questions;
    private int questionCount;

    public Quiz(int questionCount) {
        this.questionCount = questionCount;
        this.questions = new ArrayList<>();
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
}

