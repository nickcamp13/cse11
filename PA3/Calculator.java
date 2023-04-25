public class Calculator {
    private static final char DECIMAL_POINT = '.';
    private static final char ZERO = '0';
    private static final String ADD_MISSING_DECIMAL = ".0";
    private static final int CARRY_CUTOFF = 9;
    private static final int CARRY_SUBTRACT = 10;
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

    private static int getSum(char firstDigit, char secondDigit) {
        int firstDigitInt = firstDigit - ZERO;
        int secondDigitInt = secondDigit - ZERO;
        return firstDigitInt + secondDigitInt;
    }

    public static char addDigits(char firstDigit, char secondDigit, boolean carryIn) {
        int sum = getSum(firstDigit, secondDigit);
        if (sum > CARRY_CUTOFF) {
            sum -= CARRY_SUBTRACT;
        }
        char sumChar = (char)((int) ZERO + sum);
        if (carryIn) {
            ++sumChar;
        }
        return sumChar;
    }

    public static boolean carryOut(char firstDigit, char secondDigit, boolean carryIn) {
        if (carryIn) {
            return getSum(firstDigit, secondDigit) + 1 > CARRY_CUTOFF;
        }
        return getSum(firstDigit, secondDigit) > CARRY_CUTOFF;
    }

    public static void main(String args[]) {
        String number = "0";
        System.out.println(formatResult(number));
        System.out.println(addDigits('9', '2', false));
        System.out.println(carryOut('9', '0', true));
    }
}