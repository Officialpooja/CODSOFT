import javax.swing.*;
import java.awt.*;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM_GUI {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000); // initial balance

        JFrame frame = new JFrame("Simple ATM");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel balanceLabel = new JLabel("Balance: " + account.getBalance());

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton checkBtn = new JButton("Check Balance");

        // Deposit
        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter deposit amount:");
            if (input != null) {
                try {
                    double amt = Double.parseDouble(input);
                    if (account.deposit(amt)) {
                        balanceLabel.setText("Balance: " + account.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid amount!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number!");
                }
            }
        });

        // Withdraw
        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter withdraw amount:");
            if (input != null) {
                try {
                    double amt = Double.parseDouble(input);
                    if (account.withdraw(amt)) {
                        balanceLabel.setText("Balance: " + account.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number!");
                }
            }
        });

        // Check Balance — updated message
        checkBtn.addActionListener(e -> {
            double bal = account.getBalance();
            JOptionPane.showMessageDialog(frame, "Your balance is: " + bal);
            balanceLabel.setText("Balance: " + bal);
        });

        // Add components
        frame.add(balanceLabel);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(checkBtn);

        frame.setVisible(true);
    }
}
