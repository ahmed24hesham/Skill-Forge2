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
   private void viewCourseDescription() {

    if (selectedCourse == null) {
        JOptionPane.showMessageDialog(this, "Select a course first.");
        return;
    }
        JOptionPane.showMessageDialog(this,selectedCourse.getDescription());
  
}
    private void viewLessonContent() {
       Lesson selectedLesson = lessonList.getSelectedValue();
    if (selectedLesson == null) {
        JOptionPane.showMessageDialog(this, "Select a Lesson first.");
        return;
    }
        JOptionPane.showMessageDialog(this,selectedLesson.getContent());
  
}
    public double getLessonQuizAverage(String lessonId) {
    StudentDB studentDB = new StudentDB("users.json");
    ArrayList<Srudent> students = studentDB.load();

    int totalScore = 0;
    int count = 0;

    for (Srudent s : students) {
        ArrayList<QuizResult> results = s.getQuizResults();
        QuizResult last = null;
        for (QuizResult qr : results) {
            if (qr.getLessonId().equals(lessonId)) {
                if (last == null || qr.getAttemptNumber() > last.getAttemptNumber()) {
                    last = qr;
                }
            }
        }
        if (last != null) {
            totalScore += last.getScore();
            count++;
        }
    }

    if (count == 0) return 0; 

    return totalScore * 1.0 / count;
}
public double getCourseAverageScore(String courseId) {
    ArrayList<Course> courses = new CourseDB("courses.json").load();
    ArrayList<Srudent> students = new StudentDB("users.json").load();

    Course target = null;
    for (Course c : courses) {
        if (c.getCourseId().equals(courseId)) {
            target = c;
            break;
        }
    }
    if (target == null) return -1;

    ArrayList<String> lessons = new ArrayList<>();
    for (Lesson l : target.getLessons()) {
        lessons.add(l.getLessonId());
    }

    int totalScores = 0;
    int count = 0;

    for (Srudent s : students) {
        for (QuizResult qr : s.getQuizResults()) {
            if (lessons.contains(qr.getLessonId())) {
                totalScores += qr.getScore();
                count++;
            }
        }
    }

    if (count == 0) return 0;

    return (double) totalScores / count;
}
public double getLessonCompletionPercentage(String courseId, String lessonId) {
     StudentDB studentDB = new StudentDB("users.json");
    ArrayList<Srudent> allStudents = studentDB.load();
    int enrolledCount = 0;
    int completedCount = 0;
    for (Srudent s : allStudents) {

        if (s.getEnrolledCourses().contains(courseId)) {
            enrolledCount++;

            if (s.getCompletedLessons().contains(lessonId)) {
                completedCount++;
            }
        }
    }

    if (enrolledCount == 0) return 0;
    return (completedCount * 100.0) / enrolledCount;
}

