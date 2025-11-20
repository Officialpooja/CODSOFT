import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGame extends JFrame implements ActionListener {

    private Random random;
    private int secretNumber;
    private int attempts;
    private final int maxAttempts = 7;

    private int score = 0; // SCORE ADDED

    private JLabel instructionLabel, resultLabel, attemptsLabel, scoreLabel;
    private JTextField guessField;
    private JButton guessButton, newGameButton;

    public NumberGame() {
        // Frame setup
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 5, 5));
        setLocationRelativeTo(null);

        // Components
        instructionLabel = new JLabel("Guess the number between 1 and 100", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        resultLabel = new JLabel("Enter your guess above", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        attemptsLabel = new JLabel("Attempts: 0/" + maxAttempts, SwingConstants.CENTER);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER); //SCORE DISPLAY

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> newGame());

        // Add components
        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(attemptsLabel);
        add(scoreLabel); // ADD SCORE TO SCREEN
        add(newGameButton);

        // Initialize game
        random = new Random();
        newGame();
        setVisible(true);
    }

    private void newGame() {
        secretNumber = random.nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("New game started! Make your guess.");
        attemptsLabel.setText("Attempts: 0/" + maxAttempts);
        guessField.setText("");
        guessButton.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
        String userInput = guessField.getText().trim();

        if (userInput.isEmpty()) {
            resultLabel.setText("Please enter a number!");
            return;
        }

        try {
            int guess = Integer.parseInt(userInput);

            attempts++;
            attemptsLabel.setText("Attempts: " + attempts + "/" + maxAttempts);

            if (attempts > maxAttempts) {
                resultLabel.setText("No attempts left!");
                guessButton.setEnabled(false);
                JOptionPane.showMessageDialog(this,
                        "Game Over!\nThe correct number was: " + secretNumber,
                        "Out of Attempts", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (guess < 1 || guess > 100) {
                resultLabel.setText("Number must be between 1 and 100!");
            } else if (guess < secretNumber) {
                resultLabel.setText("Too low! Try again.");
            } else if (guess > secretNumber) {
                resultLabel.setText("Too high! Try again.");
            } else {
                //Correct Guess
                resultLabel.setText("Correct! The number was " + secretNumber);
                score++; // Increase score for winning
                scoreLabel.setText("Score: " + score);

                JOptionPane.showMessageDialog(this,
                        "You won!\nAttempts taken: " + attempts +"\nYour score: " + score,
                        "Congratulations", JOptionPane.INFORMATION_MESSAGE);

                guessButton.setEnabled(false);
            }

            // If all attempts used and still incorrect
            if (attempts == maxAttempts && guess != secretNumber) {
                resultLabel.setText("You've used all attempts!");
                JOptionPane.showMessageDialog(this,
                        "Game Over!\nThe correct number was: " + secretNumber,
                        "No Attempts Left", JOptionPane.ERROR_MESSAGE);
                guessButton.setEnabled(false);
            }

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input! Please enter an integer.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGame::new);
    }
}