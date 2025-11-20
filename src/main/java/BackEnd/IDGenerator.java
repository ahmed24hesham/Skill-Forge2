package BackEnd;

import java.util.ArrayList;

public class IDGenerator {

    private static int lastCourseId = 0;
    private static int lastUserId = 0;
    private static int lastLessonId = 0;

    private static boolean initialized = false;

    // Run once at startup
    private static void initialize() {
        if (initialized) return;  // avoid reloading
        initialized = true;

        loadCourseIds();
        loadUserIds();
        loadLessonIds();
    }

    private static void loadCourseIds() {
        CourseDB db = new CourseDB("courses.json");
        ArrayList<Course> courses = db.load();

        for (Course c : courses) {
            if (c.getCourseId() != null && c.getCourseId().startsWith("C")) {
                try {
                    int idNum = Integer.parseInt(c.getCourseId().substring(1));
                    if (idNum > lastCourseId) lastCourseId = idNum;
                } catch (NumberFormatException ignored) {}
            }

            // Check lessons also (lesson IDs)
            for (Lesson l : c.getLessons()) {
                if (l.getLessonId() != null && l.getLessonId().startsWith("L")) {
                    try {
                        int idNum = Integer.parseInt(l.getLessonId().substring(1));
                        if (idNum > lastLessonId) lastLessonId = idNum;
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
    }

    private static void loadUserIds() {
        InstructorDB db = new InstructorDB("users.json");
        ArrayList<Instructor> users = db.load();  // make sure this returns all teachers/students

        for (User u : users) {
            if (u.getUserId() != null && u.getUserId().startsWith("U")) {
                try {
                    int idNum = Integer.parseInt(u.getUserId().substring(1));
                    if (idNum > lastUserId) lastUserId = idNum;
                } catch (NumberFormatException ignored) {}
            }
        }
    }

    private static void loadLessonIds() {
        // Already handled in loadCourseIds() because lessons belong to courses
    }


    // ---------------------------------------
    // ID GENERATION METHODS
    // ---------------------------------------

    public static String generateCourseId() {
        initialize();
        lastCourseId++;
        return "C" + lastCourseId;
    }

    public static String generateUserId() {
        initialize();
        lastUserId++;
        return "U" + lastUserId;
    }

    public static String generateLessonId() {
        initialize();
        lastLessonId++;
        return "L" + lastLessonId;
    }
}