/*
 * Name: Nicholas Campos
 * PID: A17621673
 * Email: nicampos@ucsd.edu
 * References: JDK, Lecture Notes
 *
 * Contains a class that defines an Elephant object that extends from the
 * Critter class.
 */

import java.awt.Color;

/**
 * a class defining all the possible methods
 * that can be called on an Elephant. Elephants all move towards a target
 * (x,y) coordinate.
 */
public class Elephant extends Critter {
    private static final String SPECIES_NAME = "El";
    private static final int MATE_LVL_INCREMENT = 2;

    protected static int goalX = 0;
    protected static int goalY = 0;

    /**
     * Default constructor - creates critter with name El
     */
    public Elephant() {
        super(SPECIES_NAME);
    }

    /**
     * called when getting the color of an Elephant.
     *
     * @return gray color
     */
    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    /**
     * called to move in a certain direction.
     *
     * @return the direction
     */
    @Override
    public Direction getMove() {
        if (info.getX() == goalX && info.getY() == goalY) {
            goalX = random.nextInt(info.getWidth());
            goalY = random.nextInt(info.getHeight());
        }
        int xDisplacement = goalX - info.getX();
        int yDisplacement = goalY - info.getY();
        int distanceFromXGoal = Math.abs(xDisplacement);
        int distanceFromYGoal = Math.abs(yDisplacement);
        boolean moveEast = (xDisplacement) > 0 ? true : false;
        boolean moveSouth = (yDisplacement) > 0 ? true : false;

        if (distanceFromXGoal > distanceFromYGoal) {
            if (moveEast) {
                return Direction.EAST;
            }
            return Direction.WEST;
        } else if (distanceFromYGoal > distanceFromXGoal) {
            if (moveSouth) {
                return Direction.SOUTH;
            }
            return Direction.NORTH;
        } else {
            if (moveSouth) {
                return Direction.SOUTH;
            }
            return Direction.NORTH;
        }
    }

    /**
     * Returns whether the critter wants to eat the encountered food or not.
     *
     * @return all elephants eat no matter what
     */
    @Override
    public boolean eat() {
        return true;
    }

    /**
     * called when an elephant mates with another elephant.
     * Increases level of elephant by 2
     */
    @Override
    public void mate() {
        incrementLevel(MATE_LVL_INCREMENT);
    }

    /**
     * called when the game world is reset.
     * Elephants target is reset to 0,0
     */
    @Override
    public void reset() {
        goalX = 0;
        goalY = 0;
    }
}