import java.util.Scanner;

public class CheckingAccount {
    public static void main(String[] args) {
        int balance = 500;
        // Display a message asking the user to enter the amount they want to withdraw
        System.out.println("How much would you like to withdraw?");
        System.out.print("$");
        // Get the input from the user
        Scanner input = new Scanner(System.in);
        int withdrawal = input.nextInt();
        // Display the amount withdrawn
        System.out.println("You have decided to withdraw $" + withdrawal);
        // Display the remaining balance
        int remainingBalance = balance - withdrawal;
        System.out.println("The remaining balance on your account is $" + remainingBalance);
    }
}

// What happens if the user inputs a value like 50.25?
// Is int a good variable type for account balance?
// What if the user inputs a value greater than 500?