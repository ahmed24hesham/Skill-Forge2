package BackEnd;

import static BackEnd.PasswordHasher.sha256;
import java.util.ArrayList;

public class Instructor extends User {

    private transient CourseDB courseDB; // transient so Gson ignores it

    public Instructor() {
        courseDB = new CourseDB("courses.json"); // needed for Gson
    }

    public Instructor(String username, String email, String passwordHash) {
        super(new IDGenerator().generateUserId(),
              "Instructor",
              validateUsername(username),
              validateEmail(email),
              sha256(passwordHash));
        this.courseDB = new CourseDB("courses.json"); // initialize DB
    }

    // -----------------
    // Validations
    // -----------------
    private static String validateInstructorId(String id) {
        if (id != null && id.matches("I\\d+")) return id;
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

    // -----------------
    // Course operations
    // -----------------
    public void createCourse(Course c) {
        if (c != null && c.getCourseId() != null) courseDB.add(c);
        else System.out.println("Invalid course");
    }

    public void viewEnrolledStudents(String cID) {
        ArrayList<Course> list = courseDB.load();
        for (Course c : list) {
            if (c.getCourseId().equals(cID)) {
                System.out.println("Enrolled students in " + cID + ": " + c.getStudents());
                return;
            }
        }
        System.out.println("Course not found: " + cID);
    }

    public void addLesson(String cID, Lesson l) {
        ArrayList<Course> list = courseDB.load();
        for (Course c : list) {
            if (c.getCourseId().equals(cID) && l != null && l.getLessonId() != null) {
                c.addLesson(l);
            }
        }
        courseDB.save(list);
    }

    public void deleteLesson(String cID, String lID) {
        ArrayList<Course> list = courseDB.load();
        for (Course c : list) {
            if (c.getCourseId().equals(cID) && lID != null) {
                c.removeLesson(lID);
            }
        }
        courseDB.save(list);
    }

    public void editLesson(String cID, String lID, String newTitle, String newContent, String newLessonId) {
        ArrayList<Course> list = courseDB.load();
        for (Course c : list) {
            if (c.getCourseId().equals(cID)) {
                for (Lesson l : c.getLessons()) {
                    if (l.getLessonId().equals(lID)) {
                        if (newTitle != null && !newTitle.trim().isEmpty()) l.setTitle(newTitle);
                        if (newContent != null) l.setContent(newContent);
                        if (newLessonId != null && newLessonId.matches("L\\d+")) l.setLessonId(newLessonId);
                    }
                }
            }
        }
        courseDB.save(list);
    }
}