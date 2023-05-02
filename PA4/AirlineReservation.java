/*
 * Name:    Nicholas Campos
 * Email:   nicampos@ucsd.edu
 * PID:     A17621673
 * 
 * References: Stack Overflow, Lecture Notes, JDK Documentation
 * This class allows individuals to organize airline reservations on a flight
 * based on the number of seats on the plane, three different seat classes, and
 * the number of seats for each class. 
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class provides functionality for passengers to book, cancel, 
 * and upgrade airline reservations. Passengers are also able to lookup, view
 * available tickets, and print the layout of the current status of the
 * airline. Important variables include a reference to an array of seats on the
 * airline, the number of first class seats, and the number of business class
 * seats.
 */
public class AirlineReservation {
    /* Delimiters and Formatters */
    private static final String CSV_DELIMITER = ",";
    private static final String CSV_NEWLINE_DELIMITER = "\n";
    private static final String COMMAND_DELIMITER = " ";
    private static final String PLANE_FORMAT = "%d\t | %s | %s \n";

    /* Travel Classes */
    private static final int NUMBER_OF_CLASSES = 3;
    private static final int FIRST_CLASS = 0;
    private static final int BUSINESS_CLASS = 1;
    private static final int ECONOMY_CLASS = 2;
    private static final String[] CLASS_LIST = new String[] { "F", "B", "E" };
    private static final String[] CLASS_FULLNAME_LIST = new String[] {
        "First Class", "Business Class", "Economy Class" };

    /* Commands */
    private static final String[] COMMANDS_LIST = new String[] { "book",
        "cancel", "lookup", "availabletickets", "upgrade", "print", "exit" };
    private static final int BOOK_IDX = 0;
    private static final int CANCEL_IDX = 1;
    private static final int LOOKUP_IDX = 2;
    private static final int AVAI_TICKETS_IDX = 3;
    private static final int UPGRADE_IDX = 4;
    private static final int PRINT_IDX = 5;
    private static final int EXIT_IDX = 6;
    private static final int BOOK_UPGRADE_NUM_ARGS = 3;
    private static final int CANCEL_LOOKUP_NUM_ARGS = 2;

    /* Strings for main */
    private static final String USAGE_HELP = "Available commands:\n" +
            "- book <travelClass(F/B/E)> <passengerName>\n" +
            "- book <rowNumber> <passengerName>\n" +
            "- cancel <passengerName>\n" +
            "- lookup <passengerName>\n" +
            "- availabletickets\n" +
            "- upgrade <travelClass(F/B)> <passengerName>\n" +
            "- print\n" +
            "- exit";
    private static final String CMD_INDICATOR = "> ";
    private static final String INVALID_COMMAND = "Invalid command.";
    private static final String INVALID_ARGS = "Invalid number of arguments.";
    private static final String INVALID_ROW = 
        "Invalid row number %d, failed to book.\n";
    private static final String DUPLICATE_BOOK =
        "Passenger %s already has a booking and cannot book multiple seats.\n";
    private static final String BOOK_SUCCESS = 
        "Booked passenger %s successfully.\n";
    private static final String BOOK_FAIL = "Could not book passenger %s.\n";
    private static final String CANCEL_SUCCESS = 
        "Canceled passenger %s's booking successfully.\n";
    private static final String CANCEL_FAIL = 
        "Could not cancel passenger %s's booking, do they have a ticket?\n";
    private static final String UPGRADE_SUCCESS = 
        "Upgraded passenger %s to %s successfully.\n";
    private static final String UPGRADE_FAIL = 
        "Could not upgrade passenger %s to %s.\n";
    private static final String LOOKUP_SUCCESS = 
            "Passenger %s is in row %d.\n";
    private static final String LOOKUP_FAIL = "Could not find passenger %s.\n";
    private static final String AVAILABLE_TICKETS_FORMAT = "%s: %d\n";

    /* Static variables - DO NOT add any additional static variables */
    static String[] passengers;
    static int planeRows;
    static int firstClassRows;
    static int businessClassRows;

