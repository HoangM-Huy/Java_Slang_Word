package view.panels;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public class SlangQuizPanel extends JPanel {
    private JLabel slangLabel = new JLabel("Press 'New Question' to start", SwingConstants.CENTER);
    private JButton[] answerButtons = new JButton[4];
    private JLabel feedbackLabel = new JLabel(" ", SwingConstants.CENTER);
    private JLabel scoreLabel = new JLabel("Score: 0 / 0", SwingConstants.CENTER);
    private JButton nextButton = new JButton("New Question");

    public SlangQuizPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        slangLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(slangLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(4, 1, 8, 8));
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].setEnabled(false);
            answersPanel.add(answerButtons[i]);
        }
        add(answersPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(3, 1, 5, 5));
        bottom.add(feedbackLabel);
        bottom.add(scoreLabel);
        bottom.add(nextButton);
        add(bottom, BorderLayout.SOUTH);
    }

    public void showQuestion(String slang, List<String> options) {
        slangLabel.setText(slang);
        feedbackLabel.setText(" ");
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options.get(i));
            answerButtons[i].setBackground(null);
            answerButtons[i].setOpaque(true);
            answerButtons[i].setEnabled(true);
        }
    }

    public void showResult(int chosen, int correct) {
        for (JButton b : answerButtons) b.setEnabled(false);
        answerButtons[correct].setBackground(new Color(144, 238, 144));
        if (chosen == correct) {
            feedbackLabel.setText("Correct");
        } else {
            answerButtons[chosen].setBackground(new Color(240, 128, 128));
            feedbackLabel.setText("Wrong");
        }
    }

    public void setScore(int correct, int total) {
        scoreLabel.setText("Score: " + correct + " / " + total);
    }

    public void showMessage(String msg) {
        slangLabel.setText(msg);
        for (JButton b : answerButtons) { 
            b.setText(""); b.setEnabled(false); 
        }
    }

    public JButton[] getAnswerButtons() { 
        return answerButtons; 
    }

    public JButton getNextButton() { 
        return nextButton; 
    }
}