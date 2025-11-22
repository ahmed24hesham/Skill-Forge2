package FrontEnd;

import BackEnd.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quiz extends JPanel {

    private Lesson lesson;
    private Srudent student;
    private QuizEngine quizEngine;

    private ArrayList<JRadioButton[]> optionButtons;

    public Quiz(Lesson lesson, Srudent student) {

        this.lesson = lesson;
        this.student = student;
        quizEngine = new QuizEngine();

        setLayout(new BorderLayout());

        JPanel quizArea = new JPanel();
        quizArea.setLayout(new BoxLayout(quizArea, BoxLayout.Y_AXIS));

        optionButtons = new ArrayList<>();

        for (Question q : lesson.getQuiz().getQuestions()) {
            quizArea.add(new JLabel("Q: " + q.getText()));

            ButtonGroup group = new ButtonGroup();
            JRadioButton[] btns = new JRadioButton[q.getChoices().size()];

            for (int i = 0; i < q.getChoices().size(); i++) {
                btns[i] = new JRadioButton(q.getChoices().get(i).getText());
                group.add(btns[i]);
                quizArea.add(btns[i]);
            }

            optionButtons.add(btns);
            quizArea.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JButton submitBtn = new JButton("Submit Quiz");

        submitBtn.addActionListener(e -> submitQuiz());

        add(new JScrollPane(quizArea), BorderLayout.CENTER);
        add(submitBtn, BorderLayout.SOUTH);
    }

    private void submitQuiz() {

        ArrayList<String> answers = new ArrayList<>();

        for (JRadioButton[] btns : optionButtons) {
            for (JRadioButton btn : btns) {
                if (btn.isSelected()) answers.add(btn.getText());
            }
        }

        int score = quizEngine.takeQuiz(
                student.getUserId(),
                "C101", // replace with selected course
                lesson.getLessonId(),
                answers
        );

        if (score == -2) {
            JOptionPane.showMessageDialog(this, "Maximum attempts reached!");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Your score: " + score + "%\nCorrect answers are shown in green.");

        // show correct answers
        for (int i = 0; i < optionButtons.size(); i++) {
            String correct = lesson.getQuiz().getQuestions().get(i).getCorrectAnswerLetter();

            for (JRadioButton btn : optionButtons.get(i)) {
                if (btn.getText().equals(correct)) {
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
