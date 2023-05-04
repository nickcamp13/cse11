import java.util.*;
import java.io.*;

public class Main {
    public static void dispAve(String inputName, String outputName) throws IOException {
        File f = new File(inputName);
        Scanner s = new Scanner(f);
        File f2 = new File(outputName);
        PrintWriter out = new PrintWriter(f2);
        while (s.hasNext()) {
            String row = s.nextLine();
            String[] data = row.split(",");
            out.print(data[0]);
            double ave = 0;
            for (int i = 1; i < data.length; i++) {
                ave += Integer.parseInt(data[i]);
            }
            System.out.println(" " + ave/3);
        }
        out.close();
    }

    public static void main(String[] args) throws IOException{
        dispAve("student.cse12", );
    }
}