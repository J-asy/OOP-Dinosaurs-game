package game.environment;

import edu.monash.fit2099.engine.Ground;

import java.util.ArrayList;

/**
 * A class to add all the necessary capabilities to different terrain.
 */
public abstract class CapableGround extends Ground {

    private int age = 0;  //the age of the bush
    private ArrayList<Fruit> fruitArrayList = new ArrayList<>(); //arrayList of the fruits that have grown on the bush

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


    public boolean hasFruits() {return hasCapability(TerrainType.HAS_FRUITS); }

    int getAge() {
        return age;
    }

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

    public Fruit getFruit(int i){
        Fruit fruit = null;
        if (i >= 0 && i < getNumberOfFruits()) {
            fruit = fruitArrayList.remove(i);
        }
        return fruit;
    }

    //is this necessary?? not sure yet ... if dont need i'll remove it later
    /**
     * Removes the first Fruit in the List without returning anything.
     */
    public void removeFruit(){
        if (getNumberOfFruits() > 0)
            fruitArrayList.remove(0);
    }

    void addFruit(){
        fruitArrayList.add(new Fruit());
    }

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


}
