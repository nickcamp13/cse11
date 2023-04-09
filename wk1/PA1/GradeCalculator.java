/*
Name: Nicholas Campos
Email: nicampos@ucsd.edu
PID: A17621673
Sources used: Textbook, Lecture slides

This is my submission for PA1. The program
accepts input for PA, midterm, and final scores.
It then calculates an overall class score and determines
the user's letter grade.
*/

import java.util.Scanner;

/**
 * This class is the only class for this PA. It simply
 * asks the user to input their scores earned in CSE 11
 * and calculates their score and letter grade.
 * 
 * Instance variables: (these won't come up until future PAs)
 * numOfPAs - The number of PA scores to be entered by the user
 * sumOfPAScores - The summation of all PA scores entered by the user
 * midtermScore - The user's midterm score
 * finalScore - The user's final score
 * validInput - a boolean that turns to false when the user enters invalid input
 * overallScore - the user's end of class overall score calculated by the
 * program
 * letterGrade - the user's letter grade that is based on overallScore
 * PA_WEIGHT, MIDTERM_WEIGHT, FINAL_WEIGHT - the weights for each assignment
 * category
 * newPAScore - the new PA score entered by the user
 */
public class GradeCalculator {
    public static void main(String[] args) {
        // scanner object to extract input from user
        Scanner input = new Scanner(System.in);

        int numOfPAs = input.nextInt();
        int sumOfPAScores = 0;
        int midtermScore;
        int finalScore;
        boolean validInput = true;
        double overallScore;
        char letterGrade;

        // Weights for each assignment category
        final double PA_WEIGHT = 0.5;
        final double MIDTERM_WEIGHT = 0.125;
        final double FINAL_WEIGHT = 0.375;

        // Values for the maximum and minimum scores that the user will input
        final int MAX_SCORE = 100;
        final int MIN_SCORE = 0;

        // Cutoff values for determining letter grade
        final int A_CUTOFF = 90;
        final int B_CUTOFF = 80;
        final int C_CUTOFF = 70;
        final int D_CUTOFF = 60;

        // checks for valid input for the number of PAs (must be greater than 0)
        if (numOfPAs <= 0) {
            validInput = false;
        }

        // user inputs new PA scores based on the value of numOfPAs
        // and adds the scores to sumOfPAScores
        for (int i = 0; i < numOfPAs; i++) {
            int newPAScore = input.nextInt();

            // checks for valid input on the recently input newPAScore
            if (newPAScore < MIN_SCORE || newPAScore > MAX_SCORE) {
                validInput = false;
            }

            sumOfPAScores += newPAScore;
        }

        // gets user input for their midterm and final scores
        midtermScore = input.nextInt();
        finalScore = input.nextInt();

        // this if, else if, else block handles the final output for this program
        if (!validInput) {
            System.out.println("invalid input");
        } else if (midtermScore < MIN_SCORE || midtermScore > MAX_SCORE || finalScore < MIN_SCORE
                || finalScore > MAX_SCORE) {
            validInput = false;
            System.out.println("invalid input");
        } else {
            // calculate the student's overall score using assignment category weights
            double paScoreAverage = sumOfPAScores / (double) numOfPAs;
            overallScore = (paScoreAverage * PA_WEIGHT) + (midtermScore * MIDTERM_WEIGHT)
                    + (finalScore * FINAL_WEIGHT);

            // Determines the student's letter grade based on the calculated overall score
            if (overallScore >= A_CUTOFF) {
                letterGrade = 'A';
            } else if (overallScore >= B_CUTOFF) {
                letterGrade = 'B';
            } else if (overallScore >= C_CUTOFF) {
                letterGrade = 'C';
            } else if (overallScore >= D_CUTOFF) {
                letterGrade = 'D';
            } else {
                letterGrade = 'F';
            }

            System.out.println(overallScore);
            System.out.println(letterGrade);
        }
    }
}