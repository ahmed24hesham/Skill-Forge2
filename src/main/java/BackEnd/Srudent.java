package BackEnd;

import static BackEnd.PasswordHasher.sha256;
import java.util.ArrayList;

public class Srudent extends User {

    private ArrayList<String> enrolledCourses ;
    private ArrayList<String> completedLessons ;
     private ArrayList<QuizResult> quizResults;

    public Srudent() {
        // Needed for Gson
    }

    public Srudent(String username, String email, String passwordHash) {
        // Generate ID
        super(new IDGenerator().generateUserId(),
              "Student",
              validateUsername(username),
              validateEmail(email),
              sha256(passwordHash));
        
        // Initialize lists
        this.enrolledCourses = new ArrayList<>();
        this.completedLessons = new ArrayList<>();
        this.quizResults = new ArrayList<>();
    }

    // ----------------- Validations -----------------
    private static String validateStudentId(String id) {
        if (id != null && id.matches("S\\d+")) return id;
        return null;
    }

    private static String validateUsername(String username) {
        if (username == null) return null;
        username = username.trim();
        return username.length() >= 3 ? username : null;
    }

    private static String validateEmail(String email) {
        if (email == null) return null;
        email = email.trim();
        int atIndex = email.indexOf("@");
        if (atIndex <= 0 || atIndex == email.length() - 1) return null;
        String domain = email.substring(atIndex + 1);
        if (!domain.contains(".") || domain.startsWith(".") || domain.endsWith(".")) return null;
        return email;
    }

    // ----------------- Getters -----------------
    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public ArrayList<String> getCompletedLessons() {
        return completedLessons;
    }

    // ----------------- Course Methods -----------------
    public void enrollCourse(String courseId) {
        int courseid = Integer.parseInt(courseId.substring(1));
        if (courseid > 0 && !enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
        }
    }

    public void completeLesson(String  lessonId) {
            int lessonid = Integer.parseInt(lessonId.substring(1));

        if (lessonid > 0 && !completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
        }
    }
    
    Course c = new Course ();
    public void addQuizResult(String lessonId, int score ,int attempt ) {
        quizResults.add(new QuizResult(lessonId, score ,attempt));
        Lesson l = c.getLessonById(lessonId);
        if (score >= l.getQuiz().getPassingScore()  && !completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
        }
    }
    
    public int getAttemptsForLesson(String lessonId) {
    int attempts = 0;
    for (QuizResult qr : quizResults) {
        if (qr.getLessonId().equals(lessonId)) attempts++;
    }
    return attempts;
}

public boolean hasPassedLesson(String lessonId) {
    return completedLessons.contains(lessonId);
}

    public ArrayList<QuizResult> getQuizResults() {
        return quizResults;
    }
    
}