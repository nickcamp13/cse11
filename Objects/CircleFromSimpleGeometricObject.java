public class CircleFromSimpleGeometricObject extends SimpleGeometricObject {
    private double radius;

    public CircleFromSimpleGeometricObject() {}

    public CircleFromSimpleGeometricObject(double radius) {
        setRadius(radius);
    }

    public CircleFromSimpleGeometricObject(double radius, String color, boolean filled) {
        super(color, filled);
        setRadius(radius);
        // this.color = color is illegal 
        // this.filled = color is illegal

        // private data fields can only be accessed by their owners even if
        // another class inherits them

        // the only way to access private fields from a parent class is to
        // use their getter and setter methods
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Radius: " + radius;
    }
}