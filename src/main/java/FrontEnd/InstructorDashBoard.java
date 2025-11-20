package FrontEnd;

/**
 *
 * @author Asus
 */

import BackEnd.*;
import FrontEnd.LogIn;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class InstructorDashBoard extends JPanel { 
  
    private String loggedInstructor;
    private String username ;
    private CourseDB courseDB = new CourseDB("courses.json");
    private InstructorDB instructorDB = new InstructorDB("users.json");

    private DefaultListModel<Course> courseModel = new DefaultListModel<>();
    private DefaultListModel<Lesson> lessonModel = new DefaultListModel<>();
    private ArrayList<Instructor> list =instructorDB.load();

private Course selectedCourse;
private JList<Course> courseList;
private JList<Lesson> lessonList;
    


public InstructorDashBoard(String username ) {
    this.username = username ;
   

        for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                this.loggedInstructor=i.getUserId();
            }}
                
    initComponents();
    

    courseList = new JList<>(courseModel);
    lessonList = new JList<>(lessonModel);

  

JScrollPane courseScroll = new JScrollPane(courseList);
courseScroll.setPreferredSize(new Dimension(300, 300));   // ← control size here

coursePanel.setLayout(new BorderLayout());
coursePanel.add(courseScroll, BorderLayout.CENTER);


JScrollPane lessonScroll = new JScrollPane(lessonList);
lessonScroll.setPreferredSize(new Dimension(300, 300));   // ← control size here

LessonPanel.setLayout(new BorderLayout());
LessonPanel.add(lessonScroll, BorderLayout.CENTER);


    courseList.addListSelectionListener(e -> {
        selectedCourse = courseList.getSelectedValue();
        loadLessons();
        

    });

    loadCourses();
}

private void loadCourses() {
    courseModel.clear();
    ArrayList<Course> list = courseDB.load();

    for (Course c : list) {
        if (c.getInstructorId().equals(this.loggedInstructor)) {
            courseModel.addElement(c);
        }
    }
}


private void loadLessons() {
    lessonModel.clear();

    if (selectedCourse == null) return;

    for (Lesson l : selectedCourse.getLessons()) {
        lessonModel.addElement(l);
    }
}



     
  private void viewEnrolledStudents() {

    if (selectedCourse == null) {
        JOptionPane.showMessageDialog(this, "Select a course first.");
        return;
    }

    ArrayList<String> students = selectedCourse.getStudents();

    if (students.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No students enrolled in this course.");
        return;
    }

    StringBuilder sb = new StringBuilder("Enrolled Students:\n\n");
    for (String s : students) {
        sb.append("- ").append(s).append("\n");
    }

    JOptionPane.showMessageDialog(this, sb.toString());
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coursePanel = new javax.swing.JPanel();
        LessonPanel = new javax.swing.JPanel();
        create = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        btnRefreshCourses = new javax.swing.JButton();
        AddLesson = new javax.swing.JButton();
        DeleteLesson = new javax.swing.JButton();
        ViewEnrolledStudents = new javax.swing.JButton();
        editLesson = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout coursePanelLayout = new javax.swing.GroupLayout(coursePanel);
        coursePanel.setLayout(coursePanelLayout);
        coursePanelLayout.setHorizontalGroup(
            coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        coursePanelLayout.setVerticalGroup(
            coursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout LessonPanelLayout = new javax.swing.GroupLayout(LessonPanel);
        LessonPanel.setLayout(LessonPanelLayout);
        LessonPanelLayout.setHorizontalGroup(
            LessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );
        LessonPanelLayout.setVerticalGroup(
            LessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        create.setBackground(new java.awt.Color(255, 102, 0));
        create.setText("Add Course ");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(255, 102, 0));
        delete.setText("Delete Course ");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(255, 102, 0));
        edit.setText("Edit Course ");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        btnRefreshCourses.setBackground(new java.awt.Color(255, 102, 0));
        btnRefreshCourses.setText("Refresh ");
        btnRefreshCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshCoursesActionPerformed(evt);
            }
        });

        AddLesson.setBackground(new java.awt.Color(255, 102, 0));
        AddLesson.setText("Add Lesson");
        AddLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLessonActionPerformed(evt);
            }
        });

        DeleteLesson.setBackground(new java.awt.Color(255, 102, 0));
        DeleteLesson.setText("Delete Lesson");
        DeleteLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteLessonActionPerformed(evt);
            }
        });

        ViewEnrolledStudents.setBackground(new java.awt.Color(255, 102, 0));
        ViewEnrolledStudents.setText("View Enrolled Students");
        ViewEnrolledStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewEnrolledStudentsActionPerformed(evt);
            }
        });

        editLesson.setBackground(new java.awt.Color(255, 102, 0));
        editLesson.setText("Edit Lesson");
        editLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLessonActionPerformed(evt);
            }
        });

        btnLogOut.setBackground(new java.awt.Color(255, 102, 0));
        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 102, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Courses");

        jLabel2.setBackground(new java.awt.Color(255, 102, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Lessons");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coursePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delete)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnRefreshCourses))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(create)
                                .addGap(49, 49, 49)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LessonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(175, 175, 175))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edit)
                                .addGap(160, 160, 160))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLogOut)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ViewEnrolledStudents)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editLesson))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddLesson)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteLesson)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(LessonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coursePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(create)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLogOut)
                            .addComponent(btnRefreshCourses))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit)
                            .addComponent(AddLesson)
                            .addComponent(DeleteLesson))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewEnrolledStudents)
                            .addComponent(editLesson))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        String title = JOptionPane.showInputDialog("Course Title:");
