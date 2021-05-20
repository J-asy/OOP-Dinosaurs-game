package game.environment;

import edu.monash.fit2099.engine.Ground;

/**
 * Ground that has capabilities.
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
     * Checks if current location is a Tree.
     *
     * @return true or false
     */
    public boolean isTree() {
        return hasCapability(TerrainType.TREE);
    }

    /**
     * Checks if current location is a Bush.
     *
     * @return true or false
     */
    public boolean isBush() {
        return hasCapability(TerrainType.BUSH);
    }

    /**
     * Checks if current location is a Lake
     *
     * @return true or false
     */
    public boolean isLake() {
        return hasCapability(TerrainType.LAKE);
    }

    void setBush() {
        addCapability(TerrainType.BUSH);
    }

    void setLake() {
        addCapability(TerrainType.LAKE);
    }

    void setTree() {
        addCapability(TerrainType.TREE);
    }


}
