import java.util.Scanner;

import javax.swing.InputMap;

public class Circle {
    public static void main(String[] args) {
        int centerX = 0;
        int centerY = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("The center of the circle is (" + centerX + ", " + centerY + ")");
        System.out.println("Please enter a point on the outer edge of the circle");
        System.out.print("X-Coordinate: ");
        int x = input.nextInt();
        System.out.print("Y-Coordinate: ");
        int y = input.nextInt();
        double radius = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
        System.out.println("The radius of the circle is " + radius);
    }
}