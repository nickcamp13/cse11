/**
 * Visibility modifiers specifies how data fields and methods in a class
 * can be accessed from outside of the class.
 * 
 * Public - any class outside can access
 * Private - can only be accessed within its own class
 * Default - can only be accessed by classes within the same package
 */

public class SimpleCircle {
    private static int numberOfObjects = 0;
    // Data Fields
    private double radius;

    // Constructors
    public SimpleCircle() {
        radius = 1;
        numberOfObjects++;
    }

    public SimpleCircle(double newRadius) {
        radius = newRadius;
        numberOfObjects++;
    }
    
    // Methods
    public double getArea() {
        return radius * radius * Math.PI;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public void setRadius(double newRadius) {
        radius = newRadius;
    }

    static int getNumberOfObjects() {
        return numberOfObjects;
    }
}