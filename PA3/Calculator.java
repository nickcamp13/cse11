/*
 * Name: Nicholas Campos
 * Email: nicampos@ucsd.edu
 * PID: A17621673
 * References: JDK, Lecture Notes, Stack Overflow
 * 
 * This files provides the Calculator class
 * implementation. It includes various methods
 * that involve number formatting, extraction,
 * alignment, and calculations involving
 * addition and multiplication.
 */

/**
 * This class provides functionality to add and
 * multiply numbers that are represented as
 * String objects. This functionality is
 * achieved by formatting input and keeping
 * track of boolean carry values.
 */
public class Calculator {
    private static final char DECIMAL_POINT = '.';
    private static final char ZERO = '0';
    private static final String CARRY_ONE = "1";
    private static final String ADD_MISSING_DECIMAL = ".0";
    private static final int CARRY_CUTOFF = 9;
    private static final int CARRY_SUBTRACT = 10;
    private static final int VALID_CHARACTER = 2;

    /**
     * Finds the index of a numbers decimal point
     * 
     * @param number the number to search for its
     *               decimal point
     * @return the index of numbers decimal point
     */
    private static int indexOfDecimal(String number) {
        return number.indexOf(DECIMAL_POINT);
    }

    /**
     * Adds a decimal point to the end of a number
     * 
     * @param number the number of which a decimal point will
     *               be added to
     * @return the number passed but with the added decimal
     *         appended to it
     */
    private static String addDecimalPoint(String number) {
        return number += ADD_MISSING_DECIMAL;
    }

    /**
     * Checks if number contains a decimal
     * 
     * @param number the number of which a decimal will be
     *               searched for
     * @return true if number has decimal, false otherwise
     */
    private static boolean hasDecimalPoint(String number) {
        return number.contains(Character.toString(DECIMAL_POINT));
    }

    /**
     * Checks if number starts with a decimal (has no whole
     * number value).
     * 
     * @param number the number of which to see if it begins
     *               with a decimal
     * @return true if number starts with a decimal, false
     *         otherwise
     */
    private static boolean startsWithDecimalPoint(String number) {
        return number.charAt(0) == DECIMAL_POINT;
    }

    /**
     * Checks if a number starts with a zero
     * 
     * @param number the number of which to see if it begins
     *               with a zero
     * @return true if number starts with zero, false otherwise
     */
    private static boolean startsWithZero(String number) {
        return number.charAt(0) == ZERO;
    }

    /**
     * Checks if number ends with a decimal
     * 
     * @param number the number of which to check if it ends
     *               with a decimal
     * @return true if number ends with a decimal, false otherwise
     */
    private static boolean endsWithDecimalPoint(String number) {
        return number.charAt(number.length() - 1) == DECIMAL_POINT;
    }

    /**
     * Checks if a number ends with a zero
     * 
     * @param number the number to check if it ends
     *               with a zero
     * @return true if ends with zero, false otherwise
     */
    private static boolean endsWithZero(String number) {
        return number.charAt(number.length() - 1) == ZERO;
    }

    /**
     * Calculates the sum of single digit numbers
     * represented as character typed values
     * 
     * @param firstDigit  the first digit in the equation
     * @param secondDigit the second digit in the equation
     * @return the integer value of the sum of the two numbers
     */
    private static int getSum(char firstDigit, char secondDigit) {
        // subtracts the int value of char '0' to get the int value
        // of the char digit
        int firstDigitInt = firstDigit - ZERO;
        int secondDigitInt = secondDigit - ZERO;
        return firstDigitInt + secondDigitInt;
    }

    /**
     * Retrieves the whole number value of number
     * represented as a String object
     * 
     * @param number one of the operands in the calculation
     *               to be split into its whole number
     * @return the whole number part of the passed argument
     */
    public static String extractWholeNumber(String number) {
        String wholeNumber = "";

        // should return empty string if no whole number
        if (startsWithDecimalPoint(number)) {
            return wholeNumber;
        }

        // a number containing a decimal means everything to the left
        // of it is the whole number
        if (hasDecimalPoint(number)) {
            for (int i = 0; i < indexOfDecimal(number); ++i) {
                wholeNumber += number.charAt(i);
            }
        } else {
            // this means the entire number is a whole number
            return number;
        }
        return wholeNumber;
    }

