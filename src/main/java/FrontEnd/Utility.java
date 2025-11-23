package FrontEnd;

import BackEnd.Course;
import BackEnd.CourseDB;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Utility {
    
    private JTable table;

    // Pass the table you want Utility to manage
    public Utility(JTable table) {
        this.table = table;
    }

    // Load courses
    public ArrayList<Course> loadCourses() {
        return new CourseDB("courses.json").load();
    }

    // Update course status
    public void updateCourseStatus(String courseId, String newStatus) {
        ArrayList<Course> allCourses = loadCourses();

        for (Course c : allCourses) {
            if (c.getCourseId().equals(courseId)) {
                c.setStatus(newStatus);
                break;
            }
        }

        new CourseDB("courses.json").save(allCourses);
    }

    // Build table based on filter
    public void populateTable(ArrayList<Course> courses, String filterStatus) {

        String[] columns = {"Course ID", "Course Name", "Instructor ID", "Description"};

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        for (Course c : courses) {
            if (filterStatus.equalsIgnoreCase(c.getStatus())) {
                model.addRow(new Object[]{
                    c.getCourseId(),
                    c.getTitle(),
                    c.getInstructorId(),
                    c.getDescription()
                });
            }
        }

        table.setModel(model);
    }

    // Refresh any table with a filter
    public void refresh(String filterStatus) {
        populateTable(loadCourses(), filterStatus);
    }
}