public double getCourseCompletionPercentage(String courseId) {
    CourseDB courseDB = new CourseDB("courses.json");
    StudentDB studentDB = new StudentDB("users.json");

    ArrayList<Course> courses = courseDB.load();
    ArrayList<Srudent> students = studentDB.load();

    Course target = null;
    for (Course c : courses) {
        if (c.getCourseId().equals(courseId)) {
            target = c;
            break;
        }
    }

    if (target == null || target.getLessons().isEmpty()) return 0;

    ArrayList<Lesson> lessons = target.getLessons();
    double totalPercentage = 0;

    for (Lesson l : lessons) {
        totalPercentage += getLessonCompletionPercentage(courseId,l.getLessonId());
    }

    return totalPercentage / lessons.size();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

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

        jButton1.setBackground(new java.awt.Color(255, 102, 0));
        jButton1.setText("Course Description");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setText("Lesson Content");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 0));
        jButton3.setText("Show Quiz");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 0));
        jButton4.setText("Quiz Average per Lesson");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 102, 0));
        jButton5.setText("Quiz Average per course ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 102, 0));
        jButton6.setText("Lesson completion %");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 102, 0));
        jButton7.setText("Course completion %");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(delete)
                                    .addComponent(create))
                                .addGap(33, 33, 33)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnRefreshCourses)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LessonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(175, 175, 175))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edit)
                                .addGap(121, 121, 121))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLogOut)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddLesson)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteLesson)
                                .addGap(35, 35, 35)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(41, 41, 41))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ViewEnrolledStudents)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editLesson)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addComponent(jButton3))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
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
                            .addComponent(btnRefreshCourses)
                            .addComponent(jButton7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit)
                            .addComponent(AddLesson)
                            .addComponent(DeleteLesson)
                            .addComponent(jButton2)
                            .addComponent(jButton6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewEnrolledStudents)
                            .addComponent(editLesson)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
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
    String numberofQuestions = JOptionPane.showInputDialog("Number of Questions in Quiz:");

    if (title == null || title.isBlank()) return;

    int qCount = Integer.parseInt(numberofQuestions);

    BackEnd.Quiz quiz = new BackEnd.Quiz(qCount);

    for (int i = 0; i < qCount; i++) {

        String text = JOptionPane.showInputDialog("Question " + (i + 1) + ":");

        String choice1 = JOptionPane.showInputDialog("Choice A:");
        String choice2 = JOptionPane.showInputDialog("Choice B:");
        String choice3 = JOptionPane.showInputDialog("Choice C:");
        String choice4 = JOptionPane.showInputDialog("Choice D:");

        ArrayList<BackEnd.Choice> choices = new ArrayList<>();
        choices.add(new BackEnd.Choice(choice1));
        choices.add(new BackEnd.Choice(choice2));
        choices.add(new BackEnd.Choice(choice3));
        choices.add(new BackEnd.Choice(choice4));

        // Create radio buttons
        JRadioButton a = new JRadioButton("A");
        JRadioButton b = new JRadioButton("B");
        JRadioButton c = new JRadioButton("C");
        JRadioButton d = new JRadioButton("D");

        a.setActionCommand("A");
        b.setActionCommand("B");
        c.setActionCommand("C");
        d.setActionCommand("D");

        ButtonGroup group = new ButtonGroup();
        group.add(a);
        group.add(b);
        group.add(c);
        group.add(d);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Choose Correct Answer:"));
        panel.add(a);
        panel.add(b);
        panel.add(c);
        panel.add(d);

        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Select Correct Answer", JOptionPane.OK_CANCEL_OPTION);

        String letter = null;

        if (result == JOptionPane.OK_OPTION && group.getSelection() != null) {
            letter = group.getSelection().getActionCommand();  // A/B/C/D
        } else {
            JOptionPane.showMessageDialog(this, "You must select a correct answer!");
            i--; // repeat this question again
            continue;
        }

        BackEnd.Question question = new BackEnd.Question(text, choices, letter);
        quiz.addQuestion(question);
    }

    Lesson l = new Lesson(title, content, quiz);

    for (Instructor instructor : list) {
        if (instructor.getUsername().trim().equals(username)) {
            instructor.addLesson(selectedCourse.getCourseId(), l);
        }
    }

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
String numberofQuestions = JOptionPane.showInputDialog("Number of Questions in Quiz:");

    if (newTitle == null || newTitle.isBlank()) return;

    int qCount = Integer.parseInt(numberofQuestions);

    BackEnd.Quiz quiz = new BackEnd.Quiz(qCount);

    for (int i = 0; i < qCount; i++) {

        String text = JOptionPane.showInputDialog("Question " + (i + 1) + ":");

        String choice1 = JOptionPane.showInputDialog("Choice A:");
        String choice2 = JOptionPane.showInputDialog("Choice B:");
        String choice3 = JOptionPane.showInputDialog("Choice C:");
        String choice4 = JOptionPane.showInputDialog("Choice D:");

        ArrayList<BackEnd.Choice> choices = new ArrayList<>();
        choices.add(new BackEnd.Choice(choice1));
        choices.add(new BackEnd.Choice(choice2));
        choices.add(new BackEnd.Choice(choice3));
        choices.add(new BackEnd.Choice(choice4));

        // Create radio buttons
        JRadioButton a = new JRadioButton("A");
        JRadioButton b = new JRadioButton("B");
        JRadioButton c = new JRadioButton("C");
        JRadioButton d = new JRadioButton("D");

        a.setActionCommand("A");
        b.setActionCommand("B");
        c.setActionCommand("C");
        d.setActionCommand("D");

        ButtonGroup group = new ButtonGroup();
        group.add(a);
        group.add(b);
        group.add(c);
        group.add(d);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Choose Correct Answer:"));
        panel.add(a);
        panel.add(b);
        panel.add(c);
        panel.add(d);

        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Select Correct Answer", JOptionPane.OK_CANCEL_OPTION);

        String letter = null;

        if (result == JOptionPane.OK_OPTION && group.getSelection() != null) {
            letter = group.getSelection().getActionCommand();  // A/B/C/D
        } else {
            JOptionPane.showMessageDialog(this, "You must select a correct answer!");
            i--; // repeat this question again
            continue;
        }

        BackEnd.Question question = new BackEnd.Question(text, choices, letter);
        quiz.addQuestion(question);
    }
