import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args) throws IOException {
        
        /**
         * PrintWriter Example
         */
        // File file = new File("scores.txt");
        // if (file.exists()) {
        //     System.out.println("File already exists");
        //     System.exit(1);
        // }

        // try (
        //     PrintWriter output = new PrintWriter(file);
        // ) {
        //     output.print("John T Smith ");
        //     output.println(90);
        //     output.print("Jared T Jones ");
        //     output.println(85);
        // }

        /**
         * Scanner Example
         */
        File file = new File("scores.txt");
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String firstName = input.next();
            String mi = input.next();
            String lastName = input.next();
            int score = input.nextInt();
            System.out.println(
                firstName + " " + mi + " " + lastName + " " + score
                );
        }
        input.close();
    }
}