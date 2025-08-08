import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class QuizAppGUI {
    JFrame frame;
    CardLayout cardLayout;
    JPanel mainPanel;

    String[] quizNames = {"General Knowledge", "Science"};
    Map<String, String[]> quizQuestions = new HashMap<>();
    Map<String, String[][]> quizOptions = new HashMap<>();
    Map<String, char[]> quizAnswers = new HashMap<>();

    int currentQuestion = 0;
    int score = 0;
    String currentQuiz;

    JLabel questionLabel;
    JRadioButton[] options;
    ButtonGroup optionGroup;
    JButton submitBtn;
    JTextArea resultArea;

    public QuizAppGUI() {
        // Prepare quizzes
        quizQuestions.put("General Knowledge", new String[]{
                "What is the capital of France?",
                "Which is the largest planet?",
                "Who developed Java?"
        });
        quizOptions.put("General Knowledge", new String[][]{
                {"Paris", "Rome", "London", "Madrid"},
                {"Earth", "Mars", "Jupiter", "Venus"},
                {"Microsoft", "Sun Microsystems", "Google", "IBM"}
        });
        quizAnswers.put("General Knowledge", new char[]{'A', 'C', 'B'});

        quizQuestions.put("Science", new String[]{
                "What is H2O?",
                "Which gas do plants produce during photosynthesis?",
                "Speed of light is approximately?"
        });
        quizOptions.put("Science", new String[][]{
                {"Water", "Oxygen", "Hydrogen", "Helium"},
                {"Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"},
                {"3 x 10^8 m/s", "3 x 10^5 m/s", "300 m/s", "150 km/s"}
        });
        quizAnswers.put("Science", new char[]{'A', 'B', 'A'});

        frame = new JFrame("Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Home Screen
        JPanel homePanel = new JPanel(new GridLayout(quizNames.length + 2, 1, 10, 10));
        homePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        homePanel.add(new JLabel("Select a Quiz:", SwingConstants.CENTER));

        for (String quiz : quizNames) {
            JButton quizBtn = new JButton(quiz);
            quizBtn.addActionListener(e -> startQuiz(quiz));
            homePanel.add(quizBtn);
        }

        JButton randomQuizBtn = new JButton("Random Quiz");
        randomQuizBtn.addActionListener(e -> startQuiz(quizNames[new Random().nextInt(quizNames.length)]));
        homePanel.add(randomQuizBtn);

        mainPanel.add(homePanel, "Home");

        // Quiz Screen
        JPanel quizPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quizPanel.add(questionLabel, BorderLayout.NORTH);

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        quizPanel.add(optionsPanel, BorderLayout.CENTER);

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> checkAnswer());
        quizPanel.add(submitBtn, BorderLayout.SOUTH);

        mainPanel.add(quizPanel, "Quiz");

        // Result Screen
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JButton homeBtn = new JButton("Back to Home");
        homeBtn.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        resultPanel.add(homeBtn, BorderLayout.SOUTH);

        mainPanel.add(resultPanel, "Result");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void startQuiz(String quiz) {
        currentQuiz = quiz;
        currentQuestion = 0;
        score = 0;
        showQuestion();
        cardLayout.show(mainPanel, "Quiz");
    }

    private void showQuestion() {
        String[] questions = quizQuestions.get(currentQuiz);
        String[][] opts = quizOptions.get(currentQuiz);

        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion]);
            for (int i = 0; i < 4; i++) {
                options[i].setText((char) ('A' + i) + ") " + opts[currentQuestion][i]);
                options[i].setSelected(false);
            }
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        char correctAns = quizAnswers.get(currentQuiz)[currentQuestion];
        char selected = ' ';

        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = (char) ('A' + i);
                break;
            }
        }

        if (selected == ' ') {
            JOptionPane.showMessageDialog(frame, "Please select an option!");
            return;
        }

        if (selected == correctAns) {
            score++;
            JOptionPane.showMessageDialog(frame, "✅ Correct!");
        } else {
            JOptionPane.showMessageDialog(frame, "❌ Wrong! Correct answer: " + correctAns);
        }

        currentQuestion++;
        showQuestion();
    }

    private void showResult() {
        String[] questions = quizQuestions.get(currentQuiz);
        char[] correctAnswers = quizAnswers.get(currentQuiz);
        String[][] opts = quizOptions.get(currentQuiz);

        StringBuilder sb = new StringBuilder();
        sb.append("Quiz: ").append(currentQuiz).append("\n");
        sb.append("Score: ").append(score).append(" / ").append(questions.length).append("\n\n");

        for (int i = 0; i < questions.length; i++) {
            sb.append((i + 1)).append(") ").append(questions[i]).append("\n");
            sb.append("   Correct: ").append(correctAnswers[i]).append(") ").append(opts[i][correctAnswers[i] - 'A']).append("\n");
        }

        resultArea.setText(sb.toString());
        cardLayout.show(mainPanel, "Result");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizAppGUI::new);
    }
}
