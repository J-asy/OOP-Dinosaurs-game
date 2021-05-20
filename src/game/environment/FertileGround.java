package game.environment;

import edu.monash.fit2099.engine.Ground;
import game.EcoPoints;
import game.Food;

import java.util.ArrayList;

/**
 * A class that represents Ground that has fruit-growing plants
 */
public abstract class FertileGround extends Ground implements Food {

    /**
     * age of the plant
     */
    private int age = 0;

    /**
     * ArrayList of the fruits that have grown on the plant
     */
    private ArrayList<Fruit> fruitArrayList = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public FertileGround(char displayChar) {
        super(displayChar);
    }

    void setBush() {
        addCapability(TerrainType.BUSH);
    }

    void setTree() {
        addCapability(TerrainType.TREE);
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
     * Checks if current Ground has fruits.
     * @return true or false
     */
    public boolean hasFruits() {return hasCapability(TerrainType.HAS_FRUITS); }

    /**
     * Access the age of the FertileGround (Tree/Bush) growing on the current Location.
     * @return age of the FertileGround
     */
    int getAge() {
        return age;
    }

    /**
     * Increments the age of the ground by one.
     */
    void incrementAge() {
        age++;
    }

    /**
     * Removes the first Fruit in list.
     *
     * @return the removed Fruit
     */
    public Fruit getFruit(){
        Fruit fruit = null;
        if (getNumberOfFruits() > 0) {
            fruit = fruitArrayList.remove(0);
        }
        return fruit;
    }

    /**
     * Removes the fruit in position i.
     * @param i the index of the fruit that is to be removed
     * @return fruit at index i
     */
    public Fruit getFruit(int i){
        Fruit fruit = null;
        if (i >= 0 && i < getNumberOfFruits()) {
            fruit = fruitArrayList.remove(i);
        }
        return fruit;
    }

    /**
     * Adds a new Fruit instance to list (simulate the growing of fruits).
     * 1 EcoPoint added each time this method is called.
     */
    void addFruit(){
        fruitArrayList.add(new Fruit());
        EcoPoints.incrementEcoPoints(1);
    }

    /**
     * Adjusts the HAS_FRUITS capability that the FertileGround should have when necessary.
     */
    void adjustHasFruitCapability(){
        if (getNumberOfFruits() > 0 && !hasFruits()) {
            addCapability(TerrainType.HAS_FRUITS);
        }
        else if (getNumberOfFruits() == 0 && hasFruits()){
            removeCapability(TerrainType.HAS_FRUITS);
        }
    }

    /**
     * Get the amount of food points gained by eating a fruit
     * from a plant, depending on the type of ground it grows on.
     * @return amount of food points gained by eating a fruit
     */
    abstract int getHealPoints();

    /**
     * Access the size of the fruitArrayList.
     * @return the size of the fruitArrayList
     */
    public int getNumberOfFruits() {
        return fruitArrayList.size();
    }

    /**
     * Simulates eating one fruit growing on the plant by removing it
     * from the plant, and returns the amount of food points/heal points
     * gained by eating the fruit.
     * @return amount of food points gained by eating a fruit on a plant growing on the ground
     */
    int eatFruit(){
        int healPoints = 0;
        if (getNumberOfFruits() > 0) {
            fruitArrayList.remove(0);
            healPoints = getHealPoints();
        }
        return healPoints;
    }

    /**
     * Returns the name of Food growing on the ground, which is Fruit.
     * @return name of fruit item
     */
    @Override
    public String foodName () {
        return new Fruit().foodName();
    }


}