    /**
     * Runs the command-line interface for our Airline Reservation System.
     * Prompts user to enter commands, which correspond to different functions.
     * 
     * @param args args[0] contains the filename to the csv input
     * @throws FileNotFoundException if the filename args[0] is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // If there are an incorrect num of args, print error message and quit
        if (args.length != 1) {
            System.out.println(INVALID_ARGS);
            return;
        }
        initPassengers(args[0]); // Populate passengers based on csv input file
        System.out.println(USAGE_HELP);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(CMD_INDICATOR);
            String line = scanner.nextLine().trim();

            // Exit
            if (line.toLowerCase().equals(COMMANDS_LIST[EXIT_IDX])) {
                scanner.close();
                return;
            }

            String[] splitLine = line.split(COMMAND_DELIMITER);
            splitLine[0] = splitLine[0].toLowerCase();

            // Check for invalid commands
            boolean validFlag = false;
            for (int i = 0; i < COMMANDS_LIST.length; i++) {
                if (splitLine[0].toLowerCase().equals(COMMANDS_LIST[i])) {
                    validFlag = true;
                }
            }
            if (!validFlag) {
                System.out.println(INVALID_COMMAND);
                continue;
            }

            // Book
            if (splitLine[0].equals(COMMANDS_LIST[BOOK_IDX])) {
                if (splitLine.length < BOOK_UPGRADE_NUM_ARGS) {
                    System.out.println(INVALID_ARGS);
                    continue;
                }
                String[] contents = line.split(COMMAND_DELIMITER,
                        BOOK_UPGRADE_NUM_ARGS);
                String passengerName = contents[contents.length - 1];
                try {
                    // book row <passengerName>
                    int row = Integer.parseInt(contents[1]);
                    if (row < 0 || row >= passengers.length) {
                        System.out.printf(INVALID_ROW, row);
                        continue;
                    }
                    // Do not allow duplicate booking
                    boolean isDuplicate = false;
                    for (int i = 0; i < passengers.length; i++) {
                        if (passengerName.equals(passengers[i])) {
                            isDuplicate = true;
                        }
                    }
                    if (isDuplicate) {
                        System.out.printf(DUPLICATE_BOOK, passengerName);
                        continue;
                    }
                    if (book(row, passengerName)) {
                        System.out.printf(BOOK_SUCCESS, passengerName);
                    } else {
                        System.out.printf(BOOK_FAIL, passengerName);
                    }
                } catch (NumberFormatException e) {
                    // book <travelClass(F/B/E)> <passengerName>
                    validFlag = false;
                    contents[1] = contents[1].toUpperCase();
                    for (int i = 0; i < CLASS_LIST.length; i++) {
                        if (CLASS_LIST[i].equals(contents[1])) {
                            validFlag = true;
                        }
                    }
                    if (!validFlag) {
                        System.out.println(INVALID_COMMAND);
                        continue;
                    }
                    // Do not allow duplicate booking
                    boolean isDuplicate = false;
                    for (int i = 0; i < passengers.length; i++) {
                        if (passengerName.equals(passengers[i])) {
                            isDuplicate = true;
                        }
                    }
                    if (isDuplicate) {
                        System.out.printf(DUPLICATE_BOOK, passengerName);
                        continue;
                    }
                    int travelClass = FIRST_CLASS;
                    if (contents[1].equals(CLASS_LIST[BUSINESS_CLASS])) {
                        travelClass = BUSINESS_CLASS;
                    } else if (contents[1].equals(
                            CLASS_LIST[ECONOMY_CLASS])) {
                        travelClass = ECONOMY_CLASS;
                    }
                    if (book(passengerName, travelClass)) {
                        System.out.printf(BOOK_SUCCESS, passengerName);
                    } else {
                        System.out.printf(BOOK_FAIL, passengerName);
                    }
                }
            }

            // Upgrade
            if (splitLine[0].equals(COMMANDS_LIST[UPGRADE_IDX])) {
                if (splitLine.length < BOOK_UPGRADE_NUM_ARGS) {
                    System.out.println(INVALID_ARGS);
                    continue;
                }
                String[] contents = line.split(COMMAND_DELIMITER,
                        BOOK_UPGRADE_NUM_ARGS);
                String passengerName = contents[contents.length - 1];
                validFlag = false;
                contents[1] = contents[1].toUpperCase();
                for (int i = 0; i < CLASS_LIST.length; i++) {
                    if (CLASS_LIST[i].equals(contents[1])) {
                        validFlag = true;
                    }
                }
                if (!validFlag) {
                    System.out.println(INVALID_COMMAND);
                    continue;
                }
                int travelClass = FIRST_CLASS;
                if (contents[1].equals(CLASS_LIST[BUSINESS_CLASS])) {
                    travelClass = BUSINESS_CLASS;
                } else if (contents[1].equals(CLASS_LIST[ECONOMY_CLASS])) {
                    travelClass = ECONOMY_CLASS;
                }
                if (upgrade(passengerName, travelClass)) {
                    System.out.printf(UPGRADE_SUCCESS, passengerName,
                            CLASS_FULLNAME_LIST[travelClass]);
                } else {
                    System.out.printf(UPGRADE_FAIL, passengerName,
                            CLASS_FULLNAME_LIST[travelClass]);
                }
            }

            // Cancel
            if (splitLine[0].equals(COMMANDS_LIST[CANCEL_IDX])) {
                if (splitLine.length < CANCEL_LOOKUP_NUM_ARGS) {
                    System.out.println(INVALID_ARGS);
                    continue;
                }
                String[] contents = line.split(COMMAND_DELIMITER,
                        CANCEL_LOOKUP_NUM_ARGS);
                String passengerName = contents[contents.length - 1];
                if (cancel(passengerName)) {
                    System.out.printf(CANCEL_SUCCESS, passengerName);
                } else {
                    System.out.printf(CANCEL_FAIL, passengerName);
                }
            }

            // Lookup
            if (splitLine[0].equals(COMMANDS_LIST[LOOKUP_IDX])) {
                if (splitLine.length < CANCEL_LOOKUP_NUM_ARGS) {
                    System.out.println(INVALID_ARGS);
                    continue;
                }
                String[] contents = line.split(COMMAND_DELIMITER,
                        CANCEL_LOOKUP_NUM_ARGS);
                String passengerName = contents[contents.length - 1];
                if (lookUp(passengerName) == -1) {
                    System.out.printf(LOOKUP_FAIL, passengerName);
                } else {
                    System.out.printf(LOOKUP_SUCCESS, passengerName,
                            lookUp(passengerName));
                }
            }

            // Available tickets
            if (splitLine[0].equals(COMMANDS_LIST[AVAI_TICKETS_IDX])) {
                int[] numTickets = availableTickets();
                for (int i = 0; i < CLASS_FULLNAME_LIST.length; i++) {
                    System.out.printf(AVAILABLE_TICKETS_FORMAT,
                            CLASS_FULLNAME_LIST[i], numTickets[i]);
                }
            }

            // Print
            if (splitLine[0].equals(COMMANDS_LIST[PRINT_IDX])) {
                printPlane();
            }
        }
    }

    /**
     * Reads in a line of text from the passed scanner and initializes the
     * passenger array
     *
     * @param passengerLine the scanner to read input from
     */
    private static void readPassengerLine(Scanner passengerLine) {
        passengerLine.useDelimiter(CSV_DELIMITER);
        passengers[passengerLine.nextInt()] = passengerLine.next();
    }

