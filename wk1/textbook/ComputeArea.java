import java.util.Scanner;

public class ComputeArea {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double radius;
        double area;

        System.out.println("Enter a number for the radius");
        radius = input.nextDouble();

        area = radius * radius * 3.14159;
        System.out.println("The area of the circle w radius " + radius + " is " + area);
    }
}