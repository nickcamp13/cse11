/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines a Lion object that extends from the
 * Feline class.
 */

import java.awt.Color;

/**
 * a class defining all the possible methods
 * that can be called on a Lion. Lions move in squares.
 */
public class Lion extends Feline {
    private static final String SPECIES_NAME = "Lion";
    private static final String REVERSED_SPECIES_NAME = "noiL";
    private static final int WALK_LENGTH = 5;

    private int eastSteps;
    private int southSteps;
    private int westSteps;
    private int northSteps;
    private int fightsWon;

    /**
     * Default constructor - creates critter with name Lion, fights won 0, and
     * steps in all directions 0
     */
    public Lion() {
        displayName = SPECIES_NAME;
        fightsWon = 0;
        eastSteps = 0;
        southSteps = 0;
        westSteps = 0;
        northSteps = 0;
    }

    /**
     * called when getting the color of a Lion.
     *
     * @return yellow color
     */
    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    /**
     * called to move in a certain direction.
     *
     * @return the direction
     */
    @Override
    public Direction getMove() {
        if (eastSteps < WALK_LENGTH) {
            eastSteps++;
            return Direction.EAST;
        } else if (eastSteps == WALK_LENGTH && southSteps < WALK_LENGTH) {
            southSteps++;
            return Direction.SOUTH;
        } else if (southSteps == WALK_LENGTH && westSteps < WALK_LENGTH) {
            westSteps++;
            return Direction.WEST;
        } else if (westSteps == WALK_LENGTH && northSteps < WALK_LENGTH) {
            northSteps++;
            return Direction.NORTH;
        } else {
            eastSteps = 1;
            southSteps = 0;
            westSteps = 0;
            northSteps = 0;
            return Direction.EAST;
        }

    }

    /**
     * Returns whether the critter wants to eat the encountered food or not.
     *
     * @return lions get hungry after winning a fight
     */
    @Override
    public boolean eat() {
        if (fightsWon > 0) {
            fightsWon = 0;
            return true;
        }
        return false;
    }

    /**
     * called when your animal is put to sleep for eating too much food.
     * Fights won is reset to 0 and display name is reversed.
     */
    @Override
    public void sleep() {
        fightsWon = 0;
        displayName = REVERSED_SPECIES_NAME;
    }

    /**
     * called when your animal wakes up from sleeping. display name is
     * reverted back to original name
     */
    @Override
    public void wakeup() {
        displayName = SPECIES_NAME;
    }

    /**
     * called when you win a fight against another animal.
     * Lion's fights won increments.
     */
    @Override
    public void win() {
        fightsWon++;
    }
}