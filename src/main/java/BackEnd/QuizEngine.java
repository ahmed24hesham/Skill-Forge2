package BackEnd;

import java.util.ArrayList;

public class QuizEngine {

    private CourseDB courseDB;
    private StudentDB studentDB;

    public QuizEngine() {
        courseDB = new CourseDB("courses.json");
        studentDB = new StudentDB("users.json");
    }

    public double takeQuiz(String studentId, String courseId, String lessonId, ArrayList<String> answers) {

        ArrayList<Course> courses = courseDB.load();
        Course course = null;

        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                course = c;
                break;
            }
        }
        if (course == null) return -1;

        Lesson lesson = course.getLessonById(lessonId);
        if (lesson == null) return -1;

        Quiz quiz = lesson.getQuiz();

        
        
       ArrayList<Srudent> students = studentDB.load();
        Srudent student = null;

        for (Srudent s : students) {
            if (s.getUserId().equals(studentId)) student = s;
        }

        int attempts = student.getAttemptsForLesson(lessonId);

        if (attempts >= quiz.getMaxAttempts()) {
            return -2;  // MAX ATTEMPTS REACHED
        }

        double score = quiz.calculateScore(answers);
        student.addQuizResult(lessonId, score, attempts + 1);


        studentDB.save(students);
        return score;
    }
}
