package game.environment;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.Probability;
import game.dinosaurs.CapableActor;


/**
 * Class representing terrain of Tree type.
 */
public class Tree extends FertileGround {

	private static final char SMALL = '+';
	private static final char MEDIUM = 't';
	private static final char BIG = 'T';
	private static final char FRUITY_TREE = '&';

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

	@Override
	public boolean canEat(CapableActor capableActor) {
		return capableActor.isHerbivorous() && capableActor.canReachTree() && hasFruits();
	}

	@Override
	public int eat() {
		int healPoints = 0;
		for (int i = 0; i < getNumberOfFruits(); i++) {
			if (Probability.generateProbability(0.5f)) {
				healPoints += eatFruit();
			}
		}
		return healPoints;
	}

	@Override
	public FoodItem foodToEat() {
		return new Fruit();
	}

	@Override
	public boolean canLayEggHere(CapableActor capableActor){
		return capableActor.isArboreal();
	}

	@Override
	public boolean canBreedHere(CapableActor capableActor){
		return capableActor.isArboreal();
	}



}
