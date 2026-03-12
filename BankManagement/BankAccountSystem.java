package BankManagement;

import java.util.Scanner;

public class BankAccountSystem {

    static String accountName = null;
    static double balance = 0;

    public static void createAccount(String name, double initialBalance) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid account name. Name cannot be empty or null.");
            }
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }
            accountName = name;
            balance = initialBalance;
            System.out.println("Account created successfully for " + accountName + " with balance: $" + balance);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during account creation: " + e.getMessage());
        }
    }

    public static void deposit(double amount) {
        try {
            if (accountName == null) {
                throw new IllegalStateException("No account found. Please create an account first.");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be greater than zero.");
            }
            balance += amount;
            System.out.println("Deposited: $" + amount);
            checkBalance();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during deposit: " + e.getMessage());
        }
    }

    public static void withdraw(double amount) {
        try {
            if (accountName == null) {
                throw new IllegalStateException("No account found. Please create an account first.");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
            }
            if (amount > balance) {
                throw new ArithmeticException("Insufficient balance for withdrawal.");
            }
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
            checkBalance();
        } catch (IllegalArgumentException | IllegalStateException | ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error during withdrawal: " + e.getMessage());
        }
    }

    public static void checkBalance() {
        try {
            if (accountName == null) {
                throw new IllegalStateException("No account found. Please create an account first.");
            }
            System.out.println("Current balance for " + accountName + ": $" + balance);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bank Account Management System ===");

        // Create Account
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        createAccount(name, initialBalance);
        System.out.println();

        // Menu loop
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdraw(withdrawAmount);
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        System.out.println("Thank you for using the Bank Account System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-4.");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear buffer
            }
        }

        scanner.close();
    }
}