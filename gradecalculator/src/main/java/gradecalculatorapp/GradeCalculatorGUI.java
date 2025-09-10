package gradecalculatorapp;

import javax.swing.*;
import java.awt.*;

public class GradeCalculatorGUI {
    private final JFrame frame;
    private final JTextField firstTestField;
    private final JTextField secondTestField;
    private final JTextField attendanceField;
    private final JTextField examField;
    private final JLabel resultLabel;

    public GradeCalculatorGUI() {
        // Initialize frame
        frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(150, 100);
        frame.setLayout(new GridLayout(5, 2, 5, 5));

        // Labels and Fields
        frame.add(new JLabel("First Test Score (0-30):"));
        firstTestField = new JTextField();
        frame.add(firstTestField);

        frame.add(new JLabel("Second Test Score (0-30):"));
        secondTestField = new JTextField();
        frame.add(secondTestField);

        frame.add(new JLabel("Attendance (0-60):"));
        attendanceField = new JTextField();
        frame.add(attendanceField);

        frame.add(new JLabel("Exam (0-100):"));
        examField = new JTextField();
        frame.add(examField);

        // Calculate Button
        JButton calculateButton = new JButton("Calculate");
        frame.add(calculateButton);

        // Result Label
        resultLabel = new JLabel("Result will appear here...");
        frame.add(resultLabel);

        // Make frame visible
        frame.setVisible(true);


        calculateButton.addActionListener(e -> {
            try {
// Get input values
                double firstTest = Double.parseDouble(firstTestField.getText());
                double secondTest = Double.parseDouble(secondTestField.getText());
                double attendance = Double.parseDouble(attendanceField.getText());
                double exam = Double.parseDouble(examField.getText());

// Validate inputs
                if (exam < 0 || exam >100 || firstTest <0 || firstTest >30 || secondTest <0 || secondTest >30 || attendance <0 || attendance >60){
                    resultLabel.setText("Error: Test Scores must be 0-30, Exam Score must be 0-100, Attendance 0-60.");
                    return;
                }

// Calculate
                double testAverage = (firstTest + secondTest) / 2;
                double attendancePercentage = (attendance / 60) * 100;
                double finalScore = (0.8 * testAverage) + (0.2 * attendancePercentage) + (exam);

// Format to two decimal places
                String formattedScore = String.format("%.2f", finalScore);

// Grade mapping
                int category;
                if (finalScore >= 90) category = 1;
                else if (finalScore >= 80) category = 2;
                else if (finalScore >= 70) category = 3;
                else if (finalScore >= 60) category = 4;
                else category = 5;

                String grade;
                switch (category) {
                    case 1: grade = "A"; break;
                    case 2: grade = "B"; break;
                    case 3: grade = "C"; break;
                    case 4: grade = "D"; break;
                    case 5: grade = "F"; break;
                    default: grade = "F"; break;
                }

                // Display result
                resultLabel.setText("Final Score: " + formattedScore + ", Grade: " + grade);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Error: Please enter valid numbers.");
            }
        });
    }

    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(GradeCalculatorGUI::new);
    }
}