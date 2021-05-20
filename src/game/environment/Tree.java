package game.environment;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Probability;
import game.dinosaurs.CapableActor;


/**
 * Class representing terrain of Tree type.
 */
public class Tree extends FertileGround {

	/**
	 * Display character of a small tree.
	 */
	private static final char SMALL = '+';

	/**
	 * Display character of a medium-sized tree.
	 */
	private static final char MEDIUM = 't';

	/**
	 * Display character of a big tree.
	 */
	private static final char BIG = 'T';

	/**
	 * Display character of a big tree that has fruits on it.
	 */
	private static final char FRUITY_TREE = '&';

	/**
	 * Amount of food points gained by eating a Fruit from a tree.
	 */
	private static final int FRUIT_HEAL_POINTS = 5;

	/**
	 * Constructor
	 */
	public Tree() {
		super(SMALL);
		setTree();
	}

	/**
	 * Updates the tree every turn.
	 * On every tick, the age of the Tree is increased. When the age of the Tree is over 20, it will have a 50% chance
	 * to grow a Fruit. The Fruit have a 5% chance of falling onto the ground, when that happens it is removed from the
	 * list of Fruits. The time the fallen Fruit are on the ground are checked every turn to see if it reached 15, if
	 * yes, it will rot and be removed from the Location.
	 * @param location the current Location of the Tree
	 */
	@Override
	public void tick(Location location) {
		incrementAge();
		int currentAge = getAge();
		if (currentAge == 10)
			displayChar = MEDIUM;
		if (currentAge == 20)
			displayChar = BIG;

		if ((displayChar == BIG || displayChar == FRUITY_TREE) && currentAge > 20){
			if (Probability.generateProbability(0.5f)){
				displayChar = FRUITY_TREE;
				addFruit();
			}

			if (getNumberOfFruits() <= 0){
				displayChar = BIG;
			}

			//check if the fruit will fall and set the item as portable and add to location, removed from treeFruits
			if (getNumberOfFruits() > 0){
				for (int i = 0; i < getNumberOfFruits(); i++){
					if (Probability.generateProbability(0.05f)) {
						Fruit fruit = getFruit(i);
						location.addItem(fruit);
					}
				}
			}
		}

		adjustHasFruitCapability();
	}

	/**
	 * Get the amount of food points gained by eating a Fruit from a Tree.
	 * @return amount of food points gained by eating a Fruit from a Tree.
	 */
	@Override
	int getHealPoints(){
		return FRUIT_HEAL_POINTS;
	}

	/**
	 * Returns true if the CapableActor can eat fruits growing on the tree,
	 * which is when the CapableActor is a tall herbivore and the tree has fruits growing on it,
	 * returns false otherwise.
	 * @param capableActor A CapableActor
	 * @param location Location of the Tree
	 * @return true if the CapableActor can eat fruits growing on the tree
	 */
	@Override
	public boolean canEat(CapableActor capableActor, Location location) {
		return capableActor.isHerbivorous() && capableActor.canReachTree() && hasFruits();
	}

	/**
	 * Simulates DinoActor eating fruits from the Tree,
	 * and returns the amount of food points gained by the eating the fruits.
	 * @param map map the Actor is on
	 * @param location location the Actor is at
	 * @param biteSize jaw size of the Actor that is trying to eat
	 * @return amount of food points that should be gained by eating fruits
	 */
	@Override
	public int eat(GameMap map, Location location, int biteSize) {
		int healPoints = 0;
		for (int i = 0; i < getNumberOfFruits(); i++) {
			if (Probability.generateProbability(0.5f)) {
				healPoints += eatFruit();
			}
		}
		return healPoints;
	}

	/**
	 * Returns true if the CapableActor can lay an Egg on Tree,
	 * which is when the CapableActor is arboreal, meaning it lives on trees,
	 * returns false otherwise.
	 * @param capableActor A capable actor
	 * @return true if the CapableActor can lay an Egg on Tree, false otherwise
	 */
	@Override
	public boolean canLayEggHere(CapableActor capableActor){
		return capableActor.isArboreal();
	}

	/**
	 * Returns true if the CapableActor can breed on Tree,
	 * which is when the CapableActor is arboreal, meaning it lives on trees,
	 * returns false otherwise.
	 * @param capableActor A capable actor
	 * @return true if the CapableActor can breed on Tree, false otherwise
	 */
	@Override
	public boolean canBreedHere(CapableActor capableActor){
		return capableActor.isArboreal();
	}



}
