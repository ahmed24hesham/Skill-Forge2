package BackEnd;

import java.util.ArrayList;

public class Course {

    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private String status = "PENDING";
    private ArrayList<Lesson> lessons;
    private ArrayList<String> students;  

    // Constructor with validation
    public Course( String title, String description, String instructorId) {
        IDGenerator idd= new IDGenerator();
        this.courseId = idd.generateCourseId();
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;

        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // Default constructor required for Gson
    public Course() {
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // Validation: Course ID must start with C and digits
    private static String validateCourseId(String id) {
        if (id != null && id.matches("C\\d+")) return id;
        return null;
    }

   

    // Getters and setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = validateCourseId(courseId); }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructorId() { return instructorId; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }

    public ArrayList<Lesson> getLessons() { return lessons; }
    public ArrayList<String> getStudents() { return students; }

    // Lesson management
    public void addLesson(Lesson lesson) {
        if (lesson != null && lesson.getLessonId() != null) {
            lessons.add(lesson);
        }
    }

    public void removeLesson(String lessonId) {
        if (lessonId != null) {
            lessons.removeIf(l -> l.getLessonId().equals(lessonId));
        }
    }

    public Lesson getLessonById(String lessonId) {
        if (lessonId == null) return null;
        for (Lesson l : lessons) {
            if (l.getLessonId().equals(lessonId)) return l;
        }
        return null;
    }

    // Student enrollment
    public void enrollStudent(String studentUserId) {
        if (studentUserId != null && !students.contains(studentUserId)) {
            students.add(studentUserId);
            
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void removeStudent(String studentUserId) {
        if (studentUserId != null) {
            students.removeIf(id -> id.equals(studentUserId));
        }
    }

    @Override
    public String toString() {
        return title + " (" + courseId + " " + status + ")" ;
    }
}