package FrontEnd;

import BackEnd.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quiz extends JPanel {

    private Lesson lesson;
    private Srudent student;
    private Course course;
    private QuizEngine quizEngine;

    private ArrayList<JRadioButton[]> optionButtons;
    private JButton btnSubmit;
    private JLabel lblTitle;
    private JPanel questionsPanel;

    public Quiz(Lesson lesson, Srudent student, Course course) {
        this.lesson = lesson;
        this.student = student;
        this.course = course;
        this.quizEngine = new QuizEngine();
        this.optionButtons = new ArrayList<>();

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        lblTitle = new JLabel("Quiz for: " + lesson.getTitle());
        lblTitle.setForeground(Color.ORANGE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        add(lblTitle, BorderLayout.NORTH);

        questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBackground(Color.BLACK);

        // Load questions
        BackEnd.Quiz quiz = lesson.getQuiz();

        for (Question q : quiz.getQuestions()) {
            JLabel lblQ = new JLabel("Q: " + q.getText());
            lblQ.setForeground(Color.WHITE);
            lblQ.setFont(new Font("Segoe UI", Font.BOLD, 16));
            questionsPanel.add(lblQ);

            ButtonGroup group = new ButtonGroup();
            JRadioButton[] btns = new JRadioButton[q.getChoices().size()];

            for (int i = 0; i < q.getChoices().size(); i++) {
    String choiceText = q.getChoices().get(i).getText(); // visible text
    char letter = (char) ('A' + i);                      // A, B, C, D...

    btns[i] = new JRadioButton(choiceText);
    btns[i].setActionCommand(String.valueOf(letter));    // <-- store A/B/C/D
    btns[i].setForeground(Color.LIGHT_GRAY);
    btns[i].setBackground(Color.BLACK);

    group.add(btns[i]);
    questionsPanel.add(btns[i]);
            }

        optionButtons.add(btns);

            questionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        add(new JScrollPane(questionsPanel), BorderLayout.CENTER);

        // ========================
        // Submit Button
        // ========================
        btnSubmit = new JButton("Submit Quiz");
        btnSubmit.setBackground(Color.ORANGE);
        btnSubmit.addActionListener(e -> submitQuiz());
        add(btnSubmit, BorderLayout.SOUTH);
    }

    
    private void goToDashboard() {
    MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
    frame.setContentPane(new StudentDashBoard(student.getUsername()));
    frame.revalidate();
    frame.repaint();
}
        
    private void submitQuiz(){
        ArrayList<String> answers = new ArrayList<>();

        // Collect student answers
        for (JRadioButton[] btns : optionButtons) {
            boolean answered = false;
            for (JRadioButton btn : btns) {
                if (btn.isSelected()) {
                    answers.add(btn.getActionCommand());
                    answered = true;
                    break;
                }
            }
            if (!answered) {
                JOptionPane.showMessageDialog(this, "Please answer all questions.");
                return;
            }
        }

        // Submit to engine
        double score = quizEngine.takeQuiz(student.getUserId(),
                course.getCourseId(),
                lesson.getLessonId(),
                answers);

        if (score == -2) {
            JOptionPane.showMessageDialog(this,
                "You have reached the maximum number of attempts.",
                "Quiz Locked",
                JOptionPane.ERROR_MESSAGE);
            goToDashboard();
            return;
        }

        JOptionPane.showMessageDialog(this, "Your score: " + score + "%");

        
// Mark lesson passed ONLY IF score >= passing score
if (score >= lesson.getQuiz().getPassingScore()) {
    lesson.setQuizPassed(true);
    showCorrectAnswers();


    // Update JSON
    CourseDB courseDB = new CourseDB("courses.json");
    ArrayList<Course> allCourses = courseDB.load();

    // Find the course & lesson and update it
    for (Course c : allCourses) {
        if (c.getCourseId().equals(course.getCourseId())) {
            for (Lesson l : c.getLessons()) {
                if (l.getLessonId().equals(lesson.getLessonId())) {
                    l.setQuizPassed(true);
                }
            }
        }
    }
    courseDB.save(allCourses);

    JOptionPane.showMessageDialog(this, "✔ Lesson Unlocked!");
} else {
    JOptionPane.showMessageDialog(this, "❌ You did not pass. Next lesson is locked.");
}

goToDashboard();
    }

    private void showCorrectAnswers() {
        BackEnd.Quiz quiz = lesson.getQuiz();

        for (int i = 0; i < quiz.getQuestions().size(); i++) {

            String correct = quiz.getQuestions().get(i).getCorrectAnswerLetter();

            for (JRadioButton btn : optionButtons.get(i)) {
                if (btn.getActionCommand().equals(correct)) {
                    btn.setForeground(Color.GREEN);
                } else {
                    btn.setForeground(Color.RED);
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
