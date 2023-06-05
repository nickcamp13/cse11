/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines a Feline object that extends from the
 * Critter class.
 */

/**
 * a class defining all the possible methods
 * that can be called on a Feline.
 */
public class Feline extends Critter {
    private static final String SPECIES_NAME = "Fe";
    private static final int SWITCH_DIR = 4;
    private static final int EAT_INTERVAL = 2;
    protected static final Direction[] DIRECTIONS_ARR = {
        Direction.NORTH,
        Direction.SOUTH,
        Direction.EAST,
        Direction.WEST
    };

    private int moveCount; // counter for getMove method before random direction
    private int eatCount; // counter for eating
    private Direction currDir; // current direction

    /**
     * Default constructor - creates critter with name Fe, moveCount 0,
     * eatCount 0, and current direction set to a random direction
     */
    public Feline() {
        super(SPECIES_NAME);
        moveCount = 0;
        eatCount = 0;
        currDir = DIRECTIONS_ARR[random.nextInt(DIRECTIONS_ARR.length)];
    }

    /**
     * called to move in a certain direction.
     *
     * @return the direction
     */
    @Override
    public Direction getMove() {
        if (moveCount == SWITCH_DIR) {
            Direction tempDirection = currDir;
            currDir = DIRECTIONS_ARR[random.nextInt(DIRECTIONS_ARR.length)];
            moveCount = 0;
            return tempDirection;
        }
        moveCount++;
        return currDir;
    }

    /**
     * Returns whether the critter wants to eat the encountered food or not.
     *
     * @return true every third time a feline encounters food
     */
    @Override
    public boolean eat() {
        if (eatCount == EAT_INTERVAL) {
            eatCount = 0;
            return true;
        }
        eatCount++;
        return false;
    }

    /**
     * Returns the type of attack that the fighting critter uses.
     *
     * @param opponent the encountered critter
     * @return all felines pounce
     */
    @Override
    public Attack getAttack(String opponent) {
        return Attack.POUNCE;
    }
}