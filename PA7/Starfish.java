/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines a Starfish object that extends from the
 * Critter class.
 */

import java.awt.Color;

/**
 * a class defining all the possible methods
 * that can be called on a Starfish.
 */
public class Starfish extends Critter {
    private static final String SPECIES_NAME = "Patrick";

    /**
     * Default constructor - creates critter with name Patrick
     */
    public Starfish() {
        super(SPECIES_NAME);
    }

    /**
     * called to move in a certain direction.
     *
     * @return the direction
     */
    @Override
    public Direction getMove() {
        return Direction.CENTER;
    }

    /**
     * Returns the color of the Starfish
     *
     * @return Color pink
     */
    @Override
    public Color getColor() {
        return Color.PINK;
    }
}