package game.environment;

import game.EcoPoints;

import java.util.ArrayList;

/**
 * A class to add all the necessary capabilities to different terrain.
 */
public abstract class FertileGround extends CapableGround implements FeedingGround{

    private int age = 0;  // the age of the plant
    private ArrayList<Fruit> fruitArrayList = new ArrayList<>(); //arrayList of the fruits that have grown on the plant

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public FertileGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Checks if current Location has fruits.
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
     * Adjusts the capability that the FertileGround should have when necessary.
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
     * Access the size of the treeFruits ArrayList
     * @return the size of the treeFruits ArrayList
     */
    public int getNumberOfFruits() {
        return fruitArrayList.size();
    }

    int eatFruit(){
        int healPoints = 0;
        if (getNumberOfFruits() > 0) {
            Fruit fruit = fruitArrayList.remove(0);
            healPoints = fruit.getHealPoints();
        }
        return healPoints;
    }


}
