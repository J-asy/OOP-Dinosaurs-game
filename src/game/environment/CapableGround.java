package game.environment;

import edu.monash.fit2099.engine.Ground;

/**
 * A class to add all the necessary capabilities to different terrain.
 */
public abstract class CapableGround extends Ground {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public CapableGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Checks if current Location has capability of Tree.
     * @return true or false
     */
    public boolean isTree(){
        return hasCapability(TerrainType.TREE);
    }

    /**
     * Checks if current Location has capability of Bush.
     * @return true or false
     */
    public boolean isBush(){
        return hasCapability(TerrainType.BUSH);
    }

    public boolean isVendingMachine(){
        return hasCapability(TerrainType.VENDING_MACHINE);
    }

}