String desc = JOptionPane.showInputDialog("Description:");

if (title == null || title.isBlank()) return;

Course c = new Course(title, desc, this.loggedInstructor);
for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                i.createCourse(c);
            }}
loadCourses();
    }//GEN-LAST:event_createActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        Course sel = courseList.getSelectedValue();
if (sel == null) {
    JOptionPane.showMessageDialog(this, "Select a course first.");
    return;
}

int ok = JOptionPane.showConfirmDialog(this,
        "Delete " + sel.getTitle() + "?",
        "Confirm",
        JOptionPane.YES_NO_OPTION);

if (ok != JOptionPane.YES_OPTION) return;

ArrayList<Course> list = courseDB.load();
list.removeIf(c -> c.getCourseId().equals(sel.getCourseId()));
courseDB.save(list);

loadCourses();
lessonModel.clear();
        
    }//GEN-LAST:event_deleteActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
         selectedCourse = courseList.getSelectedValue();
if (selectedCourse == null) {
    JOptionPane.showMessageDialog(this, "Select a course first.");
    return;
}

String newTitle = JOptionPane.showInputDialog("New Title:", selectedCourse.getTitle());
String newDesc = JOptionPane.showInputDialog("New Description:", selectedCourse.getDescription());

ArrayList<Course> all = courseDB.load();

for (Course c : all) {
    if (c.getCourseId().equals(selectedCourse.getCourseId())) {
        c.setTitle(newTitle);
        c.setDescription(newDesc);
    }
}

courseDB.save(all);
loadCourses();
    }//GEN-LAST:event_editActionPerformed

    private void btnRefreshCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshCoursesActionPerformed
        // TODO add your handling code here:
        loadCourses();
lessonModel.clear();
selectedCourse = null;
        
    }//GEN-LAST:event_btnRefreshCoursesActionPerformed

    private void AddLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLessonActionPerformed
        // TODO add your handling code here:
        if (selectedCourse == null) {
    JOptionPane.showMessageDialog(this, "Select a course first.");
    return;
}

String title = JOptionPane.showInputDialog("Lesson Title:");
String content = JOptionPane.showInputDialog("Lesson Content:");

if (title == null || title.isBlank()) return;

Lesson l = new Lesson(title, content);
for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                i.addLesson(selectedCourse.getCourseId(), l);
            }}
loadLessons();
    }//GEN-LAST:event_AddLessonActionPerformed

    private void DeleteLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteLessonActionPerformed
        // TODO add your handling code here:
        Lesson selectedLesson = lessonList.getSelectedValue();

if (selectedCourse == null || selectedLesson == null) {
    JOptionPane.showMessageDialog(this, "Select a lesson first.");
    return;
}

for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                i.deleteLesson(
        selectedCourse.getCourseId(),
        selectedLesson.getLessonId()
);
            }}

loadLessons();
    }//GEN-LAST:event_DeleteLessonActionPerformed

    private void ViewEnrolledStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewEnrolledStudentsActionPerformed
        // TODO add your handling code here:
        viewEnrolledStudents();
    }//GEN-LAST:event_ViewEnrolledStudentsActionPerformed

    private void editLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLessonActionPerformed
        // TODO add your handling code here: Lesson selectedLesson = lessonList.getSelectedValue();
Lesson selectedLesson = lessonList.getSelectedValue();
if (selectedCourse == null || selectedLesson == null) {
    JOptionPane.showMessageDialog(this, "Select a lesson first.");
    return;
}

String newTitle = JOptionPane.showInputDialog("New Title:", selectedLesson.getTitle());
String newContent = JOptionPane.showInputDialog("New Content:", selectedLesson.getContent());

for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                i.editLesson(
        selectedCourse.getCourseId(),
        selectedLesson.getLessonId(),
        newTitle,
        newContent,
        selectedLesson.getLessonId()
);
            }}

loadLessons();
    }//GEN-LAST:event_editLessonActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
          JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
     MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);

    frame.setContentPane(new LogIn());  
     mainFrame.setSize(400, 500); 
    mainFrame.setLocationRelativeTo(null); 

    mainFrame.revalidate();
    mainFrame.repaint();
    }//GEN-LAST:event_btnLogOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddLesson;
    private javax.swing.JButton DeleteLesson;
    private javax.swing.JPanel LessonPanel;
    private javax.swing.JButton ViewEnrolledStudents;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnRefreshCourses;
    private javax.swing.JPanel coursePanel;
    private javax.swing.JButton create;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JButton editLesson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
