import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

public class FileIO {
    public static void main(String[] args) throws IOException {
        File file = new File("scores.txt");
        if (file.exists()) {
            System.out.println("File already exists");
            System.exit(1);
        }

        PrintWriter output = new PrintWriter(file);

        output.print("John T Smith");
        output.println(90);
        output.print("Jared T Jones");
        output.println(85);

        output.close();
    }
}