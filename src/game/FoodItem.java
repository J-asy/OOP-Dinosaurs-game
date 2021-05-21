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

	public boolean edibleByHerbivores(){
		return hasCapability(FoodType.HERBIVORE);
	}

	public boolean edibleByCarnivores(){
		return hasCapability(FoodType.CARNIVORE);
	}

	private boolean isSmall(){
		return hasCapability(FoodType.SMALL);
	}

	private boolean isBig(){
		return hasCapability(FoodType.BIG);
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


	/**
	 * Adjusts the capabilities of the FoodItem, to indicate that it is able to be eaten
	 * whole by any DinoActor since it is small in size.
	 */
	public void setSmallSize(){
		if (!isSmall()) {
			addCapability(FoodType.SMALL);
		}
		removeCapability(FoodType.BIG);
	}

	/**
	 * Adjusts the capabilities of the FoodItem, to indicate that it is able to be eaten
	 * whole by only DinoActors with big jaws since it is big in size.
	 */
	public void setBigSize(){
		if (!isBig()) {
			addCapability(FoodType.BIG);
		}
		removeCapability(FoodType.SMALL);
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
		if (isBig()) {
			int beforeHealPoints = healPoints;
			decrementHealPoints(biteSize);
			if (healPoints == 0) {
				location.removeItem(this);
			}
			return beforeHealPoints - getHealPoints();
		}
		else {
			location.removeItem(this);
			return getHealPoints();
		}
	}
}