    /**
     * Retrieves the decimal value of a number represented
     * as a String object
     * 
     * @param number the number of which its decimal part
     *               will be extracted from.
     * @return the extracted decimal from number
     */
    public static String extractDecimal(String number) {
        String decimal = "";

        // everything to the right of a decimal is the decimal
        // portion of the number
        if (hasDecimalPoint(number)) {
            for (int i = number.length() - 1; i > indexOfDecimal(number); --i) {
                decimal = number.charAt(i) + decimal;
            }
        }

        // if no decimal exists then an empty string is returned
        return decimal;
    }

    /**
     * Prepends a specified number of zeros to a number
     * 
     * @param number   the number of which zeros will be prepended to
     * @param numZeros the number of zeros to prepend to number
     * @return the number passed but with the specified number of
     *         zeros prepended to it
     */
    public static String prependZeros(String number, int numZeros) {
        // negative values are not considered
        if (numZeros < 0) {
            return number;
        }
        for (int i = 1; i <= numZeros; ++i) {
            number = Character.toString(ZERO) + number;
        }
        return number;
    }

    /**
     * Appends a specified number of zeros to a number
     * 
     * @param number   the number of which zeros will be appended to
     * @param numZeros the number of zeros to append to number
     * @return the number passed but with the specified number of
     *         zeros appended to it
     */
    public static String appendZeros(String number, int numZeros) {
        // negative values not considered
        if (numZeros < 0) {
            return number;
        }
        for (int i = 0; i < numZeros; ++i) {
            number += Character.toString(ZERO);
        }
        return number;
    }

    /**
     * Formats number by removing all trailing and leading zeros
     * from a number. It also ensures that a number contains a
     * decimal and digits exist before and after it.
     * 
     * @param number the number of which to format
     * @return a reformatted version of the passed number
     */
    public static String formatResult(String number) {
        // these two ifs make sure the number has a decimal point
        // with at least one 0 on the whole number side
        if (!hasDecimalPoint(number)) {
            number = addDecimalPoint(number);
        }
        if (startsWithDecimalPoint(number)) {
            number = prependZeros(number, 1);
        }

        // need to keep track of the original value since the string
        // shortens each time substring is used
        int originalIndexOfDecimal = indexOfDecimal(number);
        for (int i = 0; i < originalIndexOfDecimal; ++i) {
            // remove leading zeros unless it is the only 0 before the decimal
            if (!startsWithDecimalPoint(number) &&
                (i + 1 != originalIndexOfDecimal && startsWithZero(number))) {
                // removes the current leading 0
                number = number.substring(1, number.length());
                continue;
            }
            // if the if statement fails then all the leading zeros have
            // been accounted for so break from loop
            break;
        }

        // get new "original" index of decimal after removing leading zeros
        originalIndexOfDecimal = indexOfDecimal(number);
        for (int i = number.length() - 1; i > originalIndexOfDecimal; --i) {
            if (endsWithZero(number)
                && (number.length() - VALID_CHARACTER != originalIndexOfDecimal
                || number.charAt(number.length() - VALID_CHARACTER) == ZERO)) {
                number = number.substring(0, number.length() - 1);
                continue;
            }
            break;
        }
        return number;
    }

    /**
     * Calculates the sum of single digit numbers
     * represented as character typed values and factors
     * in a carry parameter
     * 
     * @param firstDigit  the first digit in the equation
     * @param secondDigit the second digit in the equation
     * @param carryIn     if true take a carry into account for
     *                    the addition, nothing otherwise
     * @return the result of adding two single digit numbers
     *         with a potential carry factor
     */
    public static char addDigits
    (char firstDigit, char secondDigit, boolean carryIn) {
        int sum = getSum(firstDigit, secondDigit);
        if (carryIn) {
            ++sum;
        }
        if (sum > CARRY_CUTOFF) {
            sum -= CARRY_SUBTRACT;
        }
        char sumChar = (char) ((int) ZERO + sum);
        return sumChar;
    }

