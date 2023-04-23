public class Calculator {
    private static final char DECIMAL_POINT = '.';
    private static final char ZERO = '0';
    private static final String ADD_MISSING_DECIMAL = ".0";
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
        if (number.contains(Character.toString(DECIMAL_POINT))) {
            for(int i = 0; i < number.indexOf(DECIMAL_POINT); ++i) {
                wholeNumber += number.charAt(i);
            }
        } else  {
            for (int i = 0; i < number.length(); ++i) {
                wholeNumber += number.charAt(i);
            }
        }
        return wholeNumber;
    }

    public static String extractDecimal(String number) {
        String decimal = "";
        if (number.contains(Character.toString(DECIMAL_POINT))) {
            for(int i = number.length() - 1; i > number.indexOf(DECIMAL_POINT); --i) {
                decimal = number.charAt(i) + decimal;
            } 
        }
        return decimal;
    }

    public static String prependZeros(String number, int numZeros) {
        if (numZeros < 0) {
            return number;
        }
        for (int i = 1; i <= numZeros; ++i) {
            number = Character.toString(ZERO) + number;
        }
        return number;
    }

    public static String appendZeros(String number, int numZeros) {
        if (numZeros < 0) {
            return number;
        }
        for (int i = 0; i < numZeros; ++i) {
            number += Character.toString(ZERO);
        }
        return number;
    }

    public static String formatResult(String number) {
        if (!number.contains(Character.toString(DECIMAL_POINT))) {
            number += ADD_MISSING_DECIMAL;
        }
        if (number.charAt(0) == DECIMAL_POINT) {
            number = Character.toString(ZERO) + number;
        }
        int originalIndexOfDecimal = number.indexOf(DECIMAL_POINT);
        for (int i = 0; i < originalIndexOfDecimal; ++i) {
            if (number.charAt(0) == ZERO && (i + 1 != originalIndexOfDecimal || number.charAt(1) == ZERO)) {
                number = number.substring(1, number.length());
                continue;
            }
            break;
        }
        originalIndexOfDecimal = number.indexOf(DECIMAL_POINT);
        for (int i = number.length() - 1; i > originalIndexOfDecimal; --i) {
            if (number.charAt(number.length() - 1) == ZERO && number.length() - 2 != originalIndexOfDecimal || number.charAt(number.length() - 2) == ZERO) {
                number = number.substring(0, number.length() - 1);
                continue;
            }
            break;
        }
        return number;
    }

    public static void main(String args[]) {
        String number = "0";
        System.out.println(formatResult(number));
        
    }
}