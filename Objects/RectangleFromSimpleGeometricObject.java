public class RectangleFromSimpleGeometricObject extends SimpleGeometricObject {
    private double width;
    private double height;

    public RectangleFromSimpleGeometricObject() {}

    public RectangleFromSimpleGeometricObject(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public RectangleFromSimpleGeometricObject(double width, double height, String color, boolean filled) {
        setWidth(width);
        setHeight(height);
        setColor(color);
        setFilled(filled);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public void printRectangle() {
        System.out.println(
            "Date created: " + getDateCreated() + "\n" +
            "Width: " + width + "\n" +
            "Height: " + height
        );
    }
}