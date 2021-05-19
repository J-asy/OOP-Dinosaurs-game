package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.CapableActor;

/**
 * Base class for all Items that are considered Food
 */
public abstract class FoodItem extends PortableItem implements Food {

	/**
	 * Amount of food points that should be gained by eating the FoodItem.
	 */
	private int healPoints;

	/**
	 * Constructor.
	 * @param name name of the Food item
	 * @param displayChar display character of the Food item to be shown on the terminal
	 */
	public FoodItem(String name, char displayChar) {
		super(name, displayChar);
	}

	/**
	 * Returns true if the item is edible by herbivores, false otherwise.
	 * @return true if the item is edible by herbivores, false otherwise.
	 */
	public boolean edibleByHerbivores(){
		return hasCapability(FoodType.HERBIVORE);
	}

	/**
	 * Returns true if the item is edible by carnivores, false otherwise.
	 * @return true if the item is edible by carnivores, false otherwise.
	 */
	public boolean edibleByCarnivores(){
		return hasCapability(FoodType.CARNIVORE);
	}

	/**
	 * Adjusts the capabilities of the FoodItem, so that it is able to be eaten by herbivores.
	 */
	public void setForHerbivores(){
		if (!edibleByHerbivores()) {
			addCapability(FoodType.HERBIVORE);
		}
		removeCapability(FoodType.CARNIVORE);
	}

	/**
	 * Adjusts the capabilities of the FoodItem, so that it is able to be eaten by carnivores.
	 */
	public void setForCarnivores(){
		if (!edibleByCarnivores()) {
			addCapability(FoodType.CARNIVORE);
		}
		removeCapability(FoodType.HERBIVORE);
	}

	public void setHealPoints(int newHealPoints) {
		if (newHealPoints > 0){
			healPoints = newHealPoints;
		}
	}

	public int getHealPoints(){
		return healPoints;
	}

	/**
	 * Decrement the heal points of the FoodItem,
	 * while making sure that it stays at least equal to 0.
	 * @param decrementBy heal points to deduct from the FoodItem
	 */
	private void decrementHealPoints(int decrementBy) {
		healPoints = Math.max(healPoints - decrementBy, 0);
	}

	/**
	 * Returns name of the FoodItem
	 * @return name of the FoodItem
	 */
	@Override
	public String foodName() {
		return this.name;
	}

	/**
	 * Returns true if the FoodItem is appropriate for the CapableActor's diet, false otherwise
	 * @param capableActor a CapableActor
	 * @return true if the FoodItem is appropriate for the CapableActor's diet, false otherwise
	 */
	public boolean canEat(CapableActor capableActor){
		return capableActor.isHerbivorous() && edibleByHerbivores() ||
				capableActor.isCarnivorous() && edibleByCarnivores();
	}

	/**
	 * Simulates DinoActor eating FoodItem, and makes the FoodItem disappear if it is eaten completely,
	 * finally returns the amount of food points gained by the eating the FoodItem.
	 * @param map map the Actor is on
	 * @param location location the Actor is at
	 * @param biteSize jaw size of the Actor that is trying to eat
	 * @return amount of food points that should be gained by eating the FoodItem
	 */
	@Override
	public int eat(GameMap map, Location location, int biteSize) {
		int beforeHealPoints = healPoints;
		decrementHealPoints(biteSize);
		if (healPoints == 0) {
			location.removeItem(this);
		}
		return beforeHealPoints - getHealPoints();
	}
}
