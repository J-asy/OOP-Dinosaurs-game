package game;


/**
 * Base class for any item that can be picked up and dropped.
 */
public class FoodItem extends PortableItem {

	private int healPoints;

	public FoodItem(String name, char displayChar) {
		super(name, displayChar);
	}

	public boolean edibleByHerbivores(){
		return hasCapability(FoodType.HERBIVORE);
	}

	public boolean edibleByCarnivores(){
		return hasCapability(FoodType.CARNIVORE);
	}

	public void setForHerbivores(){
		if (!edibleByHerbivores()) {
			addCapability(FoodType.HERBIVORE);
		}
		removeCapability(FoodType.CARNIVORE);
	}

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

	public void decrementHealPoints(int decrementBy) {
		healPoints = Math.max(healPoints - decrementBy, 0);
	}

}
