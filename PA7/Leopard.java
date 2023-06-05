/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines a Leopard object that extends from the
 * Feline class.
 */

import java.awt.Color;

/**
 * a class defining all the possible methods
 * that can be called on a Leopard. Leopards share confidence which affects
 * their behavior when eating and attacking.
 */
public class Leopard extends Feline {
    private static final int CONFIDENCE_MULTIPLIER = 10;
    private static final int MIN_CONFIDENCE = 0;
    private static final int MAX_CONFIDENCE = 10;
    private static final int PERCENT_RANGE = 100;
    private static final int POUNCE_THRESHOLD = 5;
    private static final String SPECIES_NAME = "Lpd";
    private static final String FOOD = ".";
    private static final String TURTLE = "Tu";
    private static final String STARFISH = "Patrick";
    private static final Attack[] RPS_ATTACKS = {
        Attack.ROAR,
        Attack.POUNCE,
        Attack.SCRATCH
    };

    protected static int confidence = 0;

    /**
     * Default constructor - creates critter with name Lpd
     */
    public Leopard() {
        displayName = SPECIES_NAME;
    }

    /**
     * called when getting the color of a Leopard.
     *
     * @return red color
     */
    @Override
    public Color getColor() {
        return Color.RED;
    }

    /**
     * called to move in a certain direction.
     *
     * @return the direction
     */
    @Override
    public Direction getMove() {
        for (Direction d : DIRECTIONS_ARR) {
            String neighbor = info.getNeighbor(d);
            if (neighbor.equals(FOOD) || neighbor.equals(STARFISH)) {
                return d;
            }
        }
        int randomElement = random.nextInt(DIRECTIONS_ARR.length);
        return DIRECTIONS_ARR[randomElement];
    }

    /**
     * Returns whether the critter wants to eat the encountered food or not.
     *
     * @return higher chance of eating depending on confidence among all
     *         leopards
     */
    @Override
    public boolean eat() {
        int percentChance = confidence * CONFIDENCE_MULTIPLIER;
        int randomNum = this.random.nextInt(PERCENT_RANGE);
        if (randomNum < percentChance) {
            return true;
        }
        return false;
    }

    /**
     * called when you win a fight against another animal.
     * Leopards' confidence increments.
     */
    @Override
    public void win() {
        if (confidence < MAX_CONFIDENCE) {
            confidence++;
        }
    }

    /**
     * called when you lose a fight against another animal, and die.
     * Leopards' confidence decrements.
     */
    @Override
    public void lose() {
        if (confidence > MIN_CONFIDENCE) {
            confidence--;
        }
    }

    /**
     * Returns a random attack if the leopard's opponent is not a Starfish
     *
     * @param opponent the encountered critter
     * @return the chosen attack
     */
    protected Attack generateAttack(String opponent) {
        if (opponent.equals(STARFISH)) {
            return Attack.FORFEIT;
        }
        return RPS_ATTACKS[random.nextInt(RPS_ATTACKS.length)];
    }

    /**
     * Returns the type of attack that the fighting critter uses.
     *
     * @param opponent the encountered critter
     * @return pounce if the opponent is a turtle or confidence level is
     *         more than the pounce threshold, random attack otherwise
     */
    @Override
    public Attack getAttack(String opponent) {
        if (opponent.equals(TURTLE) || confidence > POUNCE_THRESHOLD) {
            return Attack.POUNCE;
        }
        return generateAttack(opponent);
    }

    /**
     * called when the game world is reset. Confidence is reset to 0
     */
    @Override
    public void reset() {
        confidence = 0;
    }
}