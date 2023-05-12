import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEditor {
    /* Constants (Magic numbers) */
    private static final String PNG_FORMAT = "png";
    private static final String NON_RGB_WARNING =
            "Warning: we do not support the image you provided. \n" +
            "Please change another image and try again.";
    private static final String RGB_TEMPLATE = "(%3d, %3d, %3d) ";
    private static final String INVALID_DEGREE =
            "Invalid rotation. Exiting rotate.";
    private static final int BLUE_BYTE_SHIFT = 0;
    private static final int GREEN_BYTE_SHIFT = 8;
    private static final int RED_BYTE_SHIFT = 16;
    private static final int ALPHA_BYTE_SHIFT = 24;
    private static final int BLUE_BYTE_MASK = 0xff << BLUE_BYTE_SHIFT;
    private static final int GREEN_BYTE_MASK = 0xff << GREEN_BYTE_SHIFT;
    private static final int RED_BYTE_MASK = 0xff << RED_BYTE_SHIFT;
    private static final int ALPHA_BYTE_MASK = ~(0xff << ALPHA_BYTE_SHIFT);
    private static final int DEGREE_DIVISIBLE = 90;
    private static final int ONE_ROTATE = 1;
    private static final int TWO_ROTATES = 2;
    private static final int THREE_ROTATES = 3;
    private static final int NINETY_DEGREES = 90;
    private static final int ONE_EIGHTY_DEGREES = 180;
    private static final int TWO_SEVENTY_DEGREES = 270;
    private static final int COMPLETE_REVOLUTION = 360;


    /* Static variables - DO NOT add any additional static variables */
    static int[][] image;

    /**
     * Open an image from disk and return a 2D array of its pixels.
     * Use 'load' if you need to load the image into 'image' 2D array instead
     * of returning the array.
     *
     * @param pathname path and name to the file, e.g. "input.png",
     *                 "D:\\Folder\\ucsd.png" (for Windows), or
     *                 "/User/username/Desktop/my_photo.png" (for Linux/macOS).
     *                 Do NOT use "~/Desktop/xxx.png" (not supported in Java).
     * @return 2D array storing the rgb value of each pixel in the image
     * @throws IOException when file cannot be found or read
     */
    public static int[][] open(String pathname) throws IOException {
        BufferedImage data = ImageIO.read(new File(pathname));
        if (data.getType() != BufferedImage.TYPE_3BYTE_BGR &&
                data.getType() != BufferedImage.TYPE_4BYTE_ABGR) {
            System.err.println(NON_RGB_WARNING);
        }
        int[][] array = new int[data.getHeight()][data.getWidth()];

        for (int row = 0; row < data.getHeight(); row++) {
            for (int column = 0; column < data.getWidth(); column++) {
                /* Images are stored by column major
                   i.e. (2, 10) is the pixel on the column 2 and row 10
                   However, in class, arrays are in row major
                   i.e. [2][10] is the 11th element on the 2nd row
                   So we reverse the order of i and j when we load the image.
                 */
                array[row][column] = data.getRGB(column, row) & ALPHA_BYTE_MASK;
            }
        }

        return array;
    }

    /**
     * Load an image from disk to the 'image' 2D array.
     *
     * @param pathname path and name to the file, see open for examples.
     * @throws IOException when file cannot be found or read
     */
    public static void load(String pathname) throws IOException {
        image = open(pathname);
    }

    /**
     * Save the 2D image array to a PNG file on the disk.
     *
     * @param pathname path and name for the file. Should be different from
     *                 the input file. See load for examples.
     * @throws IOException when file cannot be found or written
     */
    public static void save(String pathname) throws IOException {
        BufferedImage data = new BufferedImage(
                image[0].length, image.length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < data.getHeight(); row++) {
            for (int column = 0; column < data.getWidth(); column++) {
                // reverse it back when we write the image
                data.setRGB(column, row, image[row][column]);
            }
        }
        ImageIO.write(data, PNG_FORMAT, new File(pathname));
    }

    /**
     * Unpack red byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return red value in that packed pixel, 0 <= red <= 255
     */
    private static int unpackRedByte(int rgb) {
        return (rgb & RED_BYTE_MASK) >> RED_BYTE_SHIFT;
    }

    /**
     * Unpack green byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return green value in that packed pixel, 0 <= green <= 255
     */
    private static int unpackGreenByte(int rgb) {
        return (rgb & GREEN_BYTE_MASK) >> GREEN_BYTE_SHIFT;
    }

    /**
     * Unpack blue byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return blue value in that packed pixel, 0 <= blue <= 255
     */
    private static int unpackBlueByte(int rgb) {
        return (rgb & BLUE_BYTE_MASK) >> BLUE_BYTE_SHIFT;
    }

    /**
     * Pack RGB bytes back to an int in the format of
     * [byte0: unused][byte1: red][byte2: green][byte3: blue]
     *
     * @param red   red byte, must satisfy 0 <= red <= 255
     * @param green green byte, must satisfy 0 <= green <= 255
     * @param blue  blue byte, must satisfy 0 <= blue <= 255
     * @return packed int to represent a pixel
     */
    private static int packInt(int red, int green, int blue) {
        return (red << RED_BYTE_SHIFT)
                + (green << GREEN_BYTE_SHIFT)
                + (blue << BLUE_BYTE_SHIFT);
    }

    /**
     * Print the current image 2D array in (red, green, blue) format.
     * Each line represents a row in the image.
     */
    public static void printImage() {
        for (int[] ints : image) {
            for (int pixel : ints) {
                System.out.printf(
                        RGB_TEMPLATE,
                        unpackRedByte(pixel),
                        unpackGreenByte(pixel),
                        unpackBlueByte(pixel));
            }
            System.out.println();
        }
    }


    /**
     *
     * @param numRotations
     */
    private static void rotate90(int numRotations) {
        if (numRotations < 0 || numRotations > 3) {
            return;
        }
        for (int k = 0; k < numRotations; ++k) {
            int originalRows = image.length;
            int originalCols = image[0].length;
            int[][] rotatedImage = new int[originalCols][originalRows];
            for (int i = 0; i < originalRows; ++i) {
                for (int j = 0; j < originalCols; ++j) {
                    rotatedImage[j][originalRows - 1 - i] = image[i][j];
                }
            }
            image = rotatedImage;
        }
    }

    /**
     *
     * @param degree
     */
    public static void rotate(int degree) {
        if (degree < 0 || degree % DEGREE_DIVISIBLE != 0) {
            System.out.println(INVALID_DEGREE);
            return;
        }
        switch((degree + COMPLETE_REVOLUTION) % COMPLETE_REVOLUTION) {
            case NINETY_DEGREES:
                rotate90(ONE_ROTATE);
                break;
            case ONE_EIGHTY_DEGREES:
                rotate90(TWO_ROTATES);
                break;
            case TWO_SEVENTY_DEGREES:
                rotate90(THREE_ROTATES);
            default:
                break;
        }
    }

    public static void downSample(int heightScale, int widthScale) {
        if (heightScale < 1 || widthScale < 1
                || heightScale > image.length || widthScale > image[0].length
                || image.length % heightScale != 0
                || image[0].length % widthScale != 0) {
            return;
        }

        int downscaledRows = image.length / heightScale;
        int downscaledCols = image[0].length / widthScale;
        int subArrayPixels = heightScale * widthScale;

        int[][] downscaledImage = new int[downscaledRows][downscaledCols];

        for (int x = 0; x < downscaledImage.length; ++x) {
            for (int y = 0; y < downscaledImage[0].length; ++y) {
                int redSum = 0;
                int greenSum = 0;
                int blueSum = 0;
                for (int i = x * heightScale, startRow = i;
                        i < startRow + heightScale; ++i) {
                    for (int j = y * widthScale, startCol = j;
                            j < startCol + widthScale; ++j) {
                        int rgb = image[i][j];
                        redSum += unpackRedByte(rgb);
                        greenSum += unpackGreenByte(rgb);
                        blueSum += unpackBlueByte(rgb);
                    }
                }
                int redAvg = (int)Math.floor((double)redSum / subArrayPixels);
                int greenAvg = (int)Math.floor((double)greenSum / subArrayPixels);
                int blueAvg = (int)Math.floor((double)blueSum / subArrayPixels);

                downscaledImage[x][y] = packInt(redAvg, greenAvg, blueAvg);
            }
        }
        image = downscaledImage;
    }

    public static void main(String[] args) throws IOException {
        load("ucsd.png");
        System.out.println(image[0][0]);
        System.out.println(unpackRedByte(image[0][0]));
        System.out.println(unpackGreenByte(image[0][0]));
        System.out.println(unpackBlueByte(image[0][0]));
        load("ucsd_patch_khosla.png");
        downSample(7, 12);
        save("ucsd_patch_khosla.png");

    }
}