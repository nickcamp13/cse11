
/**
 * Name: Nicholas Campos
 * Email: nicampos@ucsd.edu
 * PID: A17621673
 * Sources: Java Docs, Stack Overflow, Textbook
 * Description: This file contains the PasswordSecurity class
 * that contains the main method for this program. The program
 * prompts its user to enter a password. Based on the input, the
 * program will suggest a stronger password if it was not strong
 * enough originally. 
*/

import java.util.Scanner;

/**
 * This class provides the main method. Users will be prompted to
 * enter a password and the program will evaluate the strength of
 * the password.
 */
public class PasswordSecurity {
    private static final String PW_PROMPT = "Please enter a password: ";
    private static final String PW_TOO_SHORT = "Password is too short";
    private static final String PW_VERY_WEAK = "Password strength: very weak";
    private static final String PW_WEAK = "Password strength: weak";
    private static final String PW_MEDIUM = "Password strength: medium";
    private static final String PW_STRONG = "Password strength: strong";
    private static final String SUGGESTION_PROMPT = "Here is a suggested stronger password: ";
    private static final String LETTER_RULE_SUGGESTION = "Cse";
    private static final String SYMBOL_RULE_SUGGESTION = "@!";

    private static final int MIN_PW_LENGTH = 8;
    private static final int VERY_WEAK_THRESHOLD = 1;
    private static final int WEAK_THRESHOLD = 2;
    private static final int MEDIUM_THRESHOLD = 3;
    private static final int STRONG_THRESHOLD = 4;
    private static final int LETTER_COUNT_THRESHOLD = 2;
    private static final int DIGIT_INTERVAL = 4;
    private static final int MOD_FACTOR = 10;
    private static final int START_OF_PASSWORD = 0;

    /**
     * Prompts user to enter a password.
     * Evaluates strength of password and
     * recommends a stronger password if not
     * "strong".
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        // keeps account of the number of occurrences from each
        // category in the password before any rules are applied
        int uppercaseLetters = 0;
        int lowercaseLetters = 0;
        int numbers = 0;
        int symbols = 0;

        int numOfCategoriesInPwd = 0;
        int originalPwdLength = 0;

        // read in user input
        Scanner in = new Scanner(System.in);
        System.out.print(PW_PROMPT);
        String password = in.nextLine();
        in.close();
        originalPwdLength = password.length();

        // program ends if the entered password is too short
        if (originalPwdLength < MIN_PW_LENGTH) {
            System.out.println(PW_TOO_SHORT);
        } else {
            // iterate through password to count the number
            // of characters in each category
            for (int i = 0; i < originalPwdLength; i++) {
                char currentChar = password.charAt(i);

                if (Character.isLetter(currentChar)) {
                    if (Character.isUpperCase(currentChar)) {
                        uppercaseLetters++;
                        continue;
                    }
                    lowercaseLetters++;
                } else if (Character.isDigit(currentChar)) {
                    numbers++;
                } else {
                    symbols++;
                }
            }

            int numberOfLetters = lowercaseLetters + uppercaseLetters;

            // checks for the number of categories in the
            // original password
            if (uppercaseLetters > 0)
                numOfCategoriesInPwd++;
            if (lowercaseLetters > 0)
                numOfCategoriesInPwd++;
            if (numbers > 0)
                numOfCategoriesInPwd++;
            if (symbols > 0)
                numOfCategoriesInPwd++;

            // if the password does not evaluate to "strong"
            // a stronger password will be generated by applying
            // certain rules.
            if (numOfCategoriesInPwd >= STRONG_THRESHOLD) {
                System.out.println(PW_STRONG);
            } else {
                // output based on strength of password
                if (numOfCategoriesInPwd == MEDIUM_THRESHOLD) {
                    System.out.println(PW_MEDIUM);
                } else if (numOfCategoriesInPwd == WEAK_THRESHOLD) {
                    System.out.println(PW_WEAK);
                } else if (numOfCategoriesInPwd == VERY_WEAK_THRESHOLD) {
                    System.out.println(PW_VERY_WEAK);
                }

                // Only one rule from rules 1, 2, and 3
                // will be applied

                // Rule 1
                if (numberOfLetters < LETTER_COUNT_THRESHOLD) {
                    password = LETTER_RULE_SUGGESTION + password;

                    // Rule 2
                    // Replaces first uppercase letter with its lowercase
                    // form
                } else if (lowercaseLetters == 0) {
                    for (int i = 0; i < originalPwdLength; i++) {
                        char currentChar = password.charAt(i);
                        if (Character.isLetter(currentChar)) {
                            password = password.replaceFirst(Character.toString(currentChar),
                                    Character.toString(Character.toLowerCase(currentChar)));
                            break;
                        }
                    }

                    // Rule 3
                    // replaces the last occurrence of the highest valued
                    // lowercase letter with its uppercase form
                } else if (uppercaseLetters == 0) {
                    int highestAsciiValue = 0;
                    for (int i = 0; i < originalPwdLength; i++) {
                        char currentChar = password.charAt(i);
                        if (Character.isLetter(currentChar) && (int) currentChar > highestAsciiValue) {
                            highestAsciiValue = (int) currentChar;
                        }
                    }
                    for (int i = password.length() - 1; i >= START_OF_PASSWORD; i--) {
                        char currentChar = password.charAt(i);
                        int currentAsciiValue = (int) currentChar;
                        if (currentAsciiValue == highestAsciiValue) {
                            char capitalChar = Character.toUpperCase(currentChar);
                            String frontSubstring = password.substring(START_OF_PASSWORD, i);
                            String endSubstring = password.substring(i + 1, password.length());
                            password = frontSubstring + capitalChar + endSubstring;
                            break;
                        }
                    }
                }

                // Rule 4
                // inserts the original password length modded with the
                // modulus factor after a digit interval
                if (numbers == 0) {
                    String tempPwd = "";
                    int currentPasswordLength = password.length();
                    int k = originalPwdLength % MOD_FACTOR;
                    boolean isDivisibleBy4 = (currentPasswordLength % 4 == 0 ? true : false);
                    for (int i = 0; i < currentPasswordLength; i += DIGIT_INTERVAL) {
                        int substringCutoff = i + DIGIT_INTERVAL;
                        if (substringCutoff >= currentPasswordLength) {
                            String endSubstring = password.substring(i, currentPasswordLength);
                            tempPwd += endSubstring;
                            break;
                        }
                        String substringWithMod = password.substring(i, substringCutoff) + Integer.toString(k);
                        tempPwd += substringWithMod;
                    }
                    if (isDivisibleBy4) {
                        tempPwd += k;
                    }
                    password = tempPwd;
                }

                // Rule 5
                // adds symbols to the end of the password
                if (symbols == 0) {
                    password += SYMBOL_RULE_SUGGESTION;
                }
                System.out.println(SUGGESTION_PROMPT + password);
            }
        }
    }
}