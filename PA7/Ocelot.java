/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines an Ocelot object that extends from the
 * Leopard class.
 */

import java.awt.Color;

/**
 * a class defining all the possible methods
 * that can be called on an Ocelot.
 */
public class Ocelot extends Leopard {
    private static final String SPECIES_NAME = "Oce";
    private static final String LION = "Lion";
    private static final String FELINE = "Fe";
    private static final String LEOPARD = "Lpd";

    /**
     * Default constructor - creates critter with name Oce
     */
    public Ocelot() {
        displayName = SPECIES_NAME;
    }

    /**
     * called when getting the color of an Ocelot.
     *
     * @return light gray color
     */
    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }

    /**
     * Leopard pounches if its opponent is not a feline, if it is it will
     * scratch
     *
     * @param opponent the encountered critter
     * @return the chosen attack
     */
    @Override
    protected Attack generateAttack(String opponent) {
        if (opponent.equals(FELINE)
                || opponent.equals(LION)
                || opponent.equals(LEOPARD)) {
            return Attack.SCRATCH;
        }
        return Attack.POUNCE;
    }
}