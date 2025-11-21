package BackEnd;
import java.util.List;
import java.util.ArrayList;


public class Lesson {
    private String lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;   // for optional material you wanna add
    private Quiz quiz;
    public Lesson( String title, String content,Quiz quiz) {
        IDGenerator idd = new IDGenerator();
        
            this.lessonId = idd.generateLessonId();
        
        
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>(); 
        this.quiz=quiz;
    }

    public Lesson() {
        this.resources = new ArrayList<>();
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }
    public void setQuiz(Quiz quiz){
    this.quiz=quiz;
    }
    public Quiz getQuiz(){
    return quiz;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void addResource(String resource) {
        this.resources.add(resource);
    }

    public void removeResource(String resource) {
        this.resources.remove(resource);
    }

    @Override
    public String toString() {
        return title;
    }
}