for (Instructor i : list) {
            if (i.getUsername().trim().equals(username)) {
                i.editLesson(
        selectedCourse.getCourseId(),
        selectedLesson.getLessonId(),
        newTitle,
        newContent,
        selectedLesson.getLessonId(),
        quiz
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        viewLessonContent();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
viewCourseDescription();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Lesson selectedLesson = lessonList.getSelectedValue();
if (selectedCourse == null || selectedLesson == null) {
    JOptionPane.showMessageDialog(this, "Select a lesson first.");
    return;
}
 BackEnd.Quiz quiz = selectedLesson.getQuiz();
    if (quiz == null || quiz.getQuestions().isEmpty()) {
        JOptionPane.showMessageDialog(this, "This lesson has no quiz.");
        return;
    }

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    for (int i = 0; i < quiz.getQuestions().size(); i++) {
        BackEnd.Question q = quiz.getQuestions().get(i);

        mainPanel.add(new JLabel((i + 1) + ". " + q.getText()));

        ArrayList<BackEnd.Choice> choices = q.getChoices();

        ButtonGroup group = new ButtonGroup();
        JPanel choicePanel = new JPanel(new GridLayout(0, 1));

        char option = 'A';
        for (BackEnd.Choice choice : choices) {
            JRadioButton radio = new JRadioButton(option + ") " + choice.getText());
            radio.setEnabled(false); // make it read-only
            if (option == q.getCorrectAnswerLetter().charAt(0)) {
                radio.setSelected(true); // mark the correct answer
            }
            group.add(radio);
            choicePanel.add(radio);
            option++;
        }

        mainPanel.add(choicePanel);
        mainPanel.add(Box.createVerticalStrut(10)); // spacing between questions
    }

    JScrollPane scrollPane = new JScrollPane(mainPanel);
    scrollPane.setPreferredSize(new Dimension(400, 300));

    JOptionPane.showMessageDialog(this, scrollPane, "Quiz Details", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                Lesson selectedLesson = lessonList.getSelectedValue();
if (selectedCourse == null || selectedLesson == null) {
    JOptionPane.showMessageDialog(this, "Select a lesson first.");
    return;
}
     Double average =getLessonQuizAverage(selectedLesson.getLessonId());
     String formatted = String.format("%.2f", average);
     JOptionPane.showMessageDialog(this,
        "Average Quiz Score = " + formatted);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         if (selectedCourse == null) {
        JOptionPane.showMessageDialog(this, "Select a course first.");
        return;
    }
         double average = getCourseAverageScore(selectedCourse.getCourseId());
         String formatted = String.format("%.2f", average);
  JOptionPane.showMessageDialog(this,
        "Average Marks Score  Per Course= " + formatted);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
          if (selectedCourse == null) {
        JOptionPane.showMessageDialog(this, "Select a course first.");
        return;
    }
         double average = getCourseCompletionPercentage(selectedCourse.getCourseId());
         String formatted = String.format("%.2f", average);
  JOptionPane.showMessageDialog(this,
        "Completon percantage= " + formatted);
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
          Lesson selectedLesson = lessonList.getSelectedValue();
if (selectedCourse == null || selectedLesson == null) {
    JOptionPane.showMessageDialog(this, "Select a lesson first.");
    return;
}
     Double average =getLessonCompletionPercentage(selectedCourse.getCourseId(),selectedLesson.getLessonId());
     JOptionPane.showMessageDialog(this,
        "Completion percantage = " + average);
    }//GEN-LAST:event_jButton6ActionPerformed


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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
