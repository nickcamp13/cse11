/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines a Turtle object that extends from the
 * Critter class.
 */

import java.awt.Color;

/**
 * a class defining all the possible methods
 * that can be called on a Turtle.
 */
public class Turtle extends Critter {
    private static final String SPECIES_NAME = "Tu";
    private static final String FOOD = ".";
    private static final String EMPTY_NEIGHBOR = " ";
    private static final int RANDOM_UPPER_BOUND = 100;
    private static final int FIFTY_FIFTY = 50;

    /**
     * Default constructor - creates critter with name Tu
     */
    public Turtle() {
        super(SPECIES_NAME);
    }

    /**
     * called when getting the color of a Turtle.
     *
     * @return green color
     */
    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * called to move in a certain direction.
     *
     * @return the direction
     */
    @Override
    public Direction getMove() {
        return Direction.WEST;
    }

    /**
     * Returns whether the critter wants to eat the encountered food or not.
     *
     * @return turtles eat if there are not hostile animals adjacent to it
     */
    @Override
    public boolean eat() {
        for (Direction d : Direction.values()) {
            String neighbor = info.getNeighbor(d);
            if (d == Direction.CENTER) {
                break;
            } else if (neighbor != SPECIES_NAME
                    && neighbor != FOOD
                    && neighbor != EMPTY_NEIGHBOR) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the type of attack that the fighting critter uses.
     *
     * @param opponent the encountered critter
     * @return 50% chance roar or forfeit
     */
    @Override
    public Attack getAttack(String opponent) {
        int randInt = random.nextInt(RANDOM_UPPER_BOUND);
        if (randInt < FIFTY_FIFTY) {
            return Attack.ROAR;
        }
        return Attack.FORFEIT;
    }
}