    /**
     * Iterates through passengers to check if it contains a certain passenger
     * 
     * @param passengerName the name of the passenger to look for
     * @return true if the passenger already exists in the array.
     *         false otherwise
     */
    private static boolean containsPassenger(String passengerName) {
        for (String currentPassenger : passengers) {
            if (currentPassenger != null &&
                currentPassenger.equals(passengerName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a class is either first, business, or economy
     * 
     * @param travelClass the class in question
     * @return true if travelClass is equal to 1, 2, or 3. false otherwise
     */
    private static boolean isValidClass(int travelClass) {
        if (travelClass == FIRST_CLASS ||
                travelClass == BUSINESS_CLASS ||
                travelClass == ECONOMY_CLASS)
            return true;
        return false;
    }

    /**
     * Initializes the passengers array by reading in a properly formatted
     * csv file
     * 
     * @param fileName the name of the csv file to be read
     * @throws FileNotFoundException if file is not found an exception is thrown
     */
    private static void initPassengers(String fileName) throws 
            FileNotFoundException {
        Scanner csvInput = new Scanner(new File(fileName));

        csvInput.useDelimiter(CSV_DELIMITER);

        planeRows = csvInput.nextInt();
        firstClassRows = csvInput.nextInt();

        csvInput.skip(CSV_DELIMITER);
        if (csvInput.hasNextLine())
            csvInput.useDelimiter(CSV_NEWLINE_DELIMITER);
        businessClassRows = csvInput.nextInt();
        if (csvInput.hasNext()) 
            csvInput.skip(CSV_NEWLINE_DELIMITER);
        passengers = new String[planeRows];

        while (csvInput.hasNextLine()) {
            readPassengerLine(new Scanner(csvInput.nextLine()));
        }
    }

    /**
     * Determines which class a row belongs to
     * 
     * @param row the number of the row in question
     * @return The integer value of whichever class the row belongs to.
     *         If the row does not exist within the array then -1 will be
     *         returned.
     */
    private static int findClass(int row) {
        if (row < firstClassRows)
            return FIRST_CLASS;
        else if (row < firstClassRows + businessClassRows)
            return BUSINESS_CLASS;
        else if (row < planeRows)
            return ECONOMY_CLASS;
        else
            return -1;
    }

    /**
     * Finds the first row of either of the three classes of seats on the plane
     * 
     * @param travelClass the class of which to find the first seat
     * @return the row of the first seat for travelClass
     */
    private static int findFirstRow(int travelClass) {
        if (!isValidClass(travelClass))
            return -1;
        
        if (travelClass == FIRST_CLASS) return 0;
        else if (travelClass == BUSINESS_CLASS) return firstClassRows;
        else return firstClassRows + businessClassRows;
    }

    /**
     * Finds the last row of either of the three classes of seats on the plane
     * 
     * @param travelClass the class of which to find the last seat
     * @return the row of the last seat for travelClass
     */
    private static int findLastRow(int travelClass) {
        if (!isValidClass(travelClass))
            return -1;

        if (travelClass == FIRST_CLASS)
            return firstClassRows - 1;
        else if (travelClass == BUSINESS_CLASS)
            return firstClassRows + businessClassRows - 1;
        return passengers.length - 1;
    }

    /**
     * Books a ticket for passengerName in their desired travelClass if an empty
     * seat exists in the area.
     * 
     * @param passengerName the name of the passenger to book the seat under
     * @param travelClass the desired class to book the seat in
     * @return true if the booking was successful. false if null name or seat
     *         does not exist.
     */
    public static boolean book(String passengerName, int travelClass) {
        if (passengerName == null)
            return false;

        for (int i = findFirstRow(travelClass);
                 i <= findLastRow(travelClass); i++) {
            if (passengers[i] == null) {
                passengers[i] = passengerName;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a certain seat is empty
     * 
     * @param row the seat in question
     * @return true if the seat is null. false otherwise
     */
    private static boolean seatIsEmpty(int row) {
        return passengers[row] == null;
    }

    /**
     * Fills the specified seat with a given name
     * 
     * @param row the seat to fill the name in
     * @param passengerName the name of the passenger to fill in the seat
     * @return will always return true as this function should only be called
     *         when a seat is to be filled
     */
    private static boolean fillSeat(int row, String passengerName) {
        passengers[row] = passengerName;
        return true;
    }

    /**
     * Erases the current name from a row by nullifying it
     * 
     * @param row the number of the row to nullify
     * @return always true
     */
    private static boolean nullifySeat(int row) {
        passengers[row] = null;
        return true;
    }

    /**
     * Books a valid seat with a given passenger name.
     * 
     * @param row the seat to fill the name in
     * @param passengerName the name of the passenger to fill in the seat
     * @return false if passengerName is null or unsuccessful in booking a seat.
     *         true if successful booking.
     */
    public static boolean book(int row, String passengerName) {
        if (passengerName == null) return false;
        if (seatIsEmpty(row)) return fillSeat(row, passengerName);
        int travelClass = findClass(row);
        for (int i = findFirstRow(travelClass);
                 i <= findLastRow(travelClass); i++)
            if (seatIsEmpty(i)) return fillSeat(i, passengerName);;
        return false;
    }

    /**
     * Cancels a seat booking for a given passenger
     * 
     * @param passengerName the name of the passenger to cancel the seat
     * @return false if the passengerName is null or no seats that are
     *         currently booked have that name. true if otherwise
     */
    public static boolean cancel(String passengerName) {
        if (passengerName == null) return false;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] != null &&
                passengers[i].equals(passengerName)) return nullifySeat(i);
        }
        return false;
    }

    /**
     * Looks up the row of the given passenger
     * 
     * @param passengerName the name of the passenger
     * @return -1 if the passengerName is null or if that passenger does not
     *         exist within the seat bookings. If the passenger does exist 
     *         the row number will be returned.
     */
    public static int lookUp(String passengerName) {
        if (passengerName == null) return -1;
        else {
            for (int i = 0; i < passengers.length; i++)
                if (passengers[i] != null &&
                    passengers[i].equals(passengerName)) return i;
            return -1;
        }
    }

    /**
     * Determines how many tickets are available in each class
     * 
     * @return an integer array where each index holds the number of tickets
     *         available in a certain class
     */
    public static int[] availableTickets() {
        int[] classTickets = new int[NUMBER_OF_CLASSES];
        int firstClassTickets = 0;
        int businessClassTickets = 0;
        int economyClassTickets = 0;
        for (int i = 0; i < firstClassRows; i++) {
            if (seatIsEmpty(i)) firstClassTickets++;
        }
        for (int i = firstClassRows;
                 i < firstClassRows + businessClassRows; i++) {
            if (seatIsEmpty(i)) businessClassTickets++;
        }
        for (int i = firstClassRows + businessClassRows;
                 i < passengers.length; i++) {
            if (seatIsEmpty(i)) economyClassTickets++;
        }
        classTickets[FIRST_CLASS] = firstClassTickets;
        classTickets[BUSINESS_CLASS] = businessClassTickets;
        classTickets[ECONOMY_CLASS] = economyClassTickets;
        return classTickets;
    }

    /**
     * This will upgrade a passengers seat to the first available seat in a
     * desired class that is above their current class
     * 
     * @param passengerName the name of the passenger to upgrade their seat
     * @param upgradeClass the desired class to upgrade the passenger's 
     *                     ticket to
     * @return true if the upgrade was successful. false otherwise
     */
    public static boolean upgrade(String passengerName, int upgradeClass) {
        if (passengerName == null || !containsPassenger(passengerName))
            return false;
        int passengerRow = lookUp(passengerName);
        int travelClass = findClass(passengerRow);
        if (upgradeClass >= travelClass) return false;
        int[] availableSeats = availableTickets();
        if (upgradeClass == FIRST_CLASS && availableSeats[FIRST_CLASS] > 0) {
            nullifySeat(passengerRow);
            return book(passengerName, upgradeClass);
        }
        nullifySeat(passengerRow);
        return book(passengerName, upgradeClass);
    }

    /**
     * Prints out the names of each of the passengers according to their booked
     * seat row. No name is printed for an empty (currently available) seat.
     */
    public static void printPlane() {
        for (int i = 0; i < passengers.length; i++) {
            System.out.printf(PLANE_FORMAT, i, CLASS_LIST[findClass(i)],
                    passengers[i] == null ? "" : passengers[i]);
        }
    }
}
