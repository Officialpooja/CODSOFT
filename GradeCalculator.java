import javax.swing.*;
import java.awt.*;

public class GradeCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5)); 

        JLabel label1 = new JLabel("Enter marks (0 - 100) for each subject:");
        panel.add(label1);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Subject 1:"));
        JTextField s1 = new JTextField(10);
        panel.add(s1);

        panel.add(new JLabel("Subject 2:"));
        JTextField s2 = new JTextField(10);
        panel.add(s2);

        panel.add(new JLabel("Subject 3:"));
        JTextField s3 = new JTextField(10);
        panel.add(s3);

        panel.add(new JLabel("Subject 4:"));
        JTextField s4 = new JTextField(10);
        panel.add(s4);

        panel.add(new JLabel("Subject 5:"));
        JTextField s5 = new JTextField(10);
        panel.add(s5);

        JButton calcBtn = new JButton("Calculate");
        panel.add(calcBtn);
        panel.add(new JLabel(""));

        JTextArea output = new JTextArea(5, 30);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        calcBtn.addActionListener(e -> {
            try {
                int m1 = Integer.parseInt(s1.getText().trim());
                int m2 = Integer.parseInt(s2.getText().trim());
                int m3 = Integer.parseInt(s3.getText().trim());
                int m4 = Integer.parseInt(s4.getText().trim());
                int m5 = Integer.parseInt(s5.getText().trim());

                // Validate marks (must be 0–100)
                if (m1 < 0 || m1 >100 || 
                    m2 < 0 || m2 > 100 || 
                    m3 < 0 || m3 > 100 || 
                    m4 < 0 || m4 >100 || 
                    m5 < 0 || m5 > 100) {

                    JOptionPane.showMessageDialog(frame, "Marks must be between 0 and 100 for all subjects.");
                    return;
                }

                int total = m1 + m2 + m3 + m4 + m5;
                double avg = total / 5.0;

                char grade;
                if (avg >= 90) grade = 'A';
                else if (avg >= 80) grade = 'B';
                else if (avg >= 70) grade = 'C';
                else if (avg >= 60) grade = 'D';
                else grade = 'F';

                output.setText(
                        "Total Marks: " + total + "\nAverage: " + avg + "\nGrade: " + grade );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric values.");
            }
        });
        frame.setVisible(true);
    }
}
