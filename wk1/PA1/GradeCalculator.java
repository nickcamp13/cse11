import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numOfPAs = input.nextInt();
        int sumOfPAScores = 0;
        int midtermScore;
        int finalScore;
        boolean validInput = true;
        double overallScore;
        char letterGrade;

        final double PA_WEIGHT = 0.5;
        final double MIDTERM_WEIGHT = 0.125;
        final double FINAL_WEIGHT = 0.375;

        if (numOfPAs <= 0) {
            validInput = false;
        }

        for (int i = 0; i < numOfPAs; i++) {
            int newPAScore = input.nextInt();

            if (newPAScore < 0 || newPAScore > 100) {
                validInput = false;
            }

            sumOfPAScores += newPAScore;
        }

        midtermScore = input.nextInt();
        finalScore = input.nextInt();

        if (!validInput) {
            System.out.println("invalid input");
        } else if (midtermScore < 0 || midtermScore > 100 || finalScore < 0 || finalScore > 100) {
            validInput = false;
            System.out.println("invalid input");
        } else {
            // calculate final score here
            double paScoreAverage = sumOfPAScores / (double) numOfPAs;
            overallScore = (paScoreAverage * PA_WEIGHT) + (midtermScore * MIDTERM_WEIGHT)
                    + (finalScore * FINAL_WEIGHT);

            if (overallScore >= 90) {
                letterGrade = 'A';
            } else if (overallScore >= 80) {
                letterGrade = 'B';
            } else if (overallScore >= 70) {
                letterGrade = 'C';
            } else if (overallScore >= 60) {
                letterGrade = 'D';
            } else {
                letterGrade = 'F';
            }

            System.out.println(overallScore);
            System.out.println(letterGrade);
        }
    }
}