import java.util.Scanner;

public class ComputeArea {
    public static void main(String[] args) {
        // Create Scanner Object
        Scanner input = new Scanner(System.in);

        // Declare a constant named PI
        final double PI = 3.14159;

        // Declaring variables
        double radius;
        double area;

        // Prompt user for input
        System.out.println("Enter a number for the radius");
        radius = input.nextDouble();

        // Calculate Area from given input
        area = radius * radius * PI;

        // Display output
        System.out.println("The area of the circle w radius " + radius + " is " + area);
    }
}