    /**
     * Determines if a carry flag should be turned on based
     * on the result of adding two single digit numbers and
     * considering a carry factor.
     * 
     * @param firstDigit  the first digit in the equation
     * @param secondDigit the second digit in the equation
     * @param carryIn     if true take a carry into account for
     *                    the addition, nothing otherwise
     * @return true if the sum is greater than the carry cutoff,
     *         false otherwise
     */
    public static boolean carryOut
    (char firstDigit, char secondDigit, boolean carryIn) {
        if (carryIn) {
            return getSum(firstDigit, secondDigit) + 1 > CARRY_CUTOFF;
        }
        return getSum(firstDigit, secondDigit) > CARRY_CUTOFF;
    }

    /**
     * Calculates the sum of two number represented as String
     * objects
     * 
     * @param firstNumber  the first digit in the equation
     * @param secondNumber the second digit in the equation
     * 
     * @return the result of the calculation represented as
     *         a String object
     */
    public static String add(String firstNumber, String secondNumber) {
        firstNumber = formatResult(firstNumber);
        secondNumber = formatResult(secondNumber);

        // important to keep track of how many digits are on both
        // sides of each number
        int digitsBeforeDecimal1 = 0;
        int digitsBeforeDecimal2 = 0;
        int digitsAfterDecimal1 = 0;
        int digitsAfterDecimal2 = 0;

        for (int i = 0; i < indexOfDecimal(firstNumber); ++i) {
            digitsBeforeDecimal1++;
        }
        for (int i = 0; i < indexOfDecimal(secondNumber); ++i) {
            digitsBeforeDecimal2++;
        }

        // this variable determines how to align both numbers
        int differenceInDigits = digitsBeforeDecimal2 - digitsBeforeDecimal1;
        if (differenceInDigits > 0) {
            firstNumber = prependZeros(firstNumber, differenceInDigits);
        } else if (differenceInDigits < 0) {
            secondNumber = prependZeros(secondNumber, differenceInDigits * -1);
        }

        for (int i = firstNumber.length() - 1;
            i > indexOfDecimal(firstNumber); --i) {
            digitsAfterDecimal1++;
        }
        for (int i = secondNumber.length() - 1;
            i > indexOfDecimal(secondNumber); --i) {
            digitsAfterDecimal2++;
        }

        // needs to be recalculated for digits after the decimal
        differenceInDigits = digitsAfterDecimal2 - digitsAfterDecimal1;
        if (differenceInDigits > 0) {
            firstNumber = appendZeros(firstNumber, differenceInDigits);
        } else if (differenceInDigits < 0) {
            secondNumber = appendZeros(secondNumber, differenceInDigits * -1);
        }

        String result = "";
        // false for the first digit calculation
        boolean carryIn = false;
        // length of firstNumber should equal length of secondNumber
        int lengthOfNumbers = firstNumber.length();
        for (int i = lengthOfNumbers - 1; i >= 0; --i) {
            char charFirstNum = firstNumber.charAt(i);
            char charSecondNum = secondNumber.charAt(i);
            if (charFirstNum == DECIMAL_POINT) {
                result = Character.toString(DECIMAL_POINT) + result;
                continue;
            }
            result = addDigits(charFirstNum, charSecondNum, carryIn) + result;
            if (carryOut(charFirstNum, charSecondNum, carryIn)) {
                carryIn = true;
                if (i == 0) {
                    result = CARRY_ONE + result;
                }
                continue;
            }
            carryIn = false;
        }

        // ensure the result is properly formatted
        result = formatResult(result);
        if (endsWithDecimalPoint(secondNumber)) {
            result += ZERO;
        }

        return result;
    }

    /**
     * Calculates a multiple of number by using the
     * add method a specified number of times. Does not calculate
     * negative multiples.
     * 
     * @param number   the number to be multiplied
     * @param numTimes the number of times the add method should
     *                 be executed
     * 
     * @return the desired multiple of number represented as a
     *         String object
     */
    public static String multiply(String number, int numTimes) {
        if (numTimes < 0) {
            return number;
        }
        String result = "0.0";
        for (int i = 0; i < numTimes; ++i) {
            result = add(result, number);
        }
        return result;
    }
}