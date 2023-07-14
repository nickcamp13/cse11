public class Tester {
    public static void main(String[] args) {
        MyStringBuilder msb1 = new MyStringBuilder('a');
        MyStringBuilder msb2 = new MyStringBuilder("a");
        MyStringBuilder msb3 = new MyStringBuilder(msb2);
        msb1.append("abc");

        System.out.println("msb1: " + msb1.toString());
        System.out.println("msb2: " + msb2.toString());
        System.out.println("msb3: " + msb3.toString());

        System.out.println(msb1.subString(1));
        System.out.println(msb1.subString(0, 4));
    }
}