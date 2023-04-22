public class Calculator {
    private static final char DECIMAL_POINT = '.';
    /**
     * 
     * @param number one of the operands in the calculation
     *               to be split into its whole number
     * @return the whole number part of the passed argument
     */
    public static String extractWholeNumber(String number) {
        String wholeNumber = "";
        if (number.charAt(0) == DECIMAL_POINT) {
            return wholeNumber;
        }
        for(int i = 0; i < number.indexOf(DECIMAL_POINT); ++i) {
            wholeNumber += number.charAt(i);
        }
        return wholeNumber;
    }

    public static String extractDecimal(String number) {
        String decimal = "";
        if (number.contains(".")) {
            for(int i = number.length() - 1; i > number.indexOf(DECIMAL_POINT); --i) {
                decimal = number.charAt(i) + decimal;
            } 
        }
        return decimal;
    }
    public static void main(String args[]) {
        String number = "123";
        System.out.println(extractWholeNumber(number) + " " + extractDecimal(number));
    